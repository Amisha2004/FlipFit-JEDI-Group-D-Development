package com.flipfit.exceptions;

public class InvalidGSTException extends Exception {

    @Override
    public String getMessage() {
        // Returns a custom error message when this exception is thrown
        return "Invalid GST. Please enter correct GST";
    }
}