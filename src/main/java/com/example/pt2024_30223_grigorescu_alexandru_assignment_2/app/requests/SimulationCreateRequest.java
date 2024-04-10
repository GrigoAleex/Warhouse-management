package com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.requests;

import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.routing.Session;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.routing.contracts.IRequest;

public class SimulationCreateRequest implements IRequest {
    private final Integer clients;
    private final Integer queues;
    private final Integer simulationTime;
    private final Integer arrivalTimeMin;
    private final Integer arrivalTimeMax;
    private final Integer serviceTimeMin;
    private final Integer serviceTimeMax;

    public SimulationCreateRequest(Integer clients, Integer queues, Integer simulationTime, Integer arrivalTimeMin, Integer arrivalTimeMax, Integer serviceTimeMin, Integer serviceTimeMax) {
        this.clients = clients;
        this.queues = queues;
        this.simulationTime = simulationTime;
        this.arrivalTimeMin = arrivalTimeMin;
        this.arrivalTimeMax = arrivalTimeMax;
        this.serviceTimeMin = serviceTimeMin;
        this.serviceTimeMax = serviceTimeMax;
    }

    public Integer getServiceTimeMax() {
        return serviceTimeMax;
    }

    public Integer getClients() {
        return clients;
    }

    public Integer getQueues() {
        return queues;
    }

    public Integer getSimulationTime() {
        return simulationTime;
    }

    public Integer getArrivalTimeMin() {
        return arrivalTimeMin;
    }

    public Integer getArrivalTimeMax() {
        return arrivalTimeMax;
    }

    public Integer getServiceTimeMin() {
        return serviceTimeMin;
    }
}
