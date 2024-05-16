package com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.models;

import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.data.daos.UserDAO;

import java.time.LocalDate;
import java.util.Date;

public class Order extends Model{
    @Column(name="id", type="Int")
    private int id = -1;

    @Column(name="user_id", type="Int")
    private Integer userId;

    @Column(name="status", type="String")
    private String status = "UNPAID";

    @Column(name="created_at", type="Date")
    private final LocalDate created_at = LocalDate.now();

    @Column(name="updated_at", type="Date")
    private final LocalDate updated_at = LocalDate.now();

    public Order() {}

    public Order(Integer userId, String status) {
        this.userId = userId;
        this.status = status;
    }

    public LocalDate getUpdated_at() {
        return updated_at;
    }

    public LocalDate getCreated_at() {
        return created_at;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public User user() {
        return new UserDAO().find(this.userId);
    }

    public int getId() {
        return id;
    }
}
