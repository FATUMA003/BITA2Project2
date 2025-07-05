package com.example.project_tracker.service;

import com.example.project_tracker.model.User;
import java.util.List;

public interface UserService {
    User createUser(User user);
    List<User> getAllUsers();
    User getUserById(Long id);
    User updateUser(Long id, User user);
    boolean deleteUser(Long id);

    User getUserByEmail(String email);
}

