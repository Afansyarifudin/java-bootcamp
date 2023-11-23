package com.example.springwebrest_ch5.service;

import com.example.springwebrest_ch5.model.User;
import com.example.springwebrest_ch5.repository.UserRepository;
import com.example.springwebrest_ch5.utils.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User create(User user) {
        if (user.getUsername().isEmpty()) return null;
        user = userRepository.save(user);
        return user;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getById(UUID id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            logger.info("There's no User yet");
            throw new RuntimeException();
        }
        return userOptional;
    }

    @Override
    public User update(User user, UUID id) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isEmpty()) {
            logger.info("User not found");
            throw new RuntimeException("User not found");
        }

        User updatedUser = existingUser.get();
        if (!updatedUser.isDeleted()) {
            updatedUser.setUsername(user.getUsername())
                    .setEmail(user.getEmail())
                    .setPassword(user.getPassword());
            return userRepository.save(updatedUser);
        } else {
            logger.info("User has been deleted");
            throw new RuntimeException("User has been deleted cannot be updated");
        }
    }

    @Override
    public void delete(UUID id) {
        Optional<User> userOptional = getById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            LocalDateTime currentDate = LocalDateTime.now();
            user.setDeletedDate(currentDate);
            userRepository.save(user);
            userRepository.deleteById(id);
        } else {
            ResponseUtil.notFoundResponse("User not found");
        }
    }
}
