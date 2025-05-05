package com.thang.todolist.repository;
import com.thang.todolist.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
    Users findByUsername(String username);
    Users findByEmail(String email);
    Users findByUsernameOrEmail(String username, String email);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

//    @Modifying
//    @Query("UPDATE Users u SET u.account_status = :status WHERE u.id = :id")
//    void changeStatusById(@Param("id") Integer id, @Param("status") Users.UserStatus status);
}
