package com.myorg.mvc.controllers;

import com.myorg.mvc.model.User;
import com.myorg.mvc.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private final
    UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping(value = "/all")
    public String getUsers(Model model) {
        List<User> allUsers = userService.getAllUsers();
        model.addAttribute("users", allUsers);
        return "all";
    }

    @GetMapping("/{userId}")
    public String getByID(@PathVariable("userId") Long userId, Model model) {
        User userById = userService.getUserById(userId);
        model.addAttribute("user", userById);
        return "userById";
    }

    @PostMapping
    public void addNewUser(@RequestBody User user) {
        userService.addNewUser(user);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable("userId") Long userID) {
        userService.deleteUser(userID);
    }

    @PutMapping("/{userId}&{name}&{email}")
    public User updateUser(@PathVariable("userId") Long userId,
                           @PathVariable("name") String name,
                           @PathVariable("email") String email) {
        userService.updateUser(userId, name, email);
        return userService.getUserById(userId);
    }
}