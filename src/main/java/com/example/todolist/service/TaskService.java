package com.example.todolist.service;

import java.util.List;
import java.util.Optional;

import com.example.todolist.entities.Tasks;

public interface TaskService {

    public Tasks createTask(Tasks tasks);
    Optional<List<Tasks>> getTasksByUserId(Long userId);
    Tasks toggleTaskCompletion(Long taskId, Long userId);
}
