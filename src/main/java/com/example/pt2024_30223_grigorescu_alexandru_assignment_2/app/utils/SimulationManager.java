package com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.utils;

import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.Models.CashRegister;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.Models.Client;

import java.util.ArrayList;

public class SimulationManager {
    private final ArrayList<Client> clients;
    private final ArrayList<CashRegister> registers;

    public SimulationManager(ArrayList<Client> clients, ArrayList<CashRegister> registers) {
        this.clients = clients;
        this.registers = registers;
    }

    public void dispatchClientsToRegisters(Integer time) {
        for (Client client : filterClientsByArrivalTime(clients, time)) {
            CashRegister selectedRegister = registers.getFirst();
            int minWaitingTime = Integer.MAX_VALUE;

            for (CashRegister register : registers) {
                int waitingTime = register.getWaitingTime();

                if (waitingTime < minWaitingTime) {
                    minWaitingTime = waitingTime;
                    selectedRegister = register;
                } else if (waitingTime == minWaitingTime && register.getQueue().size() < selectedRegister.getQueue().size()) {
                    selectedRegister = register;
                }
            }

            selectedRegister.addClient(client);
        }
    }

    private ArrayList<Client> filterClientsByArrivalTime(ArrayList<Client> clients, int time) {
        ArrayList<Client> filteredClients = new ArrayList<>();

        for (Client client : clients) {
            if (client.getArrivalTime() == time) {
                filteredClients.add(client);
            }
        }

        return filteredClients;
    }
}
