package com.example.pt2024_30223_grigorescu_alexandru_assignment_3.presentation.controllers;


import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.requests.ProductStoreRequest;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.requests.UserStoreRequest;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.routing.Router;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.routing.contracts.IRequest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class ProductsCreateController {

    private ImageView employeeImage;

    @FXML
    private TextField stock;

    @FXML
    private Text employeeRole;

    @FXML
    private TextField productName;

    @FXML
    private TextField barCode;

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
        IRequest request = new ProductStoreRequest(productName.getText(), Integer.parseInt(stock.getText()), barCode.getText());
        Router.go("products.store", request);
    }
}
