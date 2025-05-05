package com.thang.todolist.service;

import com.thang.todolist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.thang.todolist.entity.Users;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public Users findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Users save(Users users) {
        return userRepository.save(users);
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

//    public void changeStatusById(Integer id, Users.UserStatus status) {
//        userRepository.changeStatusById(id, status);
//    }

    public Users findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    public Users findByUsernameOrEmail(String username, String email) {
        return userRepository.findByUsernameOrEmail(username, email);
    }
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
    public boolean existsByUsernameOrEmail(String username, String email) {
        return userRepository.existsByUsername(username) || userRepository.existsByEmail(email);
    }
    public Users findById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

}
