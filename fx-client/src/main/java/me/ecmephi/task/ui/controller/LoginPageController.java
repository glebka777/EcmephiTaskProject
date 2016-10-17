package me.ecmephi.task.ui.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import me.ecmephi.task.common.UserModel;
import me.ecmephi.task.common.response.ResponseCode;
import me.ecmephi.task.common.response.UserResponse;
import me.ecmephi.task.ui.ClientFXApp;
import me.ecmephi.task.ui.service.LoginService;
import me.ecmephi.task.ui.tool.AlertBox;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginPageController implements Initializable {

    private ClientFXApp app;

    private LoginService loginService;

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    @FXML
    private ProgressIndicator progressIndicator;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        app = ClientFXApp.getInstance();
        app.setLoginPageController(this);

        loginService = new LoginService();
        progressIndicator.visibleProperty().bind(loginService.runningProperty());
        loginService.setOnSucceeded(event->{
            UserResponse response = loginService.getValue();
            ResponseCode responseCode;
            if ((responseCode = response.getResponseCode()) != ResponseCode.SUCCESS) {
                AlertBox.display("Error", responseCode);
                return;
            }
            UserModel user = response.getUser();
            app.switchToMainPage(user);
        });
        loginService.setOnFailed(event->AlertBox.display("Error", "Connection problem occurred."));
    }

    public String getUsername() {
        return usernameField.getText().trim();
    }

    public String getPassword() {
        return passwordField.getText().trim();
    }

    public void setKeys() {
        app.getLoginPage().setOnKeyPressed(event->{
            switch (event.getCode()) {
                case ENTER:
                    loginButton.fire();
            }
        });
    }

    @FXML
    private void exitButtonClick() {
        app.getStage().close();
    }

    @FXML
    private void loginButtonClick() {
        if (!validateInput(getUsername())) {
            AlertBox.display("Warning", "Username field must not be empty.");
            return;
        }
        if (!validateInput(getPassword())) {
            AlertBox.display("Warning", "Password field must not be empty.");
            return;
        }
        loginService.restart();
    }

    private boolean validateInput(String inputString) {
        return inputString.length() != 0;
    }

}
