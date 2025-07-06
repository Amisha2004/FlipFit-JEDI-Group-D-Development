package com.flipfit.exceptions;


public class InvalidLoginException extends Exception {
    public InvalidLoginException() {}

    @Override
    public String getMessage() {
        // Returns a custom error message when this exception is thrown
        return "Login failed. Please try again";
    }
}