package com.example.springwebrest_ch5.controller;

import com.example.springwebrest_ch5.model.User;
import com.example.springwebrest_ch5.model.dto.request.LoginRequest;
import com.example.springwebrest_ch5.model.dto.response.JwtResponse;
import com.example.springwebrest_ch5.model.dto.response.ResponseHandler;
import com.example.springwebrest_ch5.security.jwt.JwtUtils;
import com.example.springwebrest_ch5.security.service.UserDetailsImpl;
import com.example.springwebrest_ch5.service.UserServiceImpl;
import com.example.springwebrest_ch5.utils.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@Tag(
        name = "Auth Resource",
        description = "Auth Resource Description"
)
@RequestMapping("api/auth")
public class AuthController {
    private final UserServiceImpl userService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    public AuthController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    @Operation(
            summary = "Register New User",
            description = "Register New User"
    )
    public ResponseEntity<Map<String, Object>> register(@RequestBody @Valid User user) {
        System.out.println(user);
        try {
            if (user.getRoles() == null || user.getRoles().isEmpty()) {
                return ResponseUtil.errorResponse("User mush have at least one role");
            }

            User createdUser = userService.create(user);
            return ResponseUtil.successResponseWithData("User successfully created", createdUser);
        } catch (Exception e) {
            return ResponseUtil.notFoundResponse("Failed to register user: " + e.getMessage());
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticate (@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(), loginRequest.getPassword()
                ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
        JwtResponse jwtResponse = new JwtResponse(jwt, userDetails.getUsername(), roles);
        return ResponseHandler.generateResponse("success", jwtResponse, null, HttpStatus.OK);

    }
}
