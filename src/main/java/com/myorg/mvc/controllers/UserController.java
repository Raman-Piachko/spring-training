package com.myorg.mvc.controllers;

import com.myorg.mvc.entity.User;
import com.myorg.mvc.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }


    @GetMapping()
    public String showHome() {
        return "index";
    }

    @GetMapping("/registration_form")
    public String showRegistrationForm(Model model) {
        User user = userService.createEmptyUser();
        model.addAttribute("user", user);
        return "registration_form";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute User user, Model model) {
        userService.addNewUser(user);
        return getUsers(model);
    }


    @GetMapping("/users")
    public String getUsers(Model model) {
        List<User> allUsers = userService.getAllUsers();
        model.addAttribute("users", allUsers);
        return "all";
    }

    @GetMapping("/users/{id}")
    public String getByID(@PathVariable Long id, Model model) {
        User userById = userService.getUserById(id);
        model.addAttribute("userById", userById);
        return "user_by_id";
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable("id") Long userID, Model model) {
        userService.deleteUser(userID);
        return getUsers(model);
    }

    @PutMapping("/{userId}&{firstName}&{lastName}&{email}&{birthday}")
    public String updateUser(@PathVariable("userId") Long userId,
                             @PathVariable("firstName") String firstName,
                             @PathVariable("lastName") String lastName,
                             @PathVariable("email") String email,
                             @PathVariable("birthday") String birthday, Model model) {
        userService.updateUser(userId, firstName, lastName, email, birthday);
        return getByID(userId, model);
    }
}