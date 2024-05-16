package com.example.pt2024_30223_grigorescu_alexandru_assignment_3.presentation.controllers;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.models.Log;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.routing.Router;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.data.daos.LogDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class LogsIndexController implements Initializable {
    @FXML
    private Text employeeName;

    @FXML
    private ImageView employeeImage;

    @FXML
    private TableColumn<Log, String> action;

    @FXML
    private TableColumn<Log, String> created_at;

    @FXML
    private Text employeeRole;

    @FXML
    private TableView<Log> table;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        action.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getAction()));
        created_at.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCreated_at().toString()));

        table.setItems(getLogs());
    }

    private ObservableList<Log> getLogs() {
        return FXCollections.observableArrayList(new LogDAO().all());
    }

    @FXML
    void goProducts() {
        Router.go("products.index");
    }

    @FXML
    void goClients() {
        Router.go("Orders.index");
    }

    @FXML
    void goOrders() {
        Router.go("orders.index");
    }

    @FXML
    void goLogs() {
        Router.go("logs.index");
    }

    @FXML
    void goEmployees() {
        Router.go("employees.index");
    }

    @FXML
    void goCreateOrder() {
        Router.go("orders.create");
    }

    @FXML
    void createClient(ActionEvent event) {
        Router.go("Orders.create");
    }
}
