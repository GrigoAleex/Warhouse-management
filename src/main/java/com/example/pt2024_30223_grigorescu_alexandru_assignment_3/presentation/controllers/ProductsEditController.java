package com.example.pt2024_30223_grigorescu_alexandru_assignment_3.presentation.controllers;


import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.models.Product;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.requests.ProductDeleteRequest;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.requests.ProductStoreRequest;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.requests.ProductUpdateRequest;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.routing.Router;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.routing.Session;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.routing.contracts.IRequest;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.data.daos.ProductDAO;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.data.daos.UserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class ProductsEditController implements Initializable {

    private ImageView employeeImage;
    private Product product;

    @FXML
    private TextField stock;

    @FXML
    private Text employeeRole;

    @FXML
    private TextField productName;

    @FXML
    private TextField barCode;

    @FXML
    private Text breads;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        product = new ProductDAO().find(Integer.parseInt(Session.get("productId")));
        productName.setText(product.getName());
        barCode.setText(product.getBarCode());
        stock.setText(String.valueOf(product.getStock()));
        breads.setText("PRODUSE > " + product.getName().toUpperCase() + " > MODIFICARE");
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
    void update(ActionEvent event) {
        IRequest request = new ProductUpdateRequest(product, productName.getText(), Integer.parseInt(stock.getText()), barCode.getText());
        Router.go("products.update", request);
    }

    @FXML
    void delete(ActionEvent event) {
        IRequest request = new ProductDeleteRequest(product);
        Router.go("products.delete", request);
    }
}
