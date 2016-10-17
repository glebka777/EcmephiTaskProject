package me.ecmephi.task.ui.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;
import me.ecmephi.task.common.response.MessageResponse;
import me.ecmephi.task.common.response.ResponseCode;
import me.ecmephi.task.ui.ClientFXApp;
import me.ecmephi.task.ui.service.LogHistoryService;
import me.ecmephi.task.ui.tool.AlertBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainPageController implements Initializable {

    private ClientFXApp app;

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
        app = ClientFXApp.getInstance();
        app.setMainPageController(this);

        logHistoryService = new LogHistoryService();
        progressIndicator.visibleProperty().bind(logHistoryService.runningProperty());
        logArea.disableProperty().bind(logHistoryService.runningProperty());
        logHistoryService.setOnSucceeded(event->{
            MessageResponse response = logHistoryService.getValue();
            ResponseCode responseCode;
            if ((responseCode = response.getResponseCode()) != ResponseCode.SUCCESS) {
                AlertBox.display("Error", responseCode);
                return;
            }
            logArea.setText(response.getMessage());
        });
        logHistoryService.setOnFailed(event->AlertBox.display("Error", "Connection problem occurred."));
    }

    public void setUp(String username, String role) {
        usernameLabel.setText(username);
        roleLabel.setText(role);
    }

    @FXML
    private void loadLogButtonClick() {
        logHistoryService.restart();
    }

    @FXML
    private void exitButtonClick() {
        app.getStage().close();
    }

    @FXML
    private void logoffButtonClick() throws IOException {
        app.switchToLoginPage();
    }
}
