package com.example.todolist.service;

import com.example.todolist.entities.Users;

public interface UserService {
    Users registerUser(Users registerationDto);

    Users getUserByUsername(String username);

    boolean checkPassword(String password, String password2);
}
