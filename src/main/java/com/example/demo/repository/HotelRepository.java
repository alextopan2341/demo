package com.example.demo.repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.models.Hotel;
import com.example.demo.models.Room;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HotelRepository {
    private static final String FILE_PATH = "/Users/alexandrutopanfarcau/Documents/GitHub/demo/src/main/webapp/hotel.json";

    private final List<Hotel> hotels;

    public HotelRepository() throws IOException {
        this.hotels = readFromFile();
    }
    public void saveHotels() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File(FILE_PATH), hotels);
    }

    private static List<Hotel> readFromFile() throws IOException {
        File file = new File(FILE_PATH);
        if (file.exists() && file.length() == 0) {
            return new ArrayList<>();
        }

        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(file, new TypeReference<List<Hotel>>() {});
    }

    public void updateAvailability(int hotelId, int roomNumber) throws IOException {
        for(Hotel hotel:hotels){
            for(Room room:hotel.getRooms()){
                if(hotel.getId() == hotelId && room.getRoomNumber()==roomNumber){
                    room.setIsAvailable(!room.getIsAvailable()  );
                    saveHotels();
                }
            }
        }
    }

    public List<Hotel> getAllHotels(){
        return hotels;
    }
}
