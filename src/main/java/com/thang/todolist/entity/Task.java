package com.thang.todolist.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "todolist_id")
    @JsonBackReference
    private Todolist todolist;

    @NotBlank(message = "Title cannot be empty")
    @JsonSerialize(using = ToStringSerializer.class)
    private String title;

    @NotBlank(message = "Description cannot be empty")
    @JsonSerialize(using = ToStringSerializer.class)
    private String description;

    @Enumerated(EnumType.STRING)
    @JsonSerialize(using = ToStringSerializer.class)
    private Status status;

    @Enumerated(EnumType.STRING)
    @JsonSerialize(using = ToStringSerializer.class)
    private Priority priority;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dueDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;

    public enum Priority {
        LOW,
        MEDIUM,
        HIGH
    }

    public enum Status {
        PENDING,
        IN_PROGRESS,
        DONE
    }
}

