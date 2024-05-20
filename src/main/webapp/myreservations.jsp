<%@ page import="com.example.demo.service.ReservationService" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.demo.models.Reservation" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.demo.service.HotelService" %>
<%@ page import="com.example.demo.models.Hotel" %>
<%@ page import="com.example.demo.models.User" %>
<%@ page import="com.example.demo.service.UserService" %>
<%@ page import="com.example.demo.service.FeedbackService" %>
<%@ page import="java.time.LocalTime" %>
<%@ page import="java.time.LocalDate" %>
<%
    int idUser = Integer.parseInt(request.getParameter("idUser"));
    String cnp = request.getParameter("cnp");

    if (idUser == 0) {
        UserService userService = new UserService();
        idUser = userService.getIdUserByCNP(cnp);
    }

    ReservationService reservationService = new ReservationService();
    HotelService hotelService = new HotelService();
    FeedbackService feedbackService = new FeedbackService();

    List<Reservation> myReservations = reservationService.getReservationsByUserId(idUser);
%>
<!DOCTYPE html>
<html>
<head>
    <title>Rezervarile Mele</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid black;
        }
        th, td {
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
<h1>Rezervarile Mele</h1>

<%
    if (myReservations == null || myReservations.isEmpty()) {
%>
<p>Nu aveți rezervari.</p>
<%
} else {
%>
<table>
    <thead>
    <tr>
        <th>Hotel</th>
        <th>Numar Camera</th>
        <th>Data Check-in</th>
        <th>Data Check-out</th>
        <th>Ora Check-in</th>
        <th>Pret</th>
        <th>Actiuni</th>
    </tr>
    </thead>
    <tbody>
    <%
        for (Reservation reservation : myReservations) {
            Hotel hotel = hotelService.getHotelById(reservation.getHotelId());
            LocalTime localTime = LocalTime.now().plusHours(2);
            LocalTime checkInHour = LocalTime.parse(reservation.getCheckInTime());
            LocalDate localDate = LocalDate.now();
            LocalDate checkInDate = LocalDate.parse(reservation.getStartDate());
            int differenceHour, differenceMinutes, differenceDate;
            if (localTime.isBefore(checkInHour) || localDate.isBefore(checkInDate)) {
                differenceDate = Math.abs((checkInDate.getDayOfMonth()-localDate.getDayOfMonth()));
                differenceHour = Math.abs(checkInHour.getHour() - localTime.getHour());
                differenceMinutes = Math.abs(checkInHour.getMinute() - localTime.getMinute());
            } else {
                differenceHour = 0;
                differenceMinutes = 0;
                differenceDate = 0;
            }
    %>
    <tr>
        <td><%= hotelService.getHotelById(hotel.getId()).getName() %></td>
        <td><%= reservation.getRoomNumber() %></td>
        <td><%= reservation.getStartDate() %></td>
        <td><%= reservation.getEndDate() %></td>
        <td><%= reservation.getCheckInTime() %></td>
        <td><%= hotelService.getRoomByNumber(hotel.getId(), reservation.getRoomNumber()).getPrice() %></td>
        <td>
            <% if (differenceHour > 0 || differenceMinutes > 0 || differenceDate>0) { %>
            <form action="deleteReservation" method="post" style="display:inline;">
                <input type="hidden" name="reservationId" value="<%= reservation.getId() %>">
                <button type="submit">Delete</button>
            </form>
            <% } else { %>
            <form action="addFeedback" method="post" style="display:inline;">
                <input type="hidden" name="hotelId" value="<%= reservation.getHotelId() %>">
                <input type="hidden" name="idUser" value="<%= idUser %>">
                <textarea name="feedback" placeholder="Leave your feedback here"></textarea>
                <button type="submit">Submit Feedback</button>
            </form>
            <% } %>
        </td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>
<%
    }
%>
</body>
</html>
