package com.example.todolist.serviceImpl;

import java.nio.file.AccessDeniedException;
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
    public Optional<Tasks> toggleTaskCompletion(Long taskId, Long userId) throws AccessDeniedException {
        Optional<Tasks> taskOptional = taskRepository.findById(taskId);
        if (taskOptional.isEmpty()) {
            return Optional.empty();
        }

        Tasks task = taskOptional.get();

        if (!task.getUserId().equals(userId)) {
            throw new AccessDeniedException("You are not authorised to modify this task.");
        }

        task.setCompleted(!task.isCompleted());
        taskRepository.save(task);
        return Optional.of(task);
    }
    
}
