package com.myorg.mvc.controllers;

import com.myorg.mvc.model.User;
import com.myorg.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("/all")
    public List getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("{userId}")
    public User getByID(@PathVariable("userId") Long ID) {
        return userService.getUserByID(ID);
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
    public User updateUser(@PathVariable("userID") Long userID,
                           @PathVariable("name") String name,
                           @PathVariable("email") String email) {
        userService.updateUser(userID, name, email);
        return userService.getUserByID(userID);
    }
}