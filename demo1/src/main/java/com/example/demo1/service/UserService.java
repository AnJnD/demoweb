package com.example.demo1.service;

import com.example.demo1.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService { // tạo ra lớp cha
    List<User> getAllUser();

    void saveUser(User user);  // định nghĩa các phương thức

    void deleteUser(Long id);

    boolean existsByEmail(String email); // phương thức kiểm tra email đã tồn tại hay chưa


    Optional<User> findUserById(Long id);

    User findByEmail(String email);

    List<User> findAll();

    User findByUsername(String username);
}