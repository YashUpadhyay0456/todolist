package com.example.todolist.serviceImpl;

import java.util.List;
import java.util.Optional;

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
    public Optional<List<Tasks>> getTasksByUserId(Long userId){
        return Optional.ofNullable(taskRepository.findByUserId(userId));
    }
    
    @Override
    public Tasks toggleTaskCompletion(Long taskId, Long userId) {
        Tasks task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        if (!task.getUserId().equals(userId)) {
            throw new RuntimeException("You are not authorized to update this task");
        }

        task.setCompleted(!task.isCompleted());
        return taskRepository.save(task);
    }
    
}
