package com.example.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.todolist.entities.Tasks;

public interface TaskRepository extends JpaRepository<Tasks, Long> {
    
}
