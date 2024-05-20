package com.example.demo.models;

public class User {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    private String CNP;

    private String lastName;

    public User(String CNP, String lastName) {
        this.CNP = CNP;
        this.lastName = lastName;
    }
    public User() {
    }

    public User(int id, String CNP, String lastName) {
        this.id = id;
        this.CNP = CNP;
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCNP() {
        return CNP;
    }

    public void setCNP(String CNP) {
        this.CNP = CNP;
    }
}
