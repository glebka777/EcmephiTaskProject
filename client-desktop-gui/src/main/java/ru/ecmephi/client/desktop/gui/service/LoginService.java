package ru.ecmephi.client.desktop.gui.service;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import lombok.val;
import ru.ecmephi.client.desktop.gui.ClientApp;
import ru.ecmephi.user.service.ws.endpoint.UserWebService;
import ru.ecmephi.user.service.ws.request.LoginRequest;
import ru.ecmephi.user.service.ws.response.UserResponse;

import javax.xml.ws.WebServiceException;

public class LoginService extends Service<UserResponse> {

    @Override
    protected Task<UserResponse> createTask() {
        return new Task<UserResponse>() {
            @Override
            protected UserResponse call() {
                return tryToLogin();
            }
        };
    }

    private UserResponse tryToLogin() throws WebServiceException {
        val loginPageController = ClientApp.getInstance().getLoginPageController();
        val username = loginPageController.getUsername();
        val password = loginPageController.getPassword();
        val request = new LoginRequest();
        request.setUsername(username);
        request.setPassword(password);
        val userWebService = new UserWebService();
        val clientPort = userWebService.getUserWSPort();
        return clientPort.login(request);
    }

}
