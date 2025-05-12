package com.thang.todolist.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskResponseDTO {
    private Integer id;
    private String title;
    private String description;
    private String status;
    private String priority;
    private LocalDateTime dueDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Integer todolistId;
}
