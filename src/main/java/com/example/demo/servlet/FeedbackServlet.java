package com.example.demo.servlet;

import com.example.demo.service.FeedbackService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/addFeedback")
public class FeedbackServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int hotelId = Integer.parseInt(request.getParameter("hotelId"));
        String feedback = request.getParameter("feedback");
        int idUser = Integer.parseInt(request.getParameter("idUser"));
        FeedbackService feedbackService = new FeedbackService();
        feedbackService.addFeedback(hotelId,feedback);
        response.sendRedirect("myreservations.jsp?idUser=" + idUser);
    }
}
