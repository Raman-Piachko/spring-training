package com.myorg.mvc.controllers;

import com.myorg.mvc.entity.User;
import com.myorg.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class UserRestController {
    @Autowired
    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/save")
    public User saveUser(@ModelAttribute User user) {
        return userService.addNewUser(user);
    }


    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public User getByID(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @DeleteMapping("/users/{id}")
    public User deleteUser(@PathVariable("id") Long userID) {
        return userService.deleteUser(userID);
    }

    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable("id") Long userId,
                           @RequestBody User user) {
        return userService.updateUser(userId, user);
    }
}
