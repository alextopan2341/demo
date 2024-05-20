$(document).ready(function () {
    getLocation();
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
        var radius = $('#radius').val();
        window.location.href = "hotel.jsp?latitude=" + latitude + "&longitude=" + longitude + "&radius=" + radius;
    });
}

