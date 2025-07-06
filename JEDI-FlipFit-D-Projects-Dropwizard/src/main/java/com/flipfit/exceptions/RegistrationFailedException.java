package com.flipfit.exceptions;

public class RegistrationFailedException extends Exception {
    public RegistrationFailedException() {}
    @Override
    public String getMessage() {
        // Returns a custom error message when this exception is thrown
        return "Registration failed. Please try again";
    }
}