package com.myorg.mvc.exceptions;

public class DoubleRegistrationException extends UserServiceException {
    private String email;

    public DoubleRegistrationException(String email) {
        super("User with email: " + email + " is already registered");
    }


}
