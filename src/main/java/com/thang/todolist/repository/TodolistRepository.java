package com.thang.todolist.repository;

import com.thang.todolist.entity.Todolists;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodolistRepository extends JpaRepository<Todolists, Integer> {
    Todolists findByName(String name);
//    boolean existsByName(String name);
//    boolean existsByUsersId(Integer userId);
//    boolean existsByNameAndUsersId(String name, Integer usersId);
    List<Todolists> findByUsersId(Integer usersId);

}
