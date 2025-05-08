package com.thang.todolist.controller;
import com.thang.todolist.dto.TodoDTO;
import com.thang.todolist.entity.Todolist;
import com.thang.todolist.entity.User;
import com.thang.todolist.service.TodolistService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/todo")
public class TodolistController {

    private final TodolistService todoListService;

    @Autowired
    public TodolistController(TodolistService todoListService) {
        this.todoListService = todoListService;
    }

    @GetMapping("/todolists")
    public List<Todolist> getAllTodoLists() {
        return this.todoListService.getAllTodoLists();
    }

    @GetMapping("/{id}")
    public Todolist getTodoListById(@PathVariable Integer id) {
        return this.todoListService.getTodolistById(id);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public TodoDTO createTodoList(@Valid @RequestBody Todolist todolist) {
        if (todolist.getUser() == null) {
            User defaultUser = new User();
            defaultUser.setId(1);
            todolist.setUser(defaultUser);
        }
        Todolist todo = this.todoListService.saveTodoList(todolist); // Trả về đối tượng để serialize thành JSON
        TodoDTO todoResponse = new TodoDTO();
        todoResponse.setId(todo.getId());
        todoResponse.setName(todo.getName());
        todoResponse.setCreatedAt(todo.getCreatedAt());
        todoResponse.setUpdatedAt(todo.getUpdatedAt());
        todoResponse.setStatus("success");
        todoResponse.setMessage("Todo list created successfully");

        return todoResponse;
    }



    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}")
    public Todolist updateTodoList(@PathVariable Integer id, @RequestBody Todolist todolist) {
        //todolist.setId(id); - just test
        return this.todoListService.saveTodoList(todolist);
    }






}
