package com.myorg.mvc;

import com.myorg.mvc.controllers.UserController;
import com.myorg.mvc.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class ApplicationConfig {

    @Bean
    UserService userService() {
        return new UserService();
    }

    @Bean
    UserController userController() {
        return new UserController(userService());
    }
}