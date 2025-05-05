package com.thang.todolist.service;

import com.thang.todolist.entity.Todolists;
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

    public List<Todolists> getAllTodoLists() {
        return todolistRepository.findAll();
    }

    public Todolists getTodolistById(Integer id) {
        return todolistRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Todo list with ID " + id + " not found"));
    }

    public Todolists getTodoListByName(String name) {
        return todolistRepository.findByName(name);
    }

    //create also update
    public Todolists saveTodoList(Todolists todolist) {
        return todolistRepository.save(todolist);
    }

    public void deleteTodoList(Integer id) {
        todolistRepository.deleteById(id);
    }

//    public boolean existsByName(String name) {
//        return todolistRepository.existsByName(name);
//    }
//    public boolean existsByUserId(Integer usersId) {
//        return todolistRepository.existsByUsersId(usersId);
//    }
//    public boolean existsByNameAndUserId(String name, Integer usersId) {
//        return todolistRepository.existsByNameAndUsersId(name, usersId);
//    }
    public List<Todolists> findByUserId(Integer usersId) {
        return todolistRepository.findByUsersId(usersId);
    }

}
