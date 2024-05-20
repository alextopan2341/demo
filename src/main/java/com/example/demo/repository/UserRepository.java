package com.example.demo.repository;

import com.example.demo.models.Reservation;
import com.example.demo.models.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserRepository {
    private static final String FILE_PATH = "/Users/alexandrutopanfarcau/Documents/GitHub/demo/src/main/webapp/users.json";
    private final List<User> users;

    public UserRepository() throws IOException {
        users = readFromFile();
    }

    public void saveUser() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File(FILE_PATH), users);
    }

    private int nextIdUser(){
        return users.size() + 1;
    }

    public void addUser(User user) throws IOException {
        user.setId(nextIdUser());
        users.add(user);
        saveUser();
    }
    public int getIdByCNP(String CNP){
        for (User user:users)
            if(Objects.equals(user.getCNP(), CNP))
                return user.getId();
        return 0;
    }

    private static List<User> readFromFile() throws IOException {
        File file = new File(FILE_PATH);
        if (file.exists() && file.length() == 0) {
            return new ArrayList<>();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(file, new TypeReference<List<User>>() {});
    }

    public List<User> getAllUsers() {
        return users;
    }

}
