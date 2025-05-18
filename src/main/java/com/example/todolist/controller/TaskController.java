package com.example.todolist.controller;

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
}
