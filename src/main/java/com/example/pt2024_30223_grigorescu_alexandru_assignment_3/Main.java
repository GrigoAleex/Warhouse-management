package com.example.pt2024_30223_grigorescu_alexandru_assignment_3;

import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.controllers.UserController;
import javafx.application.Application;
import javafx.stage.Stage;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.config.ConfigLoader;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.presentation.Window;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.routing.Route;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.routing.Router;

public class Main extends Application {
    public void start(Stage primaryStage) {
        new Window(primaryStage);

        Router.go("products.index");
    }

    public static void main(String[] args) {
        ConfigLoader.handle();
        launch(args);
    }
}