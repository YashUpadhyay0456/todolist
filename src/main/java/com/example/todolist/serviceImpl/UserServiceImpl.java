package com.example.todolist.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.todolist.dto.UserDto;
import com.example.todolist.entities.Users;
import com.example.todolist.repository.UserRepository;
import com.example.todolist.service.UserService;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public String registerUser(UserDto registerationDto){
        if(userRepository.existsByUsername(registerationDto.getUsername())){
            return "User already exists";
        }
        else{
            Users user = new Users();
            user.setName(registerationDto.getName());
            user.setUsername(registerationDto.getUsername());
            user.setPassword(passwordEncoder.encode(registerationDto.getPassword()));
            userRepository.save(user);
            return "User registered successfully";
        }

    }    
}
