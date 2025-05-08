package com.thang.todolist.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TodoDTO {
    @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
    private int id;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String name;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String status;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String redirectUrl;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String message;
    public TodoDTO() {
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;

}
