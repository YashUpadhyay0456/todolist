package com.example.todolist.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.todolist.entities.Tasks;

public interface TaskRepository extends JpaRepository<Tasks, Long> {
    List<Tasks> findByUserId(Long userId);
}
