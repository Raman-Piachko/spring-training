package com.myorg.mvc.controllers;

import com.myorg.mvc.entity.User;
import com.myorg.mvc.service.UserService;
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
    private UserService userService;

    @GetMapping
    public String showHome() {
        return "index";
    }

    @GetMapping("/login")
    public String logIn() {
        return "login";
    }

    @GetMapping("/register_success")
    public String success() {
        return "register_success";
    }

    @GetMapping("/registration_form")
    public String showRegistrationForm(Model model) {
        User user = userService.createEmptyUser();
        model.addAttribute("user", user);
        return "registration_form";
    }

    @PostMapping("/users")
    public String saveUser(@ModelAttribute User user) {
        userService.addNewUser(user);
        return "redirect:/register_success";
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
    public String deleteUser(@PathVariable("id") Long userID) {
        userService.deleteUser(userID);
        return "redirect:/users";
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