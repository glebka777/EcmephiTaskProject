package me.ecmephi.task.service;

import lombok.val;
import me.ecmephi.task.common.UserModel;
import me.ecmephi.task.common.endpoint.UserWS;
import me.ecmephi.task.common.request.AdminProcedureRequest;
import me.ecmephi.task.common.request.CreateUserRequest;
import me.ecmephi.task.common.request.IdRequest;
import me.ecmephi.task.common.request.LoginRequest;
import me.ecmephi.task.common.response.*;
import me.ecmephi.task.service.dao.UserDAO;
import me.ecmephi.task.service.entity.AccessLevel;
import me.ecmephi.task.service.entity.User;
import me.ecmephi.task.service.interceptor.InputValidationInterceptor;
import me.ecmephi.task.service.interceptor.UserAccessLevelCheckInterceptor;
import me.ecmephi.task.service.interceptor.UserExistsCheckInterceptor;
import org.apache.log4j.Logger;

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
        endpointInterface = "me.ecmephi.task.common.endpoint.UserWS"
)
public class UserManager implements UserWS {

    private static final Logger logger = Logger.getLogger(UserManager.class);
    private static final String administratorRights = "administrator";
    private static final String nonAdministratorRights = "standard user";

    @Inject
    UserDAO userDAO;

    @Inject
    UserLogger userLogger;

    @Interceptors({UserAccessLevelCheckInterceptor.class, UserExistsCheckInterceptor.class})
    public BaseResponse grantAdministratorAccessLevel(AdminProcedureRequest request) {
        val response = new BaseResponse();
        val user = userDAO.getById(request.getTargetId());
        val admin = userDAO.getById(request.getAdminId());
        if (user.getAccessLevel() == AccessLevel.ADMINISTRATOR) {
            response.setResponseCode(ResponseCode.SAME_RIGHTS);
            return response;
        }
        user.setAccessLevel(AccessLevel.ADMINISTRATOR);
        userLogger.markRightsChange(user, admin, administratorRights);
        userDAO.update(user);
        logger.info(String.format("Rights of the user '%s' changed to administrator rights", user
                .getUsername()));
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
        userLogger.markRightsChange(user, admin, nonAdministratorRights);
        userDAO.update(user);
        logger.info(String.format("Rights of the user '%s' changed to non-administrator rights", user
                .getUsername()));
        response.setResponseCode(ResponseCode.SUCCESS);
        return response;
    }

    @Interceptors({UserAccessLevelCheckInterceptor.class, UserExistsCheckInterceptor.class})
    public BaseResponse clearHistory(AdminProcedureRequest request) {
        val response = new BaseResponse();
        val user = userDAO.getById(request.getTargetId());
        userLogger.clearLog(user);
        userDAO.update(user);
        logger.info(String.format("Log of the user '%s' cleared", user.getUsername()));
        response.setResponseCode(ResponseCode.SUCCESS);
        return response;
    }

    @Interceptors({UserExistsCheckInterceptor.class})
    public MessageResponse getAccessLevel(IdRequest request) {
        val response = new MessageResponse();
        val user = userDAO.getById(request.getId());
        AccessLevel accessLevel = user.getAccessLevel();
        response.setResponseCode(ResponseCode.SUCCESS);
        response.setMessage(accessLevel.toString());
        return response;
    }

    @Interceptors({UserExistsCheckInterceptor.class})
    public MessageResponse getLog(IdRequest request) {
        val response = new MessageResponse();
        val user = userDAO.getById(request.getId());
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
        val user = new User(username, password);
        userDAO.create(user);
        if (user.getId() == null) {
            logger.error("Cannot create user: unknown error");
            response.setResponseCode(ResponseCode.UNKNOWN_ERROR);
            return response;
        }
        userLogger.markCreation(user);
        userDAO.update(user);
        logger.info(String.format("User '%s' created. Id: %d", username, user.getId()));
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
        userList.forEach(user->userModels.add(createUserModel(user)));
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
                .filter(user->(user.getUsername().equals(username) && user.getPassword().equals(password)))
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
        response.setResponseCode(ResponseCode.INCORRECT_INPUT);
        return response;
    }

    private UserModel createUserModel(User user) {
        val userModel = new UserModel();
        userModel.setId(user.getId());
        userModel.setUsername(user.getUsername());
        userModel.setRole(user.getAccessLevel().toString());
        return userModel;
    }

}