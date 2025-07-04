package com.flipfit.exceptions;

import com.flipfit.constants.ColorConstants;

public class InvalidLoginException extends Exception {
    private String message = "Login failed. Please try again";
    public InvalidLoginException() {}
    public InvalidLoginException(String message) {
        this.message = message;
    }
    @Override
    public String getMessage() {
        // Returns a custom error message when this exception is thrown
        return message;
    }
}