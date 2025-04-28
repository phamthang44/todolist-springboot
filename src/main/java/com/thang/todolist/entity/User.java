package com.thang.todolist.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
import java.util.List;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import static jakarta.persistence.GenerationType.AUTO;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Integer id;

    @NotBlank(message = "Username cannot be empty")
    @NotNull
    private String username;

    @NotBlank(message = "Email cannot be empty")
    @NotNull
    @Column(unique = true)
    private String email;

    @NotNull
    @NotBlank(message = "Password cannot be empty")
    private String password;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Todolist> todoLists;

    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

    public enum UserStatus {
        ACTIVE,
        BANNED,
        INACTIVE,
        DELETED
    }
}