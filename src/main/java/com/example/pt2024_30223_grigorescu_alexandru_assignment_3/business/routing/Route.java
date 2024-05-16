package com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.routing;

import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.routing.contracts.IController;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.routing.contracts.IControllerRunner;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.routing.contracts.IRequest;

public class Route {
    private final String name;
    private final IControllerRunner controller;

    public Route(String name, Class<? extends IController> controller, String action) {
        this.name = name;
        this.controller = new ControllerRunner(controller, action);
    }

    public String getName() { return name; }

    public void execute(IRequest request) {
        controller.run(request);
    }
}
