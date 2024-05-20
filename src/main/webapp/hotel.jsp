<%@ page import="java.io.*, java.util.*, org.json.simple.*, org.json.simple.parser.*" %>
<%@ page import="com.example.demo.service.FeedbackService" %>
<%@ page import="com.example.demo.models.Feedback" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hotel Search Results</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid black;
            padding: 8px;
            text-align: left;
        }
    </style>
</head>
<body>
<h1>Hotel Search Results</h1>

<p>Latitude: <%= request.getParameter("latitude") %></p>
<p>Longitude: <%= request.getParameter("longitude") %></p>
<p>Radius: <%= request.getParameter("radius") %> km</p>

<%
    double userLatitude = Double.parseDouble(request.getParameter("latitude"));
    double userLongitude = Double.parseDouble(request.getParameter("longitude"));
    double radius = Double.parseDouble(request.getParameter("radius"));

    JSONParser parser = new JSONParser();
    JSONArray hotelsArray = null;
    try {
        hotelsArray = (JSONArray) parser.parse(new FileReader("/Users/alexandrutopanfarcau/Documents/GitHub/demo/src/main/webapp/hotel.json"));
    } catch (ParseException e) {
        throw new RuntimeException(e);
    }

    JSONArray nearbyHotels = new JSONArray();

    for (Object obj : hotelsArray) {
        JSONObject hotel = (JSONObject) obj;
        double hotelLatitude = (double) hotel.get("latitude");
        double hotelLongitude = (double) hotel.get("longitude");

        double distance = haversine(userLatitude, userLongitude, hotelLatitude, hotelLongitude);

        if (distance <= radius) {
            nearbyHotels.add(hotel);
        }
    }

    FeedbackService feedbackService = new FeedbackService();
%>

<table>
    <tr>
        <th>Name</th>
        <th>Latitude</th>
        <th>Longitude</th>
        <th>Rooms</th>
        <th>Feedbacks</th>
    </tr>
    <% for (Object obj : nearbyHotels) {
        JSONObject hotel = (JSONObject) obj;
        int hotelId = ((Long) hotel.get("id")).intValue();
        List<Feedback> feedbacks = feedbackService.getAllFeedbacksByHotelId(hotelId);
    %>
    <tr>
        <td><%= hotel.get("name") %></td>
        <td><%= hotel.get("latitude") %></td>
        <td><%= hotel.get("longitude") %></td>
        <td>
            <ul>
                <% JSONArray rooms = (JSONArray) hotel.get("rooms");
                    for (Object roomObj : rooms) {
                        JSONObject room = (JSONObject) roomObj;
                        String type = "";
                        int roomType = ((Long) room.get("type")).intValue();
                        switch (roomType) {
                            case 1:
                                type = "Single Room";
                                break;
                            case 2:
                                type = "Double Room";
                                break;
                            case 3:
                                type = "Suite Room";
                                break;
                            case 4:
                                type = "Matrimonial Room";
                                break;
                            default:
                                type = "Unknown";
                        }
                %>
                <li>Room Number: <%= room.get("roomNumber") %>, Type: <%= type %>, Price: <%= room.get("price") %>
                    <% if ((boolean) room.get("isAvailable")) { %>
                    <a href="book.jsp?hotelName=<%= hotel.get("name") %>&roomNumber=<%= room.get("roomNumber") %>&hotelId=<%= hotel.get("id") %>"><button>Make a reservation</button></a>
                    <% } %>
                </li>
                <% } %>
            </ul>
        </td>
        <td>
            <ul>
                <% for (Feedback feedback : feedbacks) { %>
                <li><%= feedback.getFeedback() %></li>
                <% } %>
            </ul>
        </td>
    </tr>
    <% } %>
</table>
<%!
    public double haversine(double lat1, double lon1, double lat2, double lon2) {
        double R = 6371;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c;
        return distance;
    }
%>
</body>
</html>
