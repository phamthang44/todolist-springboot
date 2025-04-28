package com.thang.todolist.controller;

import com.thang.todolist.entity.Todolist;
import com.thang.todolist.service.TaskService;
import com.thang.todolist.service.TodolistService;
import com.thang.todolist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping
    public Todolist createTodoList(@RequestBody Todolist todolist) {
        return this.todoListService.saveTodoList(todolist);
    }

    @PutMapping("/{id}")
    public Todolist updateTodoList(@PathVariable Integer id, @RequestBody Todolist todolist) {
        //todolist.setId(id); - just test

        return this.todoListService.saveTodoList(todolist);
    }






}
