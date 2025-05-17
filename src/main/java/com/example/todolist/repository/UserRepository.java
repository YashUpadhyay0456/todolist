package com.example.todolist.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.todolist.entities.Users;

public interface UserRepository extends JpaRepository<Users,Long> {
    
    boolean existsByUsername(String username);

    Optional<Users> findByUsername(String username);
}
