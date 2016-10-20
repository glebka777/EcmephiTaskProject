package ru.ecmephi.user.service;

import lombok.val;
import org.apache.log4j.Logger;
import ru.ecmephi.user.service.dao.UserDAO;
import ru.ecmephi.user.service.entity.AccessLevel;
import ru.ecmephi.user.service.entity.User;
import ru.ecmephi.user.service.interceptor.InputValidationInterceptor;
import ru.ecmephi.user.service.interceptor.UserAccessLevelCheckInterceptor;
import ru.ecmephi.user.service.interceptor.UserExistsCheckInterceptor;
import ru.ecmephi.user.service.ws.UserModel;
import ru.ecmephi.user.service.ws.endpoint.UserWS;
import ru.ecmephi.user.service.ws.request.AdminProcedureRequest;
import ru.ecmephi.user.service.ws.request.CreateUserRequest;
import ru.ecmephi.user.service.ws.request.IdRequest;
import ru.ecmephi.user.service.ws.request.LoginRequest;
import ru.ecmephi.user.service.ws.response.*;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.jws.WebService;

@Stateless
@Local(UserWS.class)
@WebService(
        serviceName = "UserWebService",
        portName = "UserWSPort",
        endpointInterface = "ru.ecmephi.user.service.ws.endpoint.UserWS"
)
public class UserManager implements UserWS {

    private static final Logger logger = Logger.getLogger(UserManager.class);
    private static final String ADMINISTRATOR_RIGHTS = "administrator";
    private static final String STANDARD_USER_RIGHTS = "standard user";

    @Inject
    private UserDAO userDAO;

    @Inject
    private UserLogger userLogger;

    @Interceptors({UserAccessLevelCheckInterceptor.class, UserExistsCheckInterceptor.class})
    public BaseResponse grantAdministratorAccessLevel(AdminProcedureRequest request) {
        val response = new BaseResponse();
        val targetId = request.getTargetId();
        val adminId = request.getAdminId();
        val user = userDAO.getById(targetId);
        val admin = userDAO.getById(adminId);
        if (user.getAccessLevel() == AccessLevel.ADMINISTRATOR) {
            response.setResponseCode(ResponseCode.SAME_RIGHTS);
            return response;
        }
        user.setAccessLevel(AccessLevel.ADMINISTRATOR);
        userLogger.markRightsChange(user, admin, ADMINISTRATOR_RIGHTS);
        userDAO.update(user);
        val username = user.getUsername();
        logger.info(String.format("Rights of the user '%s' changed to administrator rights", username));
        response.setResponseCode(ResponseCode.SUCCESS);
        return response;
    }

    @Interceptors({UserAccessLevelCheckInterceptor.class, UserExistsCheckInterceptor.class})
    public BaseResponse grantStandardUserAccessLevel(AdminProcedureRequest request) {
        val response = new BaseResponse();
        val adminId = request.getAdminId();
        val targetId = request.getTargetId();
        if (adminId.equals(targetId)) {
            response.setResponseCode(ResponseCode.OPERATION_ON_CURRENT_USER);
            return response;
        }
        val user = userDAO.getById(targetId);
        val admin = userDAO.getById(adminId);
        if (user.getAccessLevel() == AccessLevel.USER) {
            response.setResponseCode(ResponseCode.SAME_RIGHTS);
            return response;
        }
        user.setAccessLevel(AccessLevel.USER);
        userLogger.markRightsChange(user, admin, STANDARD_USER_RIGHTS);
        userDAO.update(user);
        val username = user.getUsername();
        logger.info(String.format("Rights of the user '%s' changed to non-administrator rights", username));
        response.setResponseCode(ResponseCode.SUCCESS);
        return response;
    }

    @Interceptors({UserAccessLevelCheckInterceptor.class, UserExistsCheckInterceptor.class})
    public BaseResponse clearHistory(AdminProcedureRequest request) {
        val response = new BaseResponse();
        val targetId = request.getTargetId();
        val user = userDAO.getById(targetId);
        userLogger.clearLog(user);
        userDAO.update(user);
        val username = user.getUsername();
        logger.info(String.format("Log of the user '%s' cleared", username));
        response.setResponseCode(ResponseCode.SUCCESS);
        return response;
    }

