package com.thang.todolist.repository;

import com.thang.todolist.entity.Task;
import com.thang.todolist.entity.Todolist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodolistRepository extends JpaRepository<Todolist, Integer> {
    Todolist findByName(String name);
    boolean existsByName(String name);
    boolean existsByUserId(Integer userId);
    boolean existsByNameAndUserId(String name, Integer userId);
    List<Todolist> findByUserId(Integer userId);
    List<Todolist> findByNameContaining(String name);
    List<Todolist> findByNameContainingAndUserId(String name, Integer userId);

}
