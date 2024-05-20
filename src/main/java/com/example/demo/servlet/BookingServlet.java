package com.example.demo.servlet;

import com.example.demo.service.HotelService;
import com.example.demo.service.ReservationService;
import com.example.demo.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.simple.parser.ParseException;

import java.io.IOException;

@WebServlet("/booking")
public class BookingServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String paymentMethod = request.getParameter("paymentMethod");
        String lastName = request.getParameter("lastName");
        String cnp = request.getParameter("cnp");
        String checkInTime = request.getParameter("checkInTime");
        int hotelId = Integer.parseInt(request.getParameter("hotelId"));
        int roomNumber = Integer.parseInt(request.getParameter("roomNumber"));
        UserService userService = new UserService();
        HotelService hotelService = new HotelService();
        if(userService.getIdUserByCNP(cnp) == 0){
            userService.addUser(cnp,lastName);
        }
        try {
            ReservationService reservationService = new ReservationService();
            reservationService.AddReservation(userService.getIdUserByCNP(cnp),hotelId, roomNumber, paymentMethod, checkInTime,startDate,endDate);
            hotelService.updateAvailability(hotelId,roomNumber);
        } catch (ParseException e) {
            System.out.println(e);
        }
        response.sendRedirect("myreservations.jsp?idUser=" + userService.getIdUserByCNP(cnp));
    }
}
