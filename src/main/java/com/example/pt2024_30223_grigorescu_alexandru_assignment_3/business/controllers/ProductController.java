package com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.controllers;

import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.models.Product;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.models.User;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.requests.*;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.routing.Router;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.routing.Session;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.routing.contracts.IController;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.presentation.Window;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.presentation.exceptions.ViewNotFoundException;

public class ProductController implements IController {
    public static void index() {
        try {
            Window.setView("products/index");
        } catch (ViewNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(12);
        }
    }

    public static void create() {
        try {
            Window.setView("products/create");
        } catch (ViewNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(12);
        }
    }

    public static void store(ProductStoreRequest request) {
        Product product = new Product();

        product.setName(request.name());
        product.setStock(request.stock());
        product.setBarCode(request.barCode());

        product.create();
        Router.go("products.index");
    }

    public static void edit(ProductEditRequest request) {
        try {
            Session.set("productId", String.valueOf(request.productId()));
            Window.setView("products/edit");
        } catch (ViewNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(12);
        }
    }

    public static void update(ProductUpdateRequest request) {
        Product product = request.product();

        product.setName(request.name());
        product.setStock(request.stock());
        product.setBarCode(request.barCode());

        product.update();
        Router.go("products.index");
    }

    public static void delete(ProductDeleteRequest request) {
        request.product().delete();

        Router.go("products.index");
    }

}
