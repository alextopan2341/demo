package com.example.demo.models;

public class Feedback {
    private int id;
    private int hotelId;
    private String feedback;

    public Feedback(int id, int hotelId, String feedback) {
        this.id = id;
        this.hotelId = hotelId;
        this.feedback = feedback;
    }
    public Feedback(int hotelId, String feedback) {
        this.hotelId = hotelId;
        this.feedback = feedback;
    }

    public Feedback() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
