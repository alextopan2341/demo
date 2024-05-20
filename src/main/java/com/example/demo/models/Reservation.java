package com.example.demo.models;

public class Reservation {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private long userId;
    private int hotelId;
    private int roomNumber;
    private String paymentMethod;

    private String checkInTime;

    private String startDate;
    private String endDate;

    public Reservation() {
    }
    public Reservation(int id, long userId, int hotelId, int roomNumber, String paymentMethod, String checkInTime,String startDate,String endDate) {
        this.id = id;
        this.userId = userId;
        this.hotelId = hotelId;
        this.roomNumber = roomNumber;
        this.paymentMethod = paymentMethod;
        this.checkInTime = checkInTime;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Reservation(long userId, int hotelId, int roomNumber, String paymentMethod, String checkInTime,String startDate,String endDate) {
        this.id = 1;
        this.userId = userId;
        this.hotelId = hotelId;
        this.roomNumber = roomNumber;
        this.paymentMethod = paymentMethod;
        this.checkInTime = checkInTime;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getCheckInTime(){
        return checkInTime;
    }

    public void setCheckInTime(String checkInTime) {
        this.checkInTime = checkInTime;
    }
}
