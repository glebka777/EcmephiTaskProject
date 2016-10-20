package ru.ecmephi.client.desktop.gui.tool;

import javafx.fxml.FXMLLoader;
import ru.ecmephi.client.desktop.gui.ClientApp;

import java.io.IOException;
import java.net.URL;

public class CheckedFXMLLoader {

    private FXMLLoader fxmlLoader;

    public CheckedFXMLLoader(URL location) {
        fxmlLoader = new FXMLLoader(location);
    }

    public <T> T load() {
        T root = null;
        try {
            root = fxmlLoader.load();
        } catch(IOException | RuntimeException e) {
            AlertBox.display(ClientApp.ERROR, ClientApp.INIT_ERROR);
            ClientApp.getInstance().stop();
        }
        return root;
    }

}
