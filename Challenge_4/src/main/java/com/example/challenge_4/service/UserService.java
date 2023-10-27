package com.example.challenge_4.service;

import com.example.challenge_4.model.User;

import java.util.List;

public interface UserService {
    void registerUser(String email, String password, String username);

    void updateEmail(String username, String email);

    void deleteUser(String username);

    List<User> getAll();

}
