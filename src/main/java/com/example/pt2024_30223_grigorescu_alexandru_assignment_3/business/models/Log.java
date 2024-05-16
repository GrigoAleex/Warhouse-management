package com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.models;

import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.data.daos.UserDAO;

import java.time.LocalDate;

public class Log extends Model{
    @Column(name="id", type="Int")
    private int id = -1;

    @Column(name="action", type="String")
    private String action;

    @Column(name = "created_at", type = "Date")
    private LocalDate created_at = LocalDate.now();

    public Log() {}
    public int getId() {
        return id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public LocalDate getCreated_at() {
        return created_at;
    }
}
