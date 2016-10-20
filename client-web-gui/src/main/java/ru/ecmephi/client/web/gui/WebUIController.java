package ru.ecmephi.client.web.gui;

import lombok.Getter;
import lombok.Setter;
import lombok.val;
import ru.ecmephi.user.service.ws.UserModel;
import ru.ecmephi.user.service.ws.endpoint.UserWS;
import ru.ecmephi.user.service.ws.endpoint.UserWebService;
import ru.ecmephi.user.service.ws.request.AdminProcedureRequest;
import ru.ecmephi.user.service.ws.request.CreateUserRequest;
import ru.ecmephi.user.service.ws.request.IdRequest;
import ru.ecmephi.user.service.ws.request.LoginRequest;
import ru.ecmephi.user.service.ws.response.BaseResponse;
import ru.ecmephi.user.service.ws.response.ResponseCode;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.List;

@SuppressWarnings("Duplicates")
@ManagedBean
@SessionScoped
@Getter
@Setter
public class WebUIController {

    private static final String NOT_ALLOWED = "Not allowed for current user.";
    private static final String USER_EXISTS = "User with this name already exists.";
    private static final String INCORRECT_INPUT_ON_CREATE = "Incorrect input.\nUsername should contain 3-9 symbols." +
            "\nPassword should contain more than 3 symbols.";
    private static final String INCORRECT_INPUT_ON_LOGIN = "Incorrect input.";
    private static final String MODIFY_ERROR = "Cannot modify current user.";
    private static final String USER_DOES_NOT_EXIST = "User does not exist.";
    private static final String UNKNOWN_ERROR = "Unknown error occurred.";
    private static final String DENIED = "Denied.";

    private static final String ADMINISTRATOR_RIGHTS = "administrator";
    private static final String STANDARD_USER_RIGHTS = "standard user";
    private static final String LOGIN_PAGE = "login";
    private static final String MAIN_PAGE = "main";
    private static final String UNAUTHORIZED_PAGE = "access_denied";


    private UserWS userService;

    private String username;
    private String password;
    private UserModel superUser;

    private String newUsername;
    private String newPassword;
    private Boolean newAdmin;

    private Long logId;
    private Long idToClear;
    private Long idToDelete;
    private Long idToChange;
    private Boolean changeToAdmin = false;

    private String logHistory;

    private List<UserModel> userList;
    private UserModel targetUser;

    public String logOff() {
        superUser = null;
        clearFields();
        return LOGIN_PAGE;
    }

