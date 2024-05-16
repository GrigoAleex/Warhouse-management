package com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.config;

import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.controllers.LogController;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.controllers.OrderController;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.controllers.ProductController;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.controllers.UserController;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.routing.Route;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.routing.Router;

public class ConfigLoader {
    public static void handle() {
        Router.add(new Route("users.index", UserController.class, "index"));
        Router.add(new Route("users.create", UserController.class, "create"));
        Router.add(new Route("users.store", UserController.class, "store"));
        Router.add(new Route("users.edit", UserController.class, "edit"));
        Router.add(new Route("users.update", UserController.class, "update"));
        Router.add(new Route("users.delete", UserController.class, "delete"));

        Router.add(new Route("products.index", ProductController.class, "index"));
        Router.add(new Route("products.create", ProductController.class, "create"));
        Router.add(new Route("products.store", ProductController.class, "store"));
        Router.add(new Route("products.edit", ProductController.class, "edit"));
        Router.add(new Route("products.update", ProductController.class, "update"));
        Router.add(new Route("products.delete", ProductController.class, "delete"));

        Router.add(new Route("orders.index", OrderController.class, "index"));
        Router.add(new Route("orders.create", OrderController.class, "create"));
        Router.add(new Route("orders.store", OrderController.class, "store"));

        Router.add(new Route("logs.index", LogController.class, "index"));
    }
}
