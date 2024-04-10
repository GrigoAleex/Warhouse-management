package com.example.pt2024_30223_grigorescu_alexandru_assignment_2;

import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.controllers.SimulationController;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.controllers.StartController;
import javafx.application.Application;
import javafx.stage.Stage;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.config.ConfigLoader;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.frontend.Window;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.routing.Route;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.routing.Router;

public class Main extends Application {
    public void start(Stage primaryStage) {
        new Window(primaryStage);

        Router.go("start");
    }

    public static void main(String[] args) {
        ConfigLoader.handle();
        launch(args);
    }

    private static void loadRoutes() {
        Router.add(new Route("start", StartController.class, "index"));
        Router.add(new Route("simulation", SimulationController.class, "index"));
        Router.add(new Route("simulation/create", SimulationController.class, "store"));
    }
}