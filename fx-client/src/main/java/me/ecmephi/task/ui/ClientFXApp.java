package me.ecmephi.task.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import lombok.val;
import me.ecmephi.task.common.UserModel;
import me.ecmephi.task.ui.controller.LoginPageController;
import me.ecmephi.task.ui.controller.MainPageController;

import java.io.IOException;

@Getter
@Setter
public class ClientFXApp extends Application {

    private static final String mainPageFXML = "/fxml/main_page.fxml";
    private static final String loginPageFXML = "/fxml/login_page.fxml";

    public static double WIDTH;
    public static double HEIGHT;

    private static ClientFXApp instance;

    private Stage stage;
    private Scene loginPage;
    private Scene mainPage;

    private LoginPageController loginPageController;
    private MainPageController mainPageController;

    private UserModel currentUser;

    public static void main(String[] args) {
        launch(args);
    }

    public static ClientFXApp getInstance() {
        return instance;
    }

    public void switchToMainPage(UserModel user) {
        currentUser = user;
        mainPageController.setUp(user.getUsername(), user.getRole());
        stage.setScene(mainPage);
    }

    public void switchToLoginPage() throws IOException {
        val fxmlLoader = new FXMLLoader(getClass().getResource(mainPageFXML));
        mainPage = new Scene(fxmlLoader.load(), WIDTH / 100 * 55, HEIGHT / 100 * 50);
        currentUser = null;
        stage.setScene(loginPage);
    }

    @Override
    public void init() throws Exception {
        val visualBounds = Screen.getPrimary().getVisualBounds();
        WIDTH = visualBounds.getWidth();
        HEIGHT = visualBounds.getHeight();
        instance = this;
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(loginPageFXML));
        loginPage = new Scene(fxmlLoader.load(), WIDTH / 100 * 35, HEIGHT / 100 * 40);
        loginPageController.setKeys();
        fxmlLoader = new FXMLLoader(getClass().getResource(mainPageFXML));
        mainPage = new Scene(fxmlLoader.load(), WIDTH / 100 * 55, HEIGHT / 100 * 50);

        stage.setTitle("Desktop app");
        stage.setScene(loginPage);
        stage.setResizable(false);
        stage.show();
    }


}
