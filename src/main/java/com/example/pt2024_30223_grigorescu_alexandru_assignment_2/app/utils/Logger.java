package com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.utils;

import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.Models.CashRegister;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.Models.Client;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.Models.Log;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Map;

public class Logger {
    private final ArrayList<Client> clients;
    private final ArrayList<CashRegister> registers;
    private final Integer timeLimit;
    public String logsString = "";

    private ArrayList<Log> logs = new ArrayList<>();

    public Logger(ArrayList<Client> clients, ArrayList<CashRegister> registers, Integer timeLimit) {
        this.clients = clients;
        this.registers = registers;
        this.timeLimit = timeLimit;
    }

    public String getAverageWaitingTime() {
        return  "0";
    }

    public String gerAverageServiceTime() {
        int sum = 0;

        for (Client client : clients.stream().filter(client -> client.getArrivalTime() < timeLimit).toList()) {
            sum += client.getServiceTime();
        }

        return Double.toString((double) sum / logs.size());
    }

    //TODO: is Wrong
    public String getPeakHour() {
        int peakHour = -1, maxClients = 0;

        int time = 1;
        for (Log log : logs) {
            int clients = log.getWaitingClients().size();
            for (Map.Entry<Integer, Client> entry : log.getRegisters().entrySet()) {
                clients += entry.getValue() != null ? 1 : 0;
            }
            if (clients > maxClients) {
                maxClients =clients;
                peakHour = time;
            }
        }

        return Integer.toString(peakHour);
    }

    public void generateLogs() {
        String time = String.valueOf(System.currentTimeMillis());
        for (int i = 1; i <= timeLimit; i++) {
            new SimulationManager(clients, registers).dispatchClientsToRegisters(i);

            Log log = new Log(i);
            for (CashRegister register: registers) {
                register.processClient();
                log.addRegister(register.getId(), register.getCurrentClient());
                log.addWaitingClient(register.getQueue().stream().toList());
            }
            logs.add(log);

            String filename = "output_" + time + ".txt";

            try {
                PrintWriter writer = new PrintWriter(new FileWriter(filename, true));
                StringWriter out = new StringWriter();
                PrintWriter  stringWriter = new PrintWriter(out);

                writer.println("Time " + i);
                stringWriter.println("Time " + i);
                writer.print("Waiting clients: ");
                stringWriter.print("Waiting clients: ");
                for (Client client : log.getWaitingClients()) {
                    writer.print("(" + client.getId() + ", " + client.getArrivalTime() + ", " +client.getServiceTime() + "), ");
                    stringWriter.print("(" + client.getId() + ", " + client.getArrivalTime() + ", " +client.getServiceTime() + "), ");
                }

                writer.println("");
                stringWriter.println("");
                for (Map.Entry<Integer, Client> entry : log.getRegisters().entrySet()) {
                    Client client = entry.getValue();
                    writer.print("Cash register " + entry.getKey() + ": ");
                    stringWriter.print("Cash register " + entry.getKey() + ": ");
                    if (client != null) {
                        writer.println("(" + client.getId() + ", " + client.getArrivalTime() + ", " +client.getServiceTime() + ")");
                        stringWriter.println("(" + client.getId() + ", " + client.getArrivalTime() + ", " +client.getServiceTime() + ")");
                    } else {
                        writer.println("empty");
                        stringWriter.println("empty");
                    }
                }
                stringWriter.flush();
                logsString = logsString + out.toString();

                writer.close();
                System.out.println("Data written to " + filename);
            } catch (IOException e) {
                System.err.println("Error writing to " + filename);
                e.printStackTrace();
            }
        }
    }
}
