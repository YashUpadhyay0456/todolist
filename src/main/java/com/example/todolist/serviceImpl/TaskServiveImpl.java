package com.example.todolist.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.todolist.entities.Tasks;
import com.example.todolist.repository.TaskRepository;
import com.example.todolist.service.TaskService;

@Service
public class TaskServiveImpl implements TaskService {

    @Autowired
    TaskRepository taskRepository ;

    @Override
    public Tasks createTask(Tasks task) {
        return taskRepository.save(task);
    }
    @Override
    public List<Tasks> geTasksByUserId(Long userId){
        return taskRepository.findById(userId);
    }
    
}
