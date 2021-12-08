package com.myorg.mvc.service;

import com.myorg.mvc.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User addNewUser(User user);

    User getUserById(Long id);

    User deleteUser(Long userID);

    void updateUser(Long userId, String name, String email);
    User createEmptyUser();

    User updateUser(Long userId, User user);
}
