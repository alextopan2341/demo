<%@ page import="java.io.*, java.util.*, org.json.simple.*, org.json.simple.parser.*" %>
<%@ page import="com.example.demo.servlet.RoomManager" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String hotelName = request.getParameter("hotelName");
    String roomNumber = request.getParameter("roomNumber");
    String roomType = request.getParameter("roomType");
    String price = request.getParameter("price");

    try {
        RoomManager.updateRoomAvailability(hotelName, roomNumber);
    } catch (ParseException e) {
        throw new RuntimeException(e);
    }
%>
<html>
<head>
    <title>Room Reservation</title>
</head>
<body>
<h1>Room Reservation</h1>

<h2>Hotel: <%= hotelName %></h2>
<p>Room Number: <%= roomNumber %></p>
<p>Room Type: <%= roomType %></p>
<p>Price: <%= price %></p>

<form action="booking" method="post">
    <label for="startDate">Start Date:</label>
    <input type="date" id="startDate" name="startDate" required><br><br>

    <label for="endDate">End Date:</label>
    <input type="date" id="endDate" name="endDate" required><br><br>

    <label for="checkInTime">Check in time:</label>
    <input type="text" id="checkInTime" name="checkInTime" required><br><br>

    <label for="paymentMethod">Payment Method:</label>
    <select id="paymentMethod" name="paymentMethod" required>
        <option value="cash">Cash</option>
        <option value="card">Card</option>
    </select><br><br>

    <label for="lastName">Last Name:</label>
    <input type="text" id="lastName" name="lastName" required><br><br>

    <label for="cnp">CNP:</label>
    <input type="text" id="cnp" name="cnp" required><br><br>

    <input type="hidden" id="hotelId" name="hotelId" value="<%= request.getParameter("hotelId") %>">
    <input type="hidden" id="roomNumber" name="roomNumber" value="<%= roomNumber %>">

    <input type="submit" value="Confirm Booking">
</form>

</body>
</html>
