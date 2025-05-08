package com.thang.todolist.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
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
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = AUTO)
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer id;

    @NotBlank(message = "Username cannot be empty")
    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull
    private String username;

    @NotBlank(message = "Email cannot be empty")
    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull
    @Column(unique = true)
    private String email;

    @NotNull
    @NotBlank(message = "Password cannot be empty")
    private String password;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Todolist> todoLists;

    @Enumerated(EnumType.STRING)
    @JsonIgnore
    private UserStatus userStatus;

    public enum UserStatus {
        ACTIVE,
        BANNED,
        INACTIVE,
        DELETED
    }

}