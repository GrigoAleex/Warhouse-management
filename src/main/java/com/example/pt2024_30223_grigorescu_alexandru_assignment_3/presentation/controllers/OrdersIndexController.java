package com.example.pt2024_30223_grigorescu_alexandru_assignment_3.presentation.controllers;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.models.Order;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.models.Order;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.routing.Router;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.data.daos.OrderDAO;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.data.daos.OrderDAO;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class OrdersIndexController implements Initializable {
    @FXML
    private Text employeeName;

    @FXML
    private ImageView employeeImage;

    @FXML
    private TableColumn<Order, String> updated_at;

    @FXML
    private TableColumn<Order, String> phone;

    @FXML
    private TableColumn<Order, String> client;

    @FXML
    private TableColumn<Order, String> created_at;

    @FXML
    private TableColumn<Order, Number> id;

    @FXML
    private Text employeeRole;

    @FXML
    private TableView<Order> table;

    @FXML
    private TableColumn<Order, String> email;

    @FXML
    private TableColumn<Order, String>  status;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        id.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getId()));
        client.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().user().getName()));
        email.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().user().getEmail()));
        phone.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().user().getPhoneNumber()));
        updated_at.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getUpdated_at().toString()));
        created_at.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCreated_at().toString()));
        status.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getStatus()));

        table.setItems(getOrders());
    }

    private ObservableList<Order> getOrders() {
        return FXCollections.observableArrayList(new OrderDAO().all());
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
