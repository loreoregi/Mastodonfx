package eus.ehu.bum4fx;

import eus.ehu.bum4fx.controllers.FxController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Launcher extends Application {
    private Window mainWindow;
    private Window tootsWindow;

    private Window followersWindow;

    private Window followingWindow;

    class Window {
        Parent ui;
        FxController controller;

    }

    private Window load(String fxmlFile) throws IOException {
        Window window = new Window();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));
        window.ui = fxmlLoader.load();
        window.controller = fxmlLoader.getController();
        window.controller.setMain(this);
        return window;
    }


    private Stage stage;
    private Scene scene;

    @Override
    public void start(Stage stage) throws IOException {

        this.stage = stage;
        mainWindow = load("main-view.fxml");
        tootsWindow = load("toot-view.fxml");
        followersWindow = load("followers-view.fxml");
        followingWindow = load("followings-view.fxml");


        scene = new Scene (mainWindow.ui);
        stage.setTitle("Main");
        stage.setScene( scene );
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public void changeScene(String sceneName) {
        switch (sceneName) {
            case "Main":
                stage.setTitle("Main");
                scene.setRoot(mainWindow.ui);
                break;
            case "Show Toots":
                stage.setTitle("Show Toots");
                scene.setRoot(tootsWindow.ui);
                break;
            case "Show Followers":
                stage.setTitle("Show Followers");
                scene.setRoot(followersWindow.ui);
            case "Show Followings":
                stage.setTitle("Show Followings");
                scene.setRoot(followingWindow.ui);
        }
    }
}