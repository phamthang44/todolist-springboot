package com.thang.todolist.service;

import com.thang.todolist.entity.Task;
import com.thang.todolist.exception.NotFoundException;
import com.thang.todolist.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasksByTodolistId(Integer id) {
        return taskRepository.findByTodolistId(id);
    }
    public Task getTaskByTitle(String title) {
        return taskRepository.findByTitle(title);
    }

    //create also update here
    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    public void deleteTask(Integer id) {
        taskRepository.deleteById(id);
    }
//    public boolean existsByTitle(String title) {
//        return taskRepository.existsByTitle(title);
//    }

    public Task getTaskById(Integer id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Task with ID " + id + " not found"));
    }

    public Task getTaskByTodoListIdAndTaskId(Integer taskId, Integer todoListId) {
        return (Task) taskRepository.findByTodolistIdAndId(todoListId, taskId)
                .orElseThrow(() -> new NotFoundException("Task with ID " + taskId + " not found in TodoList with ID " + todoListId));
    }
}
