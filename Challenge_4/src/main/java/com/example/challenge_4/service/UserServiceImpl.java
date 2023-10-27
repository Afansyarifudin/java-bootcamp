package com.example.challenge_4.service;

import com.example.challenge_4.model.User;
import com.example.challenge_4.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;

    @Override
    public void registerUser(String email, String username, String password) {
        userRepository.registerUser(email, username, password);
    }

    @Override
    public void updateEmail(String username, String email) {
        userRepository.updateEmail(username, email);
    }

    @Override
    public void deleteUser(String username) {
        userRepository.deleteUserByUsername(username);
    }

    @Override
    public List<User> getAll() {
        return null;
    }
}
