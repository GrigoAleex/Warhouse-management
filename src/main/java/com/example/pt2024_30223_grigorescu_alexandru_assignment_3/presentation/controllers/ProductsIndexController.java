package com.example.pt2024_30223_grigorescu_alexandru_assignment_3.presentation.controllers;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.models.Product;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.models.User;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.requests.UserEditRequest;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.routing.Router;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.data.daos.ProductDAO;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.data.daos.UserDAO;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.presentation.components.ProductComponent;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Callback;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
public class ProductsIndexController implements Initializable{

    @FXML
    private Text employeeName;

    @FXML
    private ImageView employeeImage;

    @FXML
    private TilePane grid;

    @FXML
    private TextField stock;

    @FXML
    private Text employeeRole;

    @FXML
    private TextField productName;

    @FXML
    private TextField barCode;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<Product> products = new ProductDAO().all();
        for (Product product: products) {
            try {
                VBox component = new ProductComponent(product);
                TilePane.setMargin(component, new Insets(19, 23, 19, 23));
                grid.getChildren().add(component);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
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
    void createProduct(ActionEvent event) {
        Router.go("products.create");
    }
}