package com.example.demo.repository;

import com.example.demo.models.Reservation;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReservationRepository {
    private static final String FILE_PATH = "/Users/alexandrutopanfarcau/Documents/GitHub/demo/src/main/webapp/reservations.json";

    private final List<Reservation> reservations;

    public ReservationRepository() throws IOException {
        reservations = readFromFile();
    }

    public void saveReservations() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File(FILE_PATH), reservations);
    }

    public List<Reservation> getReservationsByIdUser(int userId){
        List<Reservation> myReservations = new ArrayList<>();
        for(Reservation reservation:reservations){
            if(reservation.getUserId() == userId){
                myReservations.add(reservation);
            }
        }
        return myReservations;

    }

    public void deleteReservation(Reservation reservation) throws IOException {
        reservations.remove(reservation);
        saveReservations();
    }
    public Reservation getReservationById(int id){
        for(Reservation reservation: reservations){
            if(reservation.getId() == id){
                return reservation;
            }
        }
        return null;
    }

    public void addReservation(Reservation reservation) throws IOException {
        reservation.setId(nextIdReservation());
        reservations.add(reservation);
        saveReservations();
    }

    private int nextIdReservation(){
        return reservations.size() +1;
    }

    private static List<Reservation> readFromFile() throws IOException{
        File file = new File(FILE_PATH);
        if (file.exists() && file.length() == 0) {
            return new ArrayList<>();
        }

        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(file, new TypeReference<List<Reservation>>() {});
    }

    public List<Reservation> getAllReservations() {
        return  reservations;
    }
}
