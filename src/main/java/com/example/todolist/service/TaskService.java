package com.example.todolist.service;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Optional;

import com.example.todolist.entities.Tasks;

public interface TaskService {

    public Tasks createTask(Tasks tasks);
    Optional<List<Tasks>> getTasksByUserId(Long userId);
    Optional<Tasks> toggleTaskCompletion(Long taskId, Long userId) throws AccessDeniedException;
}
