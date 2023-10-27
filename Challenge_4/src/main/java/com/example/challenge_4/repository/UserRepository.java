package com.example.challenge_4.repository;

import com.example.challenge_4.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.UUID;


public interface UserRepository extends JpaRepository<User, UUID> {
    @Procedure("register_user")
    void registerUser(@Param("email") String email, @Param("password") String password, @Param("username") String username);

    @Procedure("edit_email")
    void updateEmail(@Param("usernameuser") String username, @Param("updatedemail") String email);

    @Procedure("delete_user")
    void deleteUserByUsername(@Param("username") String username);
}
