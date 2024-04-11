package com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.controllers;

import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.Models.Simulation;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.config.AppProvider;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.requests.SimulationCreateRequest;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.exceptions.ValidationException;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.utils.LoaderManager;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.routing.Router;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.routing.Session;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.routing.contracts.IController;

public class SimulationController implements IController {
    public static void store(SimulationCreateRequest request) {
        try {
            validate(request);
        } catch (ValidationException e) {
            Session.set("error", e.getMessage());
            Router.go("start");
            return;
        }
        AppProvider.set(LoaderManager.class, new LoaderManager(request.getClients() + request.getQueues()));


        Simulation.setState(
                request.getClients(),
                request.getQueues(),
                request.getSimulationTime(),
                request.getArrivalTimeMin(),
                request.getArrivalTimeMax(),
                request.getServiceTimeMin(),
                request.getServiceTimeMax()
        );

        Router.go("loading");
    }

    private static void validate(SimulationCreateRequest request) throws ValidationException {
        if (request.getClients() <= 0) throw new ValidationException("Number of clients must be bigger or equal to 1");
        if (request.getQueues() <= 0) throw new ValidationException("Number of queues must be bigger or equal to 1");
        if (request.getSimulationTime() <= 0) throw new ValidationException("Simulation time must be bigger or equal to 1");
        if (request.getArrivalTimeMin() < 0) throw new ValidationException("Minimum arrival time must be bigger or equal to 0");
        if (request.getArrivalTimeMax() <= 0) throw new ValidationException("Maximum arrival time must be bigger or equal to 1");
        if (request.getArrivalTimeMin() > request.getArrivalTimeMax()) throw new ValidationException("Maximum arrival time must be bigger or equal to minimum arrival time");
        if (request.getServiceTimeMin() <= 0) throw new ValidationException("Minimum service time must be bigger or equal to 1");
        if (request.getServiceTimeMax() <= 0) throw new ValidationException("Maximum service time must be bigger or equal to 1");
        if (request.getServiceTimeMin() > request.getServiceTimeMax()) throw new ValidationException("Maximum service time must be bigger or equal to minimum service time");
    }
}
