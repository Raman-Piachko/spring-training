package com.myorg.mvc.service;

import com.myorg.mvc.entity.User;

import java.util.List;

public interface UserService {
    User createEmptyUser();

    List<User> getAllUsers();

    User addNewUser(User user);

    User getUserById(Long id);

    User deleteUser(Long userID);

    User updateUser(Long userId, User user);

    User updateUser(Long userId, String firstName, String lastName, String email, String birthday);
}
