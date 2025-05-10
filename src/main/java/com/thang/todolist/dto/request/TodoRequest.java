package com.thang.todolist.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class TodoRequest {
    @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
    private Integer id;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String name;

}
