package com.example.demo.servlet;

import com.example.demo.models.Reservation;
import com.example.demo.service.HotelService;
import com.example.demo.service.ReservationService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.simple.parser.ParseException;

import java.io.IOException;

@WebServlet("/deleteReservation")
public class DeleteReservationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            HotelService hotelService = new HotelService();
            ReservationService reservationService = new ReservationService();
            int id = Integer.parseInt(request.getParameter("reservationId"));
            Reservation reservation = reservationService.getReservationById(id);
            hotelService.updateAvailability(reservation.getHotelId(),reservation.getRoomNumber());
            reservationService.deleteReservation(id);

            response.sendRedirect("index.jsp");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
