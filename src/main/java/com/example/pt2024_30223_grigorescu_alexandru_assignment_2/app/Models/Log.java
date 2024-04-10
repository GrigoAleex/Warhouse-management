package com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.Models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

public class Log {
    private final Integer time;

    private ArrayList<Client> waitingClients = new ArrayList<>();

    private HashMap<Integer, Client> registers = new HashMap<>();

    public Log(Integer time) {
        this.time = time;
    }

    public void addWaitingClient(Collection<? extends Client> clients) {
        waitingClients.addAll(clients);
    }


    public void addRegister(Integer registerId, Client client) {
        registers.put(registerId, client);
    }

    public Integer getTime() {
        return time;
    }

    public ArrayList<Client> getWaitingClients() {
        return waitingClients;
    }

    public HashMap<Integer, Client> getRegisters() {
        return registers;
    }
}
