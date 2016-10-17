package me.ecmephi.task.ui.service;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import lombok.val;
import me.ecmephi.task.common.endpoint.UserWebService;
import me.ecmephi.task.common.request.LoginRequest;
import me.ecmephi.task.common.response.UserResponse;
import me.ecmephi.task.ui.ClientFXApp;

import javax.xml.ws.WebServiceException;

public class LoginService extends Service<UserResponse> {

    @Override
    protected Task<UserResponse> createTask() {
        return new Task<UserResponse>() {
            @Override
            protected UserResponse call() throws Exception {
                return tryToLogin();
            }
        };
    }

    private UserResponse tryToLogin() throws WebServiceException {
        val username = ClientFXApp.getInstance().getLoginPageController().getUsername();
        val password = ClientFXApp.getInstance().getLoginPageController().getPassword();
        val request = new LoginRequest();
        request.setUsername(username);
        request.setPassword(password);
        val clientPort = new UserWebService().getUserWSPort();
        return clientPort.login(request);
    }

}
