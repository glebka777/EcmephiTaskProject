package ru.ecmephi.user.service.interceptor;

import lombok.val;
import ru.ecmephi.user.service.ws.request.CreateUserRequest;
import ru.ecmephi.user.service.ws.request.LoginRequest;
import ru.ecmephi.user.service.ws.response.ResponseCode;
import ru.ecmephi.user.service.ws.response.UserResponse;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class InputValidationInterceptor {

    @AroundInvoke
    private Object checkFields(InvocationContext ctx) throws Exception {
        val response = new UserResponse();
        val request = ctx.getParameters()[0];
        String username;
        String password;
        if (request instanceof CreateUserRequest) {
            username = ((CreateUserRequest) request).getUsername();
            password = ((CreateUserRequest) request).getPassword();
        }
        else if (request instanceof LoginRequest) {
            username = ((LoginRequest) request).getUsername();
            password = ((LoginRequest) request).getPassword();
        }
        else {
            response.setResponseCode(ResponseCode.UNKNOWN_ERROR);
            return response;
        }
        int length;
        if ((username.equals("") || password.equals(""))
                || (((length = username.length()) < 3 && length > 0) || length > 9)
                || ((length = password.length()) < 4 && length > 0)) {
            if (request instanceof LoginRequest)
                response.setResponseCode(ResponseCode.INCORRECT_INPUT_LOGIN);
            else
                response.setResponseCode(ResponseCode.INCORRECT_INPUT_CREATE);
            return response;
        }
        return ctx.proceed();
    }

}