package com.thang.todolist.service;

import com.thang.todolist.entity.Todolist;
import com.thang.todolist.exception.NotFoundException;
import com.thang.todolist.repository.TodolistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodolistService {
    private final TodolistRepository todolistRepository;

    @Autowired
    public TodolistService(TodolistRepository todolistRepository) {
        this.todolistRepository = todolistRepository;
    }

    public List<Todolist> getAllTodoLists() {
        return todolistRepository.findAll();
    }

    public Todolist getTodolistById(Integer id) {
        return todolistRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Todo list with ID " + id + " not found"));
    }

    public Todolist getTodoListByName(String name) {
        return todolistRepository.findByName(name);
    }

    //create also update
    public Todolist saveTodoList(Todolist todolist) {
        return todolistRepository.save(todolist);
    }

    public void deleteTodoList(Integer id) {
        todolistRepository.deleteById(id);
    }

    public boolean existsByName(String name) {
        return todolistRepository.existsByName(name);
    }
    public boolean existsByUserId(Integer userId) {
        return todolistRepository.existsByUserId(userId);
    }
    public boolean existsByNameAndUserId(String name, Integer userId) {
        return todolistRepository.existsByNameAndUserId(name, userId);
    }
    public List<Todolist> findByUserId(Integer userId) {
        return todolistRepository.findByUserId(userId);
    }

}
