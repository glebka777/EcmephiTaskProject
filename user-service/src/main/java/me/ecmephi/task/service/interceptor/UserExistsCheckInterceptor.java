package me.ecmephi.task.service.interceptor;

import lombok.val;
import me.ecmephi.task.common.request.AdminProcedureRequest;
import me.ecmephi.task.common.request.IdRequest;
import me.ecmephi.task.common.response.BaseResponse;
import me.ecmephi.task.common.response.MessageResponse;
import me.ecmephi.task.common.response.ResponseCode;
import me.ecmephi.task.service.dao.UserDAO;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class UserExistsCheckInterceptor {

    @Inject
    UserDAO userDAO;

    @AroundInvoke
    public Object checkUserExistence(InvocationContext ctx) throws Exception {
        BaseResponse response;
        Long id;
        val request = ctx.getParameters()[0];
        if (request instanceof AdminProcedureRequest) {
            id = ((AdminProcedureRequest) request).getTargetId();
            response = new BaseResponse();
            if (userDAO.getById(((AdminProcedureRequest) request).getAdminId()) == null) {
                response.setResponseCode(ResponseCode.UNKNOWN_ERROR);
                return response;
            }
        }
        else if (request instanceof IdRequest) {
            id = ((IdRequest) request).getId();
            response = new MessageResponse();
        }
        else {
            response = new BaseResponse();
            response.setResponseCode(ResponseCode.UNKNOWN_ERROR);
            return response;
        }
        val user = userDAO.getById(id);
        if (user == null) {
            response.setResponseCode(ResponseCode.USER_DOES_NOT_EXIST);
            return response;
        }
        return ctx.proceed();
    }

}