package com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.controllers;

import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.models.User;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.requests.UserDeleteRequest;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.requests.UserEditRequest;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.requests.UserStoreRequest;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.requests.UserUpdateRequest;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.routing.Route;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.routing.Router;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.routing.Session;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.routing.contracts.IController;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.presentation.Window;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.presentation.exceptions.ViewNotFoundException;

public class UserController implements IController {
    public static void index() {
        try {
            Window.setView("users/index");
        } catch (ViewNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(12);
        }
    }

    public static void create() {
        try {
            Window.setView("users/create");
        } catch (ViewNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(12);
        }
    }

    public static void store(UserStoreRequest request) {
        User user = new User();

        user.setName(request.name());
        user.setEmail(request.email());
        user.setPhoneNumber(request.phoneNumber());

        user.create();
        Router.go("users.index");
    }

    public static void edit(UserEditRequest request) {
        try {
            Session.set("userId", String.valueOf(request.userId()));
            Window.setView("users/edit");
        } catch (ViewNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(12);
        }
    }

    public static void update(UserUpdateRequest request) {
        User user = request.user();

        user.setName(request.name());
        user.setEmail(request.email());
        user.setPhoneNumber(request.phoneNumber());

        user.update();
        Router.go("users.index");
    }

    public static void delete(UserDeleteRequest request) {
        request.user().delete();
    }

}
