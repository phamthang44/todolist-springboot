package com.thang.todolist.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
import java.util.List;
import jakarta.persistence.*;
import lombok.Data;
import static jakarta.persistence.GenerationType.AUTO;

@Entity
@Data
class User {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Integer id;

    @Column(unique = true)
    private String username;

    private String email;

    private String password;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Todolist> todolists;

}