    public String tryToLogin() {
        updatePort();
        val request = new LoginRequest(username, password);
        val response = userService.login(request);
        ResponseCode responseCode;
        if ((responseCode = response.getResponseCode()) == ResponseCode.SUCCESS) {
            superUser = response.getUser();
            return MAIN_PAGE;
        }
        else {
            val errorMessage = translateError(responseCode);
            val facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, null);
            val context = FacesContext.getCurrentInstance();
            context.addMessage(null, facesMessage);
        }
        clearFields();
        return null;
    }

    public String createNewUser() {
        if (!checkUser())
            return UNAUTHORIZED_PAGE;
        updatePort();
        val context = FacesContext.getCurrentInstance();
        val createRequest = new CreateUserRequest(superUser.getId(), newUsername, newPassword);
        val response = userService.create(createRequest);
        ResponseCode responseCode;
        FacesMessage facesMessage;
        if ((responseCode = response.getResponseCode()) == ResponseCode.SUCCESS) {
            val newUser = response.getUser();
            val id = newUser.getId();
            if (newAdmin) {
                val changeAccessLevelRequest = new AdminProcedureRequest(superUser.getId(), id);
                userService.grantAdministratorAccessLevel(changeAccessLevelRequest);
                facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, String.format("New administrator %s has been " +
                        "created (id = %d).", newUsername, id), null);
            }
            else {
                facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, String.format("New user %s has been created " +
                        "(id =" + "%d).", newUsername, id), null);
            }
        }
        else {
            val errorMessage = translateError(responseCode);
            facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, null);
        }
        context.addMessage(null, facesMessage);
        clearFields();
        if (userList != null)
            getAllUsers();
        return null;
    }

    public String deleteUser(Long id) {
        if (!checkUser())
            return UNAUTHORIZED_PAGE;
        updatePort();
        val context = FacesContext.getCurrentInstance();
        val deleteRequest = new AdminProcedureRequest(superUser.getId(), id);
        val response = userService.delete(deleteRequest);
        ResponseCode responseCode;
        FacesMessage facesMessage;
        if ((responseCode = response.getResponseCode()) == ResponseCode.SUCCESS) {
            facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, String.format("User with id %d has been " +
                    "deleted.", id), null);
        }
        else {
            val errorMessage = translateError(responseCode);
            facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, null);
        }
        context.addMessage(null, facesMessage);
        clearFields();
        if (userList != null)
            getAllUsers();
        return null;
    }

    public String changeRights(Long id) {
        if (!checkUser())
            return UNAUTHORIZED_PAGE;
        updatePort();
        val context = FacesContext.getCurrentInstance();
        val changeRightsRequest = new AdminProcedureRequest(superUser.getId(), id);
        BaseResponse response;
        if (changeToAdmin)
            response = userService.grantAdministratorAccessLevel(changeRightsRequest);
        else
            response = userService.grantStandardUserAccessLevel(changeRightsRequest);
        ResponseCode responseCode;
        FacesMessage facesMessage;
        if ((responseCode = response.getResponseCode()) == ResponseCode.SUCCESS) {
            val newRights = changeToAdmin ? "administrator" : "standard user";
            facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, String.format("User with id %d now " +
                    "has %s rights", id, newRights), null);
        }
        else {
            val errorMessage = translateError(responseCode);
            facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, null);
        }
        context.addMessage(null, facesMessage);
        clearFields();
        if (userList != null)
            getAllUsers();
        return null;
    }

    public String clearUserLog() {
        if (!checkUser())
            return UNAUTHORIZED_PAGE;
        updatePort();
        val context = FacesContext.getCurrentInstance();
        val clearLogRequest = new AdminProcedureRequest(superUser.getId(), idToClear);
        val response = userService.clearHistory(clearLogRequest);
        ResponseCode responseCode;
        FacesMessage message;
        if ((responseCode = response.getResponseCode()) == ResponseCode.SUCCESS) {
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Log of the user has been cleared.", null);
        }
        else {
            val errorMessage = translateError(responseCode);
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, null);
        }
        context.addMessage(null, message);
        clearFields();
        return null;
    }

    public String getUserLog() {
        if (!checkUser())
            return UNAUTHORIZED_PAGE;
        updatePort();
        clearLogWindow();
        val context = FacesContext.getCurrentInstance();
        if (superUser.getRole().equals("User") && !superUser.getId().equals(logId)) {
            val message = new FacesMessage(FacesMessage.SEVERITY_ERROR, DENIED, null);
            context.addMessage(null, message);
            return null;
        }
        val getLogRequest = new IdRequest(logId);
        val response = userService.getLog(getLogRequest);
        ResponseCode responseCode;
        if ((responseCode = response.getResponseCode()) == ResponseCode.SUCCESS) {
            logHistory = response.getMessage();
        }
        else {
            val errorMessage = translateError(responseCode);
            val message = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, null);
            context.addMessage(null, message);
        }
        clearFields();
        return null;
    }

    public String clearLogWindow() {
        if (!checkUser())
            return UNAUTHORIZED_PAGE;
        logHistory = "";
        return null;
    }

    public String getAllUsers() {
        if (!checkUser())
            return UNAUTHORIZED_PAGE;
        updatePort();
        clearUsersWindow();
        val context = FacesContext.getCurrentInstance();
        val getAllRequest = new IdRequest(superUser.getId());
        val response = userService.getAllUsers(getAllRequest);
        ResponseCode responseCode;
        if ((responseCode = response.getResponseCode()) == ResponseCode.SUCCESS) {
            userList = response.getUserList();
        }
        else {
            val errorMessage = translateError(responseCode);
            val message = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, null);
            context.addMessage(null, message);
        }
        clearFields();
        return null;
    }

    public String clearUsersWindow() {
        if (!checkUser())
            return UNAUTHORIZED_PAGE;
        userList = null;
        clearFields();
        return null;
    }

    private Boolean checkUser() {
        return superUser != null;
    }

    private void clearFields() {
        username = "";
        password = "";
        newUsername = "";
        newPassword = "";
        newAdmin = false;
        logId = null;
        idToDelete = null;
        idToChange = null;
        idToClear = null;
    }

    private String translateError(ResponseCode code) {
        String message;
        switch (code) {
            case ACCESS_DENIED:
                message = NOT_ALLOWED;
                break;
            case EXISTING_USERNAME:
                message = USER_EXISTS;
                break;
            case INCORRECT_INPUT_CREATE:
                message = INCORRECT_INPUT_ON_CREATE;
                break;
            case INCORRECT_INPUT_LOGIN:
                message = INCORRECT_INPUT_ON_LOGIN;
                break;
            case OPERATION_ON_CURRENT_USER:
                message = MODIFY_ERROR;
                break;
            case SAME_RIGHTS:
                val newRights = changeToAdmin ? ADMINISTRATOR_RIGHTS : STANDARD_USER_RIGHTS;
                message = "User already has " + newRights + " rights";
                break;
            case USER_DOES_NOT_EXIST:
                message = USER_DOES_NOT_EXIST;
                break;
            case UNKNOWN_ERROR:
                message = UNKNOWN_ERROR;
                break;
            default:
                message = UNKNOWN_ERROR;
                break;
        }
        return message;
    }

    private void updatePort() {
        if (userService == null) {
            val userWebService = new UserWebService();
            userService = userWebService.getUserWSPort();
        }
    }

}
