package com.example.demo.service;

import com.example.demo.models.Reservation;
import com.example.demo.repository.ReservationRepository;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.List;

public class ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationService() throws IOException, ParseException {
        this.reservationRepository = new ReservationRepository();
    }

    public void AddReservation(int userId, int hotelId, int roomNumber, String paymentMethod, String checkInTime, String startDate, String endDate) throws IOException, ParseException {
        Reservation reservation = new Reservation(userId, hotelId, roomNumber, paymentMethod, checkInTime, startDate, endDate);
        reservationRepository.addReservation(reservation);
    }
    public void deleteReservation(int id) throws IOException {
        Reservation reservation = reservationRepository.getReservationById(id);
        reservationRepository.deleteReservation(reservation);
    }

    public Reservation getReservationById(int id){
        return reservationRepository.getReservationById(id);
    }

    public List<Reservation> getReservationsByUserId(int userId){
        return reservationRepository.getReservationsByIdUser(userId);
    }
    public List<Reservation> getAllReservations() {
        return reservationRepository.getAllReservations();
    }
}
