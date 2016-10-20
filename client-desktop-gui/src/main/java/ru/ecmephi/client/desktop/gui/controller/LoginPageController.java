package ru.ecmephi.client.desktop.gui.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import ru.ecmephi.client.desktop.gui.ClientApp;
import ru.ecmephi.client.desktop.gui.service.LoginService;
import ru.ecmephi.client.desktop.gui.tool.AlertBox;
import ru.ecmephi.user.service.ws.UserModel;
import ru.ecmephi.user.service.ws.response.ResponseCode;
import ru.ecmephi.user.service.ws.response.UserResponse;

import java.net.URL;
import java.util.ResourceBundle;

import static ru.ecmephi.client.desktop.gui.ClientApp.*;

public class LoginPageController implements Initializable {

    private ClientApp app;

    private LoginService loginService;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    @FXML
    private ProgressIndicator progressIndicator;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        app = getInstance();
        app.setLoginPageController(this);

        loginService = new LoginService();
        progressIndicator.visibleProperty().bind(loginService.runningProperty());
        loginService.setOnSucceeded(event -> processLogin());
        loginService.setOnFailed(event -> AlertBox.display(ERROR, CONNECTION_PROBLEM));
    }

    public String getUsername() {
        return usernameField.getText().trim();
    }

    public String getPassword() {
        return passwordField.getText().trim();
    }

    private void processLogin() {
        UserResponse response = loginService.getValue();
        ResponseCode responseCode;
        if ((responseCode = response.getResponseCode()) != ResponseCode.SUCCESS) {
            AlertBox.display(ERROR, responseCode);
            return;
        }
        UserModel user = response.getUser();
        app.switchToMainPage(user);
    }

    @FXML
    private void processKeyboardInput(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case ENTER:
                loginButtonClick();
                break;
            case ESCAPE:
                exitButtonClick();
                break;
        }
    }

    @FXML
    private void exitButtonClick() {
        app.stop();
    }

    @FXML
    private void loginButtonClick() {
        if (!validateInput(getUsername()) || !validateInput(getPassword())) {
            AlertBox.display(WARNING, EMPTY_FIELDS);
            return;
        }
        loginService.restart();
    }

    private boolean validateInput(String inputString) {
        return inputString.length() != 0;
    }

}