    @Interceptors({UserExistsCheckInterceptor.class})
    public MessageResponse getAccessLevel(IdRequest request) {
        val response = new MessageResponse();
        val id = request.getId();
        val user = userDAO.getById(id);
        AccessLevel accessLevel = user.getAccessLevel();
        response.setResponseCode(ResponseCode.SUCCESS);
        response.setMessage(accessLevel.toString());
        return response;
    }

    @Interceptors({UserExistsCheckInterceptor.class})
    public MessageResponse getLog(IdRequest request) {
        val response = new MessageResponse();
        val id = request.getId();
        val user = userDAO.getById(id);
        val log = userLogger.getFullLog(user);
        response.setResponseCode(ResponseCode.SUCCESS);
        response.setMessage(log);
        return response;
    }

    @Interceptors({UserAccessLevelCheckInterceptor.class, InputValidationInterceptor.class})
    public UserResponse create(CreateUserRequest request) {
        val response = new UserResponse();
        val username = request.getUsername();
        val password = request.getPassword();
        if (userDAO.getByUsername(username) != null) {
            response.setResponseCode(ResponseCode.EXISTING_USERNAME);
            return response;
        }
        val user = new User();
        user.setUsername(username);
        user.setPassword(password);
        userDAO.create(user);
        if (user.getId() == null) {
            logger.error("Cannot create user: unknown error");
            response.setResponseCode(ResponseCode.UNKNOWN_ERROR);
            return response;
        }
        userLogger.markCreation(user);
        userDAO.update(user);
        val id = user.getId();
        logger.info(String.format("User '%s' created. Id: %d", username, id));
        response.setResponseCode(ResponseCode.SUCCESS);
        response.setUser(createUserModel(user));
        return response;
    }

    @Interceptors({UserAccessLevelCheckInterceptor.class, UserExistsCheckInterceptor.class})
    public BaseResponse delete(AdminProcedureRequest request) {
        val response = new BaseResponse();
        val adminId = request.getAdminId();
        val targetId = request.getTargetId();
        if (adminId.equals(targetId)) {
            response.setResponseCode(ResponseCode.OPERATION_ON_CURRENT_USER);
            return response;
        }
        val user = userDAO.remove(targetId);
        logger.info(String.format("User '%s' with id %d deleted", user.getUsername(), user.getId()));
        response.setResponseCode(ResponseCode.SUCCESS);
        return response;
    }

    @Interceptors({UserAccessLevelCheckInterceptor.class})
    public UserListResponse getAllUsers(IdRequest request) {
        val response = new UserListResponse();
        val userList = userDAO.getAll();
        val userModels = response.getUserList();
        userList.forEach(user -> userModels.add(createUserModel(user)));
        response.setResponseCode(ResponseCode.SUCCESS);
        return response;
    }

    @Interceptors({InputValidationInterceptor.class})
    public UserResponse login(LoginRequest request) {
        val response = new UserResponse();
        val username = request.getUsername();
        val password = request.getPassword();
        val users = userDAO.getAll();
        val matchedUser = users.stream()
                .filter(user -> matchUser(user, username, password))
                .findFirst();
        if (matchedUser.isPresent()) {
            val user = matchedUser.get();
            userLogger.markLogon(user);
            userDAO.update(user);
            logger.info(String.format("User '%s' logged in", username));
            response.setResponseCode(ResponseCode.SUCCESS);
            response.setUser(createUserModel(user));
            return response;
        }
        response.setResponseCode(ResponseCode.INCORRECT_INPUT_LOGIN);
        return response;
    }

    private boolean matchUser(User user, String username, String password) {
        return user.getUsername().equals(username) && user.getPassword().equals(password);
    }

    private UserModel createUserModel(User user) {
        val userModel = new UserModel();
        userModel.setId(user.getId());
        userModel.setUsername(user.getUsername());
        userModel.setRole(user.getAccessLevel().toString());
        return userModel;
    }

}