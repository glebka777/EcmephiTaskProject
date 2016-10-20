package ru.ecmephi.user.service.interceptor;

import lombok.val;
import ru.ecmephi.user.service.dao.UserDAO;
import ru.ecmephi.user.service.ws.request.AdminProcedureRequest;
import ru.ecmephi.user.service.ws.request.IdRequest;
import ru.ecmephi.user.service.ws.response.BaseResponse;
import ru.ecmephi.user.service.ws.response.MessageResponse;
import ru.ecmephi.user.service.ws.response.ResponseCode;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class UserExistsCheckInterceptor {

    @Inject
    private UserDAO userDAO;

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