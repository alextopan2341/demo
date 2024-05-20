package com.example.demo.servlet;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class RoomManager {

    public static void updateRoomAvailability(String hotelName, String roomNumber) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        JSONArray hotelsArray = (JSONArray) parser.parse(new FileReader("/Users/alexandrutopanfarcau/Documents/GitHub/demo/src/main/webapp/hotel.json"));
        for (Object obj : hotelsArray) {
            JSONObject hotel = (JSONObject) obj;
            if (hotel.get("name").equals(hotelName)) {
                JSONArray rooms = (JSONArray) hotel.get("rooms");
                for (Object roomObj : rooms) {
                    JSONObject room = (JSONObject) roomObj;
                    if (room.get("roomNumber").equals(roomNumber)) {
                        room.put("isAvailable", false);
                        break;
                    }
                }
                break;
            }
        }

        try (FileWriter file = new FileWriter("/Users/alexandrutopanfarcau/Documents/GitHub/demo/src/main/webapp/hotel.json")) {
            file.write(hotelsArray.toJSONString());
            file.flush();
        }
    }
}
