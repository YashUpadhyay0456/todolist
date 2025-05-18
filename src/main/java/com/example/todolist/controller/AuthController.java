package com.example.todolist.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todolist.config.JwtUtil;
import com.example.todolist.entities.Users;
import com.example.todolist.service.UserService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> registerUser(@RequestBody Users newUser) {
        Users createdUser = userService.registerUser(newUser);

        if (createdUser == null) {
            return buildResponse(HttpStatus.CONFLICT, "Username already exists!", null, null);
        } else {
            return buildResponse(HttpStatus.OK, "User registered successfully!", createdUser, null);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> loginUser(@RequestBody Users loginRequest) {
        Users existingUser = userService.getUserByUsername(loginRequest.getUsername());

        if (existingUser == null) {
            return buildResponse(HttpStatus.NOT_FOUND, "User does not exist", null, null);
        }

        boolean passwordMatch = userService.checkPassword(loginRequest.getPassword(), existingUser.getPassword());

        if (!passwordMatch) {
            return buildResponse(HttpStatus.UNAUTHORIZED, "Invalid credentials!", null, null);
        }

        String token = jwtUtil.generateToken(existingUser);
        return buildResponse(HttpStatus.OK, "Login successful!", existingUser, token);
    }

    private ResponseEntity<Map<String, Object>> buildResponse(HttpStatus status, String message, Users user, String token) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);

        if (user != null) {
            Map<String, Object> userData = new HashMap<>();
            userData.put("name", user.getName());
            userData.put("username", user.getUsername());
            response.put("user", userData);
        } else {
            response.put("user", null);
        }

        if (token != null) {
            response.put("token", token);
        }

        return ResponseEntity.status(status).body(response);
    }
}
