package com.thang.todolist.service;

import com.thang.todolist.entity.Tasks;
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

    public List<Tasks> getAllTasksByTodolistId(Integer id) {
        return taskRepository.findByTodolistId(id);
    }
    public Tasks getTaskByTitle(String title) {
        return taskRepository.findByTitle(title);
    }

    //create also update here
    public Tasks saveTask(Tasks tasks) {
        return taskRepository.save(tasks);
    }

    public void deleteTask(Integer id) {
        taskRepository.deleteById(id);
    }
//    public boolean existsByTitle(String title) {
//        return taskRepository.existsByTitle(title);
//    }

    public Tasks getTaskById(Integer id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Task with ID " + id + " not found"));
    }
}
