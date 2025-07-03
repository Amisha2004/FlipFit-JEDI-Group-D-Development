package com.flipfit.exceptions;

public class UpdationFailedException extends Exception {

    private String message = "Updation failed. Please try again";
    public UpdationFailedException() {}
    public UpdationFailedException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        // Returns a custom error message when this exception is thrown
        return message;
    }
}
