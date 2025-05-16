package com.example.todolist.entities;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Users {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String name;
    private String password;

    public Users() {
    }
    public Users(String username, String name, String password) {
        this.username = username;
        this.name = name;
        this.password = password;
    }
    
}
