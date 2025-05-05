package com.thang.todolist.controller;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.thang.todolist.entity.Todolists;
import com.thang.todolist.entity.Users;
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
    public List<Todolists> getAllTodoLists() {
        return this.todoListService.getAllTodoLists();
    }

    @GetMapping("/{id}")
    public Todolists getTodoListById(@PathVariable Integer id) {
        return this.todoListService.getTodolistById(id);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Todolists createTodoList(@Valid @RequestBody Todolists todolist) {
        if (todolist.getUsers() == null) {
            Users defaultUser = new Users();
            defaultUser.setId(1); // Sử dụng 1L cho Long
            todolist.setUsers(defaultUser);
        }
        return this.todoListService.saveTodoList(todolist); // Trả về đối tượng để serialize thành JSON
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
    public Todolists updateTodoList(@PathVariable Integer id, @RequestBody Todolists todolist) {
        //todolist.setId(id); - just test
        return this.todoListService.saveTodoList(todolist);
    }






}
