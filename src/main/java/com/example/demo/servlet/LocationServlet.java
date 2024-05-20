package com.example.demo.servlet;

import com.example.demo.models.User;
import com.example.demo.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/location")
public class LocationServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String latitude = request.getParameter("latitude");
        String longitude = request.getParameter("longitude");
        String cnp = request.getParameter("cnp");
        UserService userService = new UserService();
        int userId = userService.getIdUserByCNP(cnp);
        System.out.println("Latitude: " + latitude + ", Longitude: " + longitude);

        response.setContentType("application/json");

        String jsonResponse = "{\"latitude\": \"" + latitude + "\", \"longitude\": \"" + longitude + "\"}";

        response.getWriter().write(jsonResponse);
    }
}
