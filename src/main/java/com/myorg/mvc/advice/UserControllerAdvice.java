package com.myorg.mvc.advice;

import com.myorg.mvc.exceptions.DoubleRegistrationException;
import com.myorg.mvc.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class UserControllerAdvice {
    @ResponseBody
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String employeeNotFoundHandler(UserNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(DoubleRegistrationException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String handleException(DoubleRegistrationException e, Model model) {
        String message = e.getMessage();
        model.addAttribute("message", message);
        return "forbiddenError";
    }

}
