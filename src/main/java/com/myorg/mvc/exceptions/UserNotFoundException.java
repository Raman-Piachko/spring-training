package com.myorg.mvc.exceptions;

public class UserNotFoundException extends UserServiceException {
    private Long id;


    public UserNotFoundException(Long id) {
        super("Could not find user " + id);
    }
}
