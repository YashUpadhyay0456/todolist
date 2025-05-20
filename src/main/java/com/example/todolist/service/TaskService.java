package com.example.todolist.service;

import java.util.List;

import com.example.todolist.entities.Tasks;

public interface TaskService {

    public Tasks createTask(Tasks tasks);
    List<Tasks> geTasksByUserId(Long userId);
}
