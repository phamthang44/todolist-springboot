package com.thang.todolist.dto.request;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TaskRequestDTO {
    @NotNull(message = "Task ID is required")
    @JsonSerialize
    private Integer taskId;

    @NotNull(message = "TodoList ID is required")
    @JsonSerialize
    private Integer todoListId;

    private String title;
    private String description;
    private String status;
    private String priority;
    private String dueDate;
    private String updatedAt;

}
