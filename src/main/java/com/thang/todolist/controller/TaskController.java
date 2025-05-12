package com.thang.todolist.controller;

import com.thang.todolist.dto.TaskDTO;
import com.thang.todolist.dto.request.TaskRequestDTO;
import com.thang.todolist.entity.Task;
import com.thang.todolist.entity.Todolist;
import com.thang.todolist.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/task")
public class TaskController {
    private final TaskService taskService;
    private final TodolistController todolistController;
    public TaskController(TaskService taskService, TodolistController todolistController) {
        this.taskService = taskService;
        this.todolistController = todolistController;
    }

    @GetMapping("/tasks/todolist/{id}")
    public List<Task> getAllTasksByTodolistId(@PathVariable Integer id) {
        return taskService.getAllTasksByTodolistId(id);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTask(@Valid @RequestBody TaskDTO taskDTO) {
        Task task = convertToEntity(taskDTO);
        return taskService.saveTask(task);
    }

    private Task convertToEntity(TaskDTO taskDTO) {
        Task task = new Task();
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setStatus(Task.Status.valueOf(taskDTO.getStatus()));
        task.setPriority(Task.Priority.valueOf(taskDTO.getPriority()));
        task.setDueDate(taskDTO.getDueDate());
        task.setCreatedAt(taskDTO.getCreatedAt());
        task.setUpdatedAt(taskDTO.getUpdatedAt());
        Todolist todolist = todolistController.getTodoListById(taskDTO.getTodolistId());
        task.setTodolist(todolist);
        return task;
    }

    private TaskDTO convertToDTO(Task task) {
        TaskDTO dto = new TaskDTO();
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setStatus(task.getStatus().toString());
        dto.setPriority(task.getPriority().toString());
        dto.setDueDate(task.getDueDate());
        dto.setCreatedAt(task.getCreatedAt());
        dto.setUpdatedAt(task.getUpdatedAt());
        dto.setTodolistId(task.getTodolist() != null ? task.getTodolist().getId() : null);
        return dto;
    }

    @PutMapping("/update")
    public Task updateTask(@Valid @RequestBody TaskRequestDTO taskRequest) {
        Integer taskId = taskRequest.getTaskId();
        Task task = taskService.getTaskById(taskId);
        if (task == null) {
            throw new IllegalArgumentException("Task not found");
        }
        task.setTitle(taskRequest.getTitle());
        task.setDescription(taskRequest.getDescription());
        task.setStatus(Task.Status.valueOf(taskRequest.getStatus()));
        task.setPriority(Task.Priority.valueOf(taskRequest.getPriority()));
        task.setDueDate(LocalDateTime.parse(taskRequest.getDueDate()));
        task.setUpdatedAt(LocalDateTime.parse(taskRequest.getUpdatedAt()));

        return taskService.saveTask(task);
    }

    @PostMapping("/edit")
    public Task getTaskByTodoListIdAndTaskId(@Valid @RequestBody TaskRequestDTO taskRequest) {
        Integer taskId = taskRequest.getTaskId();
        Integer todoListId = taskRequest.getTodoListId();

        // Không cần kiểm tra null vì @Valid và @NotNull đã xử lý
        Task task = taskService.getTaskByTodoListIdAndTaskId(taskId, todoListId);
        if (task == null) {
            throw new IllegalArgumentException("Task not found");
        }

        return task;
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Integer id) {
        taskService.deleteTask(id);
    }
}
