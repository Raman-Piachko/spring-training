//package com.myorg.mvc.controllers;
//
//import com.myorg.mvc.model.User;
//import com.myorg.mvc.service.UserServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//@RestController
//public class UserRestController {
//    @Autowired
//    private final
//    UserServiceImpl userService;
//
//    public UserRestController(UserServiceImpl userService) {
//        this.userService = userService;
//    }
//
//
//    @GetMapping("/")
//    public String showHome(Model model) {
//        return "index";
//    }
//
//    @GetMapping("/registration_form")
//    public User showRegistrationForm(Model model) {
//        User user = new User();
//        model.addAttribute("user", user);
//        return user;
//    }
//
//    @PostMapping("/save")
//    public User saveEmployee(@ModelAttribute User user, Model model) {
//        userService.addNewUser(user);
//        model.addAttribute("employee", user);
//        return user;
//    }
//
//
//    @GetMapping(value = "/users")
//    public List<User> getUsers(Model model) {
//        List<User> allUsers = userService.getAllUsers();
//        model.addAttribute("users", allUsers);
//        return allUsers;
//    }
//
//    @GetMapping("/users/{id}")
//    public User getByID(@PathVariable Long id, Model model) {
//        User userById = userService.getUserById(id);
//        model.addAttribute("userById", userById);
//        return userById;
//    }
//
//    @DeleteMapping("/{userId}")
//    public String deleteUser(@PathVariable("userId") Long userID) {
//        userService.deleteUser(userID);
//        return "successfulDeletion";
//    }
//
//    @PutMapping("/{userId}&{name}&{email}")
//    public User updateUser(@PathVariable("userId") Long userId,
//                           @PathVariable("name") String name,
//                           @PathVariable("email") String email) {
//        userService.updateUser(userId, name, email);
//        return userService.getUserById(userId);
//    }
//}
