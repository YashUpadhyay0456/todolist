package com.example.todolist.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
    public Users registerUser(Users newUser){
        if(userRepository.existsByUsername(newUser.getUsername())){
            return null;
        }
        String encodedPassword = passwordEncoder.encode(newUser.getPassword());
        newUser.setPassword(encodedPassword);

        return userRepository.save(newUser);

    }    
    @Override
    public Users getUserByUsername(String username){
        return userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public boolean checkPassword(String logoinRequestPassword, String userPassword){
        return passwordEncoder.matches(logoinRequestPassword, userPassword);
    }
    @Override
    public Users loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElse(null);
    }
}
