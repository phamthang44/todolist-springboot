package com.thang.todolist.controller;

import com.thang.todolist.entity.Tasks;
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
    public List<Tasks> getAllTasksByTodolistId(@PathVariable Integer id) {
        return taskService.getAllTasksByTodolistId(id);
    }

    @PostMapping
    public Tasks createTask(@RequestBody Tasks tasks) {
        return taskService.saveTask(tasks);
    }

    @PutMapping
    public Tasks updateTask(@RequestBody Tasks tasks) {
        return taskService.saveTask(tasks);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Integer id) {
        taskService.deleteTask(id);
    }
}
