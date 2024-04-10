package com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.Models;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class CashRegister {
    private Client currentClient;
    private final Integer id;
    private final LinkedBlockingQueue<Client> queue = new LinkedBlockingQueue<>();
    private final AtomicInteger waitingTime = new AtomicInteger(0);

    public CashRegister(Integer id) {
        this.id = id;
    }

    public void addClient(Client client) {
        queue.add(client);
        waitingTime.addAndGet(client.getServiceTime());
    }
    public LinkedBlockingQueue<Client> getQueue() { return queue; }

    public Client getCurrentClient() {
        return currentClient;
    }

    public void processClient() {
        if (currentClient == null && queue.isEmpty()) return;
        if (currentClient == null || currentClient.decreaseServiceTime() == 0)
            currentClient = queue.poll();

        waitingTime.set(Integer.max(waitingTime.get() - 1, 0));
    }

    public Integer getWaitingTime() {
        return waitingTime.get();
    }

    public Integer getId() {
        return id;
    }
}
