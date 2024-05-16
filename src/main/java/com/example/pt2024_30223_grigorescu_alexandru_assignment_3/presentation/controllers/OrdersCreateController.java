package com.example.pt2024_30223_grigorescu_alexandru_assignment_3.presentation.controllers;


import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.models.Product;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.requests.OrderStoreRequest;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.routing.Router;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.routing.contracts.IRequest;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.data.daos.ProductDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class OrdersCreateController {

    @FXML
    private Text employeeName;

    @FXML
    private ImageView employeeImage;

    @FXML
    private Text employeeRole;

    @FXML
    private TextField quantity;

    @FXML
    private TextField productId;

    @FXML
    private Text breads;

    @FXML
    private Text error;

    @FXML
    private TextField userId;

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
        Product product = new ProductDAO().find(Integer.parseInt(productId.getText()));
        if (product.getStock() < Integer.parseInt(quantity.getText())) {
            error.setText("Cantitatea este prea mare! Maxim " + product.getStock() + " bucati!");
            return;
        };

        IRequest request = new OrderStoreRequest(
                Integer.parseInt(userId.getText()),
                "UNPAID",
                Integer.parseInt(productId.getText()),
                Integer.parseInt(quantity.getText())
        );
        Router.go("orders.store", request);
    }
}
