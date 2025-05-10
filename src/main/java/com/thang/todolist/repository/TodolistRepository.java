package com.thang.todolist.repository;

import com.thang.todolist.entity.Todolist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TodolistRepository extends JpaRepository<Todolist, Integer> {
    Todolist findByName(String name);
//    boolean existsByName(String name);
//    boolean existsByUsersId(Integer userId);
//    boolean existsByNameAndUsersId(String name, Integer usersId);
    List<Todolist> findByUserId(Integer userId);
}
