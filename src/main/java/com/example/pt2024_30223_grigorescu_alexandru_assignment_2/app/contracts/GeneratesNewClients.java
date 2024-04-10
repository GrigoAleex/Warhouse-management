package com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.contracts;

import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.Models.Client;

import java.util.ArrayList;

public interface GeneratesNewClients {
    public ArrayList<Client> handle(Integer count, Integer arrivalTimeMin, Integer arrivalTimeMax, Integer serviceTimeMin, Integer serviceTimeMax);
}
