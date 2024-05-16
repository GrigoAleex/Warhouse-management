package com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.models;

import java.util.ArrayList;

public class User extends Model{
    @Column(name="id", type="Int")
    private int id = -1;

    @Column(name="name", type="String")
    private String name;

    @Column(name="phone_number", type="String")
    private String phoneNumber;

    @Column(name="email", type="String")
    private String email;

    @Column(name="avatar", type="String")
    private String avatar = "/avatars/default.png";

    public User() {}

    public User(String name, String phoneNumber, String email, String avatar) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.avatar = avatar;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
