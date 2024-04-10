package com.example.pt2024_30223_grigorescu_alexandru_assignment_2.frontend.controllers;

import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.Models.Simulation;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.config.AppProvider;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.utils.LoaderManager;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.routing.Router;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.routing.Session;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import java.util.Timer;
import java.util.TimerTask;


public class LoadingController {

    @FXML
    private ProgressBar progress_bar;

    public void initialize() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            Double counter;

            @Override
            public void run() {
                counter = AppProvider.get(LoaderManager.class).getLoadingProgress();

                Platform.runLater(() -> progress_bar.setProgress(counter));

                if (counter >= 1.0) {
                    System.out.println("Timer finished.");
                    timer.cancel();
                    Platform.runLater(new Runnable() {
                        @Override public void run() {
                            Router.go("logs");
                        }
                    });

                }
            }
        };

        timer.scheduleAtFixedRate(task, 0, 300);
    }

}
