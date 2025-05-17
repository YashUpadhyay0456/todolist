package com.example.todolist.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todolist.entities.Users;
import com.example.todolist.service.UserService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/auth")
public class RegisterController {

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
    


}

    
