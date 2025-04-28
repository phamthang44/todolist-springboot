package com.thang.todolist.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.thang.todolist.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
    User findByEmail(String email);
    User findByUsernameOrEmail(String username, String email);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

//    @Modifying
//    @Query("UPDATE User u SET u.account_status = :status WHERE u.id = :id")
//    void changeStatusById(@Param("id") Integer id, @Param("status") User.UserStatus status);
}
