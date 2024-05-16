package com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.controllers;

import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.models.Log;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.models.Order;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.models.OrderProduct;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.models.Product;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.requests.*;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.routing.Router;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.routing.Session;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.routing.contracts.IController;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.data.daos.OrderDAO;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.data.daos.ProductDAO;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.data.daos.UserDAO;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.presentation.Window;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.presentation.exceptions.ViewNotFoundException;

public class OrderController implements IController {
    public static void index() {
        try {
            Window.setView("orders/index");
        } catch (ViewNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(12);
        }
    }

    public static void create() {
        try {
            Window.setView("orders/create");
        } catch (ViewNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(12);
        }
    }

    public static void store(OrderStoreRequest request) {
        Order order = new Order();

        order.setStatus(request.status());
        order.setUserId(request.userId());

        order.create();

        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setOrderId(getLatestOrder());
        orderProduct.setProductId(request.productId());
        orderProduct.create();

        Product product = new ProductDAO().find(request.productId());
        System.out.println(product.getId());

        product.setStock(product.getStock() - request.quantity());
        product.update();

        System.out.println(product.getId());

        Log log = new Log();
        log.setAction(new UserDAO().find(request.userId()).getName() + " a cumparat " + request.quantity() + " " + product.getName());
        log.create();

        Router.go("orders.index");
    }

    private static Integer getLatestOrder() {
        return new OrderDAO().all().getLast().getId();
    }
}
