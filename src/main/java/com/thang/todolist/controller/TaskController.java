package com.thang.todolist.controller;

import com.thang.todolist.entity.Task;
import com.thang.todolist.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks/todolist/{id}")
    public List<Task> getAllTasksByTodolistId(@PathVariable Integer id) {
        return taskService.getAllTasksByTodolistId(id);
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskService.saveTask(task);
    }
}
