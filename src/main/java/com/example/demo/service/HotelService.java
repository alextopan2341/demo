package com.example.demo.service;

import com.example.demo.models.Hotel;
import com.example.demo.models.Room;
import com.example.demo.repository.HotelRepository;

import java.io.IOException;
import java.util.List;

public class HotelService {
    private final HotelRepository hotelRepository ;

    public HotelService() throws IOException {
        this.hotelRepository = new HotelRepository();
    }

    public void updateAvailability(int hotelId, int roomNumber) throws IOException {
        hotelRepository.updateAvailability(hotelId,roomNumber);
    }
    public List<Hotel> getAllHotels(){
        return hotelRepository.getAllHotels();
    }
    public Room getRoomByNumber(int hotelId, int roomNumber){
        for(Hotel hotel:getAllHotels()){
            for(Room room: hotel.getRooms()){
                if(hotel.getId() == hotelId && room.getRoomNumber() == roomNumber)
                    return room;
            }
        }
        return null;
    }
    public Hotel getHotelById(int hotelId){
        for(Hotel hotel: getAllHotels()){
            if(hotel.getId() == hotelId){
                return hotel;
            }
        }
        return null;
    }
}
