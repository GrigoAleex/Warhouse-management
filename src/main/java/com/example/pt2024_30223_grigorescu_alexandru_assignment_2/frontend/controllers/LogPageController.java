package com.example.pt2024_30223_grigorescu_alexandru_assignment_2.frontend.controllers;

import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.config.AppProvider;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.utils.Logger;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.routing.Router;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class LogPageController {

    @FXML
    private Text avg_waiting_time;

    @FXML
    private Text avg_service_time;

    @FXML
    private TextArea logs;

    @FXML
    private Text peak_hour;

    @FXML
    void submit(ActionEvent event) {
        Router.go("start");
    }

    public void initialize() {
        Logger logger = AppProvider.get(Logger.class);
        avg_waiting_time.setText(logger.getAverageWaitingTime());
        avg_service_time.setText(logger.gerAverageServiceTime());
        peak_hour.setText(logger.getPeakHour());
        logs.setText(logger.logsString);
    }
}
