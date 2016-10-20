package ru.ecmephi.client.desktop.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import lombok.val;
import ru.ecmephi.client.desktop.gui.controller.LoginPageController;
import ru.ecmephi.client.desktop.gui.controller.MainPageController;
import ru.ecmephi.client.desktop.gui.tool.CheckedFXMLLoader;
import ru.ecmephi.client.desktop.gui.tool.GUISizer;
import ru.ecmephi.user.service.ws.UserModel;

@Getter
@Setter
public class ClientApp extends Application {

    public static final String ERROR = "Error";
    public static final String WARNING = "Warning";
    public static final String OK = "Ok";

    public static final String INIT_ERROR = "Cannot start application.\nError occurred while loading resources.";
    public static final String CONNECTION_PROBLEM = "Connection problem occurred.";
    public static final String EMPTY_FIELDS = "Username field must not be empty.";
    public static final String INCORRECT_INPUT = "Incorrect input.";
    public static final String USER_DOES_NOT_EXIST = "User does not exist.";
    public static final String UNKNOWN_ERROR = "Unknown error occurred.";

    private static final String MAIN_PAGE_FXML = "/fxml/main_page.fxml";
    private static final String LOGIN_PAGE_FXML = "/fxml/login_page.fxml";

    private static ClientApp instance;

    private Stage stage;
    private Scene loginPage;
    private Scene mainPage;

    private LoginPageController loginPageController;
    private MainPageController mainPageController;
    private GUISizer guiSizer;

    private UserModel currentUser;

    public static void main(String[] args) {
        launch(args);
    }

    public static ClientApp getInstance() {
        return instance;
    }

    public void switchToMainPage(UserModel user) {
        currentUser = user;
        mainPageController.setUp(user.getUsername(), user.getRole());
        stage.setScene(mainPage);
    }

    public void switchToLoginPage() {
        val fxmlLoader = new CheckedFXMLLoader(getClass().getResource(MAIN_PAGE_FXML));
        mainPage = new Scene(fxmlLoader.load(),
                guiSizer.getWidthPercent().multiply(55).getDoubleValue(),
                guiSizer.getHeightPercent().multiply(50).getDoubleValue());
        currentUser = null;
        stage.setScene(loginPage);
    }

    @Override
    public void init() {
        val visualBounds = Screen.getPrimary().getVisualBounds();
        guiSizer = new GUISizer(visualBounds);
        instance = this;
    }

    @Override
    public void start(Stage stage) {
        this.stage = stage;

        val loginPageLoader = new CheckedFXMLLoader(getClass().getResource(LOGIN_PAGE_FXML));
        loginPage = new Scene(loginPageLoader.load(),
                guiSizer.getWidthPercent().multiply(35).getDoubleValue(),
                guiSizer.getHeightPercent().multiply(40).getDoubleValue());
        val mainPageLoader = new CheckedFXMLLoader(getClass().getResource(MAIN_PAGE_FXML));
        mainPage = new Scene(mainPageLoader.load(),
                guiSizer.getWidthPercent().multiply(55).getDoubleValue(),
                guiSizer.getHeightPercent().multiply(50).getDoubleValue());
        stage.setTitle("Desktop app");
        stage.setScene(loginPage);
        stage.setResizable(false);
        stage.show();
    }

    @Override
    public void stop() {
        stage.close();
    }
}
