package com.example.todolist.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.todolist.entities.Users;

public interface UserService extends UserDetailsService {
    Users registerUser(Users registerationDto);

    Users getUserByUsername(String username);

    boolean checkPassword(String password, String password2);

    Users loadUserByUsername(String username);
}
