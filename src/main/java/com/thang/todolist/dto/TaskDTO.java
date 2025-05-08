package com.thang.todolist.dto;


import lombok.Data;

@Data
public class TaskDTO {

    private Integer id;
    private String title;
    private String description;
    private String status;
    private String priority;
    private String dueDate;
    private String createdAt;
    private String updatedAt;
    private Integer todolistId;

    public TaskDTO() {
    }

    public TaskDTO(Integer id, String title, String description, String status, String priority, String dueDate, String createdAt, String updatedAt, Integer todolistId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.dueDate = dueDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.todolistId = todolistId;
    }

}
