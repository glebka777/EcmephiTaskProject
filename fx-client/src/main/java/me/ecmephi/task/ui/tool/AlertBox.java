package me.ecmephi.task.ui.tool;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.val;
import me.ecmephi.task.common.response.ResponseCode;
import me.ecmephi.task.ui.ClientFXApp;

public class AlertBox {

    public static void display(String title, String message) {
        val window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);

        val label = new Label();
        label.setText(message);
        label.setWrapText(true);
        label.setTextAlignment(TextAlignment.CENTER);
        val closeButton = new Button("Ok");
        closeButton.setPrefWidth(ClientFXApp.WIDTH / 100 * 3);
        closeButton.setPrefHeight(ClientFXApp.HEIGHT / 100 * 2);
        closeButton.setOnAction(event->window.close());

        val layout = new VBox(10);
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);
        layout.setOnKeyPressed(event->{
            if (event.getCode() == KeyCode.ENTER || event.getCode() == KeyCode.ESCAPE) {
                window.close();
            }
        });

        val scene = new Scene(layout, ClientFXApp.WIDTH / 100 * 20, ClientFXApp.HEIGHT / 100 * 15);
        window.setScene(scene);
        window.showAndWait();
    }

    public static void display(String title, ResponseCode responseCode) {
        display(title, translateError(responseCode));
    }

    private static String translateError(ResponseCode code) {
        String message;
        switch (code) {
            case INCORRECT_INPUT:
                message = "Incorrect input.";
                break;
            case USER_DOES_NOT_EXIST:
                message = "User does not exist.";
                break;
            case UNKNOWN_ERROR:
                message = "Unknown error occurred.";
                break;
            default:
                message = "Unknown error occurred.";
                break;
        }
        return message;
    }

}
