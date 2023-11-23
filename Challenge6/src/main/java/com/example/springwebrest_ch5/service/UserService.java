package com.example.springwebrest_ch5.service;

import com.example.springwebrest_ch5.model.Merchant;
import com.example.springwebrest_ch5.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    User create(User user);

    List<User> getAll();

    Optional<User> getById(UUID id);

    User update(User user, UUID id);

    void delete(UUID id);
}
