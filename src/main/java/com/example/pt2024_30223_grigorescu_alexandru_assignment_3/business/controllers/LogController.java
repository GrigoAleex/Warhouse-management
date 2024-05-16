package com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.controllers;

import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.models.Log;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.models.Order;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.models.OrderProduct;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.models.Product;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.requests.OrderStoreRequest;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.routing.Router;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.routing.contracts.IController;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.data.daos.OrderDAO;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.data.daos.ProductDAO;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.data.daos.UserDAO;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.presentation.Window;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.presentation.exceptions.ViewNotFoundException;

public class LogController implements IController {
    public static void index() {
        try {
            Window.setView("logs");
        } catch (ViewNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(12);
        }
    }
}
