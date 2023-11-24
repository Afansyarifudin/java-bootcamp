package com.example.springwebrest_ch5.controller;

import com.example.springwebrest_ch5.model.Merchant;
import com.example.springwebrest_ch5.model.User;
import com.example.springwebrest_ch5.service.UserServiceImpl;
import com.example.springwebrest_ch5.utils.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@Tag(
        name = "User Resource",
        description = "User Resource Description"
)
@RequestMapping("api/user")
public class UserController {

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Autowired

    @GetMapping
    @Operation(
            summary = "Get All Merchant",
            description = "Get All Merchant Data"
    )
    public ResponseEntity<Map<String, Object>> getAll() {
        try {
            List<User> users = userService.getAll();
            return ResponseUtil.successResponseWithData("Get all data user", users);
        } catch (Exception e) {
            return ResponseUtil.notFoundResponse("Failed to get User");
        }
    }

//    @PostMapping("/register")
//    @Operation(
//            summary = "Register New User",
//            description = "Register New User"
//    )
//    public ResponseEntity<Map<String, Object>> register(@RequestBody @Valid User user) {
//        try {
//            User cratedUser = userService.create(user);
//            return ResponseUtil.successResponseWithData("User succesfully created", cratedUser);
//        } catch (Exception e) {
//            return ResponseUtil.notFoundResponse("Failed to register user");
//        }
//    }
//
//    @PostMapping("/login")
//    @Operation(
//            summary = "Login User",
//            description = "Login User"
//    )
//    public ResponseEntity<Map<String, Object>> login(@RequestBody @Valid User user) {
//        return null;
//    }

    @GetMapping("{id}")
    @Operation(
            summary = "Get User Data By Id",
            description = "Get User Data By Id"
    )
    public ResponseEntity<Map<String, Object>> show(@PathVariable("id") UUID id) {
        try {
            Optional<User> optionalUser = userService.getById(id);
            if (optionalUser.isPresent()) {
                User getUser = optionalUser.get();
                return ResponseUtil.successResponseWithData("Success get user by Id", getUser);
            } else {
                return ResponseUtil.notFoundResponse("USer not found");
            }
        } catch (Exception e) {
            return ResponseUtil.notFoundResponse("User not found");
        }
    }

    @PutMapping("{id}")
    @Operation(
            summary = "Update data user",
            description = "Update data user"
    )
    public ResponseEntity<Map<String, Object>> put(@PathVariable("id") UUID id,
                                                   @RequestBody User user) {
        try {
            User updatedUser = userService.update(user, id);
            return ResponseUtil.successResponseWithData("User successfully updated", updatedUser);
        } catch (Exception e) {
            return ResponseUtil.notFoundResponse("User not found");
        }
    }

    @DeleteMapping("{id}")
    @Operation(
            summary = "Delete user data",
            description = "delete user data"
    )
    public ResponseEntity<Map<String, Object>> delete(@PathVariable("id")UUID id){
        try {
            userService.delete(id);
            return ResponseUtil.successResponse("User deleted successfully");
        } catch (Exception e) {
            return ResponseUtil.notFoundResponse("User not found");
        }
    }

}
