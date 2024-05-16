package com.example.pt2024_30223_grigorescu_alexandru_assignment_3.presentation.controllers;


import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.models.User;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.requests.UserStoreRequest;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.requests.UserUpdateRequest;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.routing.Router;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.routing.Session;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.routing.contracts.IRequest;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.data.daos.UserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class UsersEditController implements Initializable {
    private User user;

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
    private Text breads;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        user = new UserDAO().find(Integer.parseInt(Session.get("userId")));
        userName.setText(user.getName());
        userEmail.setText(user.getEmail());
        userPhone.setText(user.getPhoneNumber());
        breads.setText("CLIENȚI > " + user.getName().toUpperCase() + " > MODIFICARE");
    }

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
        IRequest request = new UserUpdateRequest(user, userName.getText(), userEmail.getText(), userPhone.getText());
        Router.go("users.update", request);
    }

}
