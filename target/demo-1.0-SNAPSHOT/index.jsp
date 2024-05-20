<%@ page import="com.example.demo.service.UserService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Location Example</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="hotel.js"> </script>
    <script>
        $(document).ready(function () {
            getLocation();

            $('#reservationButton').click(function() {
                var cnp = $('#cnp').val();
                if(cnp) {
                    window.location.href = "myreservations.jsp?cnp=" + cnp + "&idUser=" + 0;
                } else {
                    alert("Please enter your CNP.");
                }
            });
        });

        function getLocation() {
            if (navigator.geolocation) {
                navigator.geolocation.getCurrentPosition(showPosition);
            } else {
                console.log("Geolocation is not supported by this browser.");
            }
        }

        function showPosition(position) {
            var latitude = position.coords.latitude;
            var longitude = position.coords.longitude;

            $('#latitude').text("Latitude: " + latitude);
            $('#longitude').text("Longitude: " + longitude);

            $('#searchButton').click(function() {
                var radius = $('#radius').val(); // Preia valoarea introdusă de utilizator
                window.location.href = "hotel.jsp?latitude=" + latitude + "&longitude=" + longitude + "&radius=" + radius;
            });
        }
    </script>
</head>
<body>
<h1>Location Example</h1>
<div id="locationInfo">
    <p id="latitude"></p>
    <p id="longitude"></p>
</div>
<div>
    <label for="radius">Specify Radius (km): </label>
    <input type="number" id="radius" name="radius" min="1">
    <button id="searchButton">Search Hotels</button>
</div>
<div>
    <label for="cnp">CNP: </label>
    <input type="text" id="cnp" name="cnp" required>
    <button id="reservationButton">Check My Reservations</button>
</div>
</body>
</html>
