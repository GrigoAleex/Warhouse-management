package com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.actions;

import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.Models.Client;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.Models.Simulation;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.config.AppProvider;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.contracts.GeneratesNewClients;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.contracts.GeneratesRandomNumber;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.utils.LoaderManager;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.routing.Session;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.IntStream;

public class GenerateRandomClient implements GeneratesNewClients {
    public ArrayList<Client> handle(Integer count, Integer arrivalTimeMin, Integer arrivalTimeMax, Integer serviceTimeMin, Integer serviceTimeMax) {
        Session.set("loadingState", "0.0");
        ArrayList<Client> clients = new ArrayList<Client>();
        GeneratesRandomNumber generator = AppProvider.get(GeneratesRandomNumber.class);
        LoaderManager loader = AppProvider.get(LoaderManager.class);

        for (int i = 1; i <= count; i++) {
            clients.add(new Client(
                    i,
                    generator.handle(arrivalTimeMax, arrivalTimeMin, count / 2, (arrivalTimeMin + arrivalTimeMax) / 2),
                    generator.handle(serviceTimeMax, serviceTimeMin, count / 2, (serviceTimeMin + serviceTimeMax) / 2)
            ));
            loader.incrementLoading();
        }

        clients.sort(Comparator.comparing(Client::getArrivalTime));

        return clients;
    }

    private void updateLoadingProgress(Integer counter, Integer count) {
        double loadingState = (double)(counter/ count);
        Session.set("loadingState", Double.toString(loadingState));
    }
}
