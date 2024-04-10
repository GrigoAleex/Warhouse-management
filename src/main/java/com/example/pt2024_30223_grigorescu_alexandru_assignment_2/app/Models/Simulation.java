package com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.Models;

import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.actions.GenerateRandomClient;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.config.AppProvider;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.contracts.GeneratesNewCashRegisters;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.contracts.GeneratesNewClients;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.utils.LoaderManager;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.utils.Logger;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.utils.SimulationManager;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.routing.Router;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Simulation {
    private ArrayList<Client> clients;
    private ArrayList<CashRegister> registers;
    private static final Simulation instance = new Simulation();

    public static void setState(Integer clients, Integer queues, Integer simulationTime, Integer arrivalTimeMin, Integer arrivalTimeMax, Integer serviceTimeMin, Integer serviceTimeMax) {

        GeneratesNewClients clientGenerator = AppProvider.get(GeneratesNewClients.class);
        GeneratesNewCashRegisters registerGenerator = AppProvider.get(GeneratesNewCashRegisters.class);

        instance.clients = clientGenerator.handle(clients , arrivalTimeMin, arrivalTimeMax, serviceTimeMin, serviceTimeMax);
        instance.registers = registerGenerator.handle(queues);

        Logger logger = new Logger(instance.clients, instance.registers, simulationTime);
        AppProvider.set(Logger.class, logger);
        logger.generateLogs();



    }
}
