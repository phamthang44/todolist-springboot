package com.thang.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.thang.todolist.entity.Task;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    Task findByTitle(String title);
//    boolean existsByTitle(String title);
//    boolean existsByTodolistId(Integer todolistId);
//    boolean existsByTitleAndTodolistId(String title, Integer todolistId);
    List<Task> findByTodolistId(Integer todolistId);

    Optional<Object> findByTodolistIdAndId(Integer todoListId, Integer taskId);
}
