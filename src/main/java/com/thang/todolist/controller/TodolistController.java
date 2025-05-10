package com.thang.todolist.controller;
import com.thang.todolist.dto.request.TodoRequest;
import com.thang.todolist.dto.response.TodoDTO;
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

import java.time.LocalDateTime;
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
        TodoDTO todoResponse = new TodoDTO();
        if (todolist.getUser() == null) {
            User defaultUser = new User();
            defaultUser.setId(1);
            todolist.setUser(defaultUser);
        }
        todoResponse.setId(todolist.getId());
        todoResponse.setName(todolist.getName());
        todoResponse.setCreatedAt(todolist.getCreatedAt());
        todoResponse.setUpdatedAt(todolist.getUpdatedAt());
//        todoResponse.setStatus("success"); //need to check before setStatus
        saveTodoList(todolist);
//        todoResponse.setMessage("Todo list created successfully");

        return todoResponse;
    }

    @PutMapping("/update")
    public TodoDTO updateTodoList(@Valid @RequestBody TodoRequest todolist) {
        // Kiểm tra ID
        if (todolist.getId() == null) {
            throw new IllegalArgumentException("Todo list ID is required for update");
        }

        // Lấy todo từ service
        Todolist todo = this.todoListService.getTodolistById(todolist.getId());
        if (todo == null) {
            throw new IllegalArgumentException("Todo list not found");
        }
        todo.setName(todolist.getName());
        todo.setUpdatedAt(LocalDateTime.now());
        Todolist updatedTodo = this.todoListService.saveTodoList(todo);

        TodoDTO todoResponse = new TodoDTO();
        todoResponse.setId(updatedTodo.getId());
        todoResponse.setName(updatedTodo.getName());
        todoResponse.setCreatedAt(updatedTodo.getCreatedAt());
        todoResponse.setUpdatedAt(updatedTodo.getUpdatedAt());
//        todoResponse.setStatus("success"); //need to check before setStatus
//        todoResponse.setMessage("Todo list updated successfully");

        return todoResponse;
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTodoList(@PathVariable Integer id) {
        // Kiểm tra ID
        if (id == null) {
            throw new IllegalArgumentException("Todo list ID is required for delete");
        }
        // Lấy todo từ service
        Todolist todo = this.todoListService.getTodolistById(id);
        if (todo == null) {
            throw new IllegalArgumentException("Todo list not found");
        }
        // Xóa todo
        this.todoListService.deleteTodoList(id);
    }

    private Todolist saveTodoList(@RequestBody @Valid Todolist todolist) {
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

//    @PutMapping("/update/{id}")
//    public Todolist updateTodoList(@PathVariable Integer id, @RequestBody Todolist todolist) {
//        //todolist.setId(id); - just test
//        return this.todoListService.saveTodoList(todolist);
//    }






}
