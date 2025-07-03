package com.flipfit.exceptions;

public class InvalidPanCardException extends Exception {

    @Override
    public String getMessage() {
        // Returns a custom error message when this exception is thrown
        return "Invalid Pan Card. Please enter correct details";
    }
}