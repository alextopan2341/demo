package com.example.demo.service;

import com.example.demo.models.User;
import com.example.demo.repository.UserRepository;

import java.io.IOException;
import java.util.List;

public class UserService {
    private final UserRepository userRepository;

    public UserService() throws IOException {
        this.userRepository = new UserRepository();
    }
    public void addUser( String CNP, String lastName) throws IOException {
        User user = new User(CNP, lastName);
        userRepository.addUser(user);
    }

    public List<User> getAllUsers(){
        return userRepository.getAllUsers();
    }
    public int getIdUserByCNP(String CNP){
        return userRepository.getIdByCNP(CNP);
    }
}
