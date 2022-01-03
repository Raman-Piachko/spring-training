package com.myorg.mvc.exceptions;

public class UserServiceException extends RuntimeException {
    private String message;

    public UserServiceException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
