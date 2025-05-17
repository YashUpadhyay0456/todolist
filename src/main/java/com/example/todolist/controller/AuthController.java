package com.example.todolist.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todolist.entities.Users;
import com.example.todolist.service.UserService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> registerUser(@RequestBody Users newUser) {
        Map<String, Object> response = new HashMap<>();

        Users createdUser = userService.registerUser(newUser);

        if(createdUser == null){
            response.put("message", "Username already exists!");
            response.put("name", null);
            response.put("username", null);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }else{
            response.put("message", "User registered successfully!");
            response.put("name", createdUser.getName());
            response.put("username", createdUser.getUsername());
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> loginUser(@RequestBody Users loginRequest) {
        Map<String,Object> response = new HashMap<>();
        Users existingUser = userService.getUserByUsername(loginRequest.getUsername());
        
        if(existingUser == null){
            response.put("message","User does not exists" );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        boolean passwordMatch = userService.checkPassword(loginRequest.getPassword(),existingUser.getPassword());

        if(!passwordMatch){
            response.put("message", "Invalid credentials!");
            response.put("name", null);
            response.put("username", null);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
        response.put("message", "Login successful!");
        response.put("name", existingUser.getName());
        response.put("username", existingUser.getUsername());
        return ResponseEntity. status(HttpStatus.OK).body(response);

    }
    
    
    


}

    
