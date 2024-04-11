package com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.Models;

import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.config.AppProvider;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.contracts.GeneratesNewCashRegisters;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.contracts.GeneratesNewClients;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.utils.Logger;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

public class Simulation {
    private static final Simulation instance = new Simulation();
    private ArrayList<Client> clients;
    private ArrayList<CashRegister> registers;
    private Integer clientsToGenerate;
    private Integer queues;
    private Integer simulationTime;
    private Integer arrivalTimeMin;
    private Integer arrivalTimeMax;
    private Integer serviceTimeMin;
    private Integer serviceTimeMax;


    public static void setState(Integer clients, Integer queues, Integer simulationTime, Integer arrivalTimeMin, Integer arrivalTimeMax, Integer serviceTimeMin, Integer serviceTimeMax) {
        instance.clientsToGenerate = clients;
        instance.queues = queues;
        instance.simulationTime = simulationTime;
        instance.arrivalTimeMin = arrivalTimeMin;
        instance.arrivalTimeMax = arrivalTimeMax;
        instance.serviceTimeMin = serviceTimeMin;
        instance.serviceTimeMax = serviceTimeMax;
    }

    public static CompletableFuture<Void> start() {
        GeneratesNewClients clientGenerator = AppProvider.get(GeneratesNewClients.class);
        GeneratesNewCashRegisters registerGenerator = AppProvider.get(GeneratesNewCashRegisters.class);

        //        instance.clients = clientGenerator.handle(clients , arrivalTimeMin, arrivalTimeMax, serviceTimeMin, serviceTimeMax);
//        instance.registers = registerGenerator.handle(queues);
//
//        Logger logger = new Logger(instance.clients, instance.registers, simulationTime);
//        AppProvider.set(Logger.class, logger);
//        logger.generateLogs();

        CompletableFuture<Void> clientHandleFuture = CompletableFuture.runAsync(() -> {
            instance.clients = clientGenerator.handle(
                    instance.clientsToGenerate,
                    instance.arrivalTimeMin,
                    instance.arrivalTimeMax,
                    instance.serviceTimeMin,
                    instance.serviceTimeMax
            );
        });

        CompletableFuture<Void> registerHandleFuture = CompletableFuture.runAsync(() -> {
            instance.registers = registerGenerator.handle(instance.queues);
        });

        return clientHandleFuture.thenComposeAsync((Void) -> registerHandleFuture)
                .thenRunAsync(() -> {
                    Logger logger = new Logger(instance.clients, instance.registers, instance.simulationTime);
                    AppProvider.set(Logger.class, logger);
                    logger.generateLogs();
                });
    }
}
