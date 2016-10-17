package me.ecmephi.task.service.interceptor;

import lombok.val;
import me.ecmephi.task.common.request.AdminProcedureRequest;
import me.ecmephi.task.common.request.CreateUserRequest;
import me.ecmephi.task.common.request.IdRequest;
import me.ecmephi.task.common.response.BaseResponse;
import me.ecmephi.task.common.response.ResponseCode;
import me.ecmephi.task.common.response.UserListResponse;
import me.ecmephi.task.common.response.UserResponse;
import me.ecmephi.task.service.dao.UserDAO;
import me.ecmephi.task.service.entity.AccessLevel;
import me.ecmephi.task.service.entity.User;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class UserAccessLevelCheckInterceptor {

    @Inject
    UserDAO userDAO;

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
        User user = this.userDAO.getById(id);
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