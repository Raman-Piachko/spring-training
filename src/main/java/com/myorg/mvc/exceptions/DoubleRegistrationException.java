package com.myorg.mvc.exceptions;

public class DoubleRegistrationException extends RuntimeException {


    public DoubleRegistrationException(String email) {
        super("User with email: " + email + " is already registered");
    }


}
