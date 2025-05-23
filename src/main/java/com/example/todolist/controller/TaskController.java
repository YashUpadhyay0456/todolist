package com.example.todolist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todolist.entities.Tasks;
import com.example.todolist.entities.Users;
import com.example.todolist.service.TaskService;
import com.example.todolist.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<Tasks> createTask(@RequestBody Tasks tasks) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        Users user = userService.getUserByUsername(username);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        tasks.setUserId(user.getId());
        Tasks savedTask = taskService.createTask(tasks);

        return ResponseEntity.status(HttpStatus.OK).body(savedTask);
    }

    @GetMapping("/myTasks")
    public ResponseEntity<List<Tasks>> getMyTasks(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Users user = userService.getUserByUsername(username);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        List<Tasks> userTasks = taskService.getTasksByUserId(user.getId()).orElse(java.util.Collections.emptyList());
        return ResponseEntity.ok(userTasks);

    }

    @PatchMapping("/toggle{id}")
    public ResponseEntity<?> toggleTaskCompletion(@PathVariable Long id) {
        System.out.println("Toggle task completion for task ID: " + id);
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Users user = userService.getUserByUsername(username);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated");
        }

        try {
            Tasks updatedTask = taskService.toggleTaskCompletion(id, user.getId());
            return ResponseEntity.ok(updatedTask);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }
}
