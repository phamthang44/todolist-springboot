package com.thang.todolist.controller;

import com.thang.todolist.dto.TaskDTO;
import com.thang.todolist.entity.Task;
import com.thang.todolist.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
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

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDTO createTask(@Valid @RequestBody Task task) {

        Task taskEntity = this.taskService.saveTask(task);

        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(taskEntity.getId());
        taskDTO.setTitle(taskEntity.getTitle());
        taskDTO.setDescription(taskEntity.getDescription());
        taskDTO.setStatus(taskEntity.getStatus().toString());
        taskDTO.setPriority(taskEntity.getPriority().toString());
        taskDTO.setDueDate(taskEntity.getDueDate().toString());
        taskDTO.setCreatedAt(taskEntity.getCreatedAt().toString());
        taskDTO.setUpdatedAt(taskEntity.getUpdatedAt().toString());

        if (task.getTodolist() != null) {
            taskDTO.setTodolistId(task.getTodolist().getId());
        } else {
            taskDTO.setTodolistId(null);
        }

        return taskDTO;
    }

    @PutMapping
    public Task updateTask(@RequestBody Task task) {
        return taskService.saveTask(task);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Integer id) {
        taskService.deleteTask(id);
    }
}
