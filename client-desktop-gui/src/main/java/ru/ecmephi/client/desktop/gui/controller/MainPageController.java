package ru.ecmephi.client.desktop.gui.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import ru.ecmephi.client.desktop.gui.ClientApp;
import ru.ecmephi.client.desktop.gui.service.LogHistoryService;
import ru.ecmephi.client.desktop.gui.tool.AlertBox;
import ru.ecmephi.user.service.ws.response.MessageResponse;
import ru.ecmephi.user.service.ws.response.ResponseCode;

import java.net.URL;
import java.util.ResourceBundle;

import static ru.ecmephi.client.desktop.gui.ClientApp.CONNECTION_PROBLEM;
import static ru.ecmephi.client.desktop.gui.ClientApp.ERROR;

public class MainPageController implements Initializable {

    private ClientApp app;

    private LogHistoryService logHistoryService;

    @FXML
    private Label usernameLabel;

    @FXML
    private Label roleLabel;

    @FXML
    private TextArea logArea;

    @FXML
    private ProgressIndicator progressIndicator;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        app = ClientApp.getInstance();
        app.setMainPageController(this);

        logHistoryService = new LogHistoryService();
        progressIndicator.visibleProperty().bind(logHistoryService.runningProperty());
        logArea.disableProperty().bind(logHistoryService.runningProperty());
        logHistoryService.setOnSucceeded(event -> processLogRequest());
        logHistoryService.setOnFailed(event -> AlertBox.display(ERROR, CONNECTION_PROBLEM));
    }

    public void setUp(String username, String role) {
        usernameLabel.setText(username);
        roleLabel.setText(role);
    }

    private void processLogRequest() {
        MessageResponse response = logHistoryService.getValue();
        ResponseCode responseCode;
        if ((responseCode = response.getResponseCode()) != ResponseCode.SUCCESS) {
            AlertBox.display(ERROR, responseCode);
            return;
        }
        logArea.setText(response.getMessage());
    }

    @FXML
    private void loadLogButtonClick() {
        logHistoryService.restart();
    }

    @FXML
    private void exitButtonClick() {
        app.stop();
    }

    @FXML
    private void processKeyboardInput(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case ESCAPE:
                logoffButtonClick();
                break;
        }
    }

    @FXML
    private void logoffButtonClick() {
        app.switchToLoginPage();
    }
}
