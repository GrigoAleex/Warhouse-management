package com.example.pt2024_30223_grigorescu_alexandru_assignment_3.presentation.controllers;


import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.requests.UserStoreRequest;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.routing.Router;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class UsersCreateController {

    @FXML
    private Text employeeName;

    @FXML
    private ImageView employeeImage;

    @FXML
    private TextField userPhone;

    @FXML
    private TextField userEmail;

    @FXML
    private TextField userName;

    @FXML
    private Text employeeRole;

    @FXML
    void goProducts() {
        Router.go("products.index");
    }

    @FXML
    void goClients() {
        Router.go("users.index");
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
    void save(ActionEvent event) {
        UserStoreRequest request = new UserStoreRequest(userName.getText(), userEmail.getText(), userPhone.getText());
        Router.go("users.store", request);
    }

}
