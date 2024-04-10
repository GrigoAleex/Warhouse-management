package com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.Models;

public class Client {
    private final Integer id;
    private final Integer arrivalTime;
    private Integer serviceTime;

    public Client(Integer id, Integer arrivalTime, Integer serviceTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
    }


    public Integer getId() {
        return id;
    }

    public Integer getArrivalTime() {
        return arrivalTime;
    }

    public Integer getServiceTime() {
        return serviceTime;
    }

    public int decreaseServiceTime() {
        return serviceTime -= 1;
    }
}
