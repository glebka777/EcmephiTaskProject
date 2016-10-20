package ru.ecmephi.user.service.interceptor;

import lombok.val;
import ru.ecmephi.user.service.dao.UserDAO;
import ru.ecmephi.user.service.entity.AccessLevel;
import ru.ecmephi.user.service.entity.User;
import ru.ecmephi.user.service.ws.request.AdminProcedureRequest;
import ru.ecmephi.user.service.ws.request.CreateUserRequest;
import ru.ecmephi.user.service.ws.request.IdRequest;
import ru.ecmephi.user.service.ws.response.BaseResponse;
import ru.ecmephi.user.service.ws.response.ResponseCode;
import ru.ecmephi.user.service.ws.response.UserListResponse;
import ru.ecmephi.user.service.ws.response.UserResponse;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class UserAccessLevelCheckInterceptor {

    @Inject
    private UserDAO userDAO;

    @AroundInvoke
    private Object checkUserAccessLevel(InvocationContext ctx) throws Exception {
        BaseResponse response;
        Long id;
        val request = ctx.getParameters()[0];
        if (request instanceof AdminProcedureRequest) {
            id = ((AdminProcedureRequest) request).getAdminId();
            response = new BaseResponse();
        }
        else if (request instanceof CreateUserRequest) {
            id = ((CreateUserRequest) request).getAdminId();
            response = new UserResponse();
        }
        else if (request instanceof IdRequest) {
            id = ((IdRequest) request).getId();
            response = new UserListResponse();
        }
        else {
            response = new BaseResponse();
            response.setResponseCode(ResponseCode.UNKNOWN_ERROR);
            return response;
        }
        User user = userDAO.getById(id);
        if (user == null) {
            response.setResponseCode(ResponseCode.UNKNOWN_ERROR);
            return response;
        }
        else if (!user.getAccessLevel().equals(AccessLevel.ADMINISTRATOR)) {
            response.setResponseCode(ResponseCode.ACCESS_DENIED);
            return response;
        }
        else
            return ctx.proceed();
    }

}