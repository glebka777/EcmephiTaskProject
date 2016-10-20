package ru.ecmephi.client.desktop.gui.tool;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.val;
import ru.ecmephi.client.desktop.gui.ClientApp;
import ru.ecmephi.user.service.ws.response.ResponseCode;

public class AlertBox {

    public static void display(String title, String message) {
        val window = new Stage();
        val guiResizer = ClientApp.getInstance().getGuiSizer();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);

        val label = new Label();
        label.setText(message);
        label.setWrapText(true);
        label.setTextAlignment(TextAlignment.CENTER);
        val closeButton = new Button(ClientApp.OK);
        closeButton.setPrefWidth(guiResizer.getWidthPercent().multiply(3).getDoubleValue());
        closeButton.setPrefHeight(guiResizer.getHeightPercent().multiply(2).getDoubleValue());
        closeButton.setOnAction(event -> window.close());

        val layout = new VBox(10);
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);
        layout.setOnKeyPressed(event -> processKeyboardInput(window, event));

        val scene = new Scene(layout,
                guiResizer.getWidthPercent().multiply(20).getDoubleValue(),
                guiResizer.getHeightPercent().multiply(15).getDoubleValue());
        window.setScene(scene);
        window.showAndWait();
    }

    public static void display(String title, ResponseCode responseCode) {
        display(title, translateError(responseCode));
    }

    private static void processKeyboardInput(Stage window, KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER || event.getCode() == KeyCode.ESCAPE) {
            window.close();
        }
    }

    private static String translateError(ResponseCode code) {
        String message;
        switch (code) {
            case INCORRECT_INPUT_LOGIN:
                message = ClientApp.INCORRECT_INPUT;
                break;
            case USER_DOES_NOT_EXIST:
                message = ClientApp.USER_DOES_NOT_EXIST;
                break;
            case UNKNOWN_ERROR:
                message = ClientApp.UNKNOWN_ERROR;
                break;
            default:
                message = ClientApp.UNKNOWN_ERROR;
                break;
        }
        return message;
    }

}
