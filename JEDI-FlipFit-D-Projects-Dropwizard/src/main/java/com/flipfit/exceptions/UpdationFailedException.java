package com.flipfit.exceptions;

public class UpdationFailedException extends Exception {


    public UpdationFailedException() {}
    @Override
    public String getMessage() {
        // Returns a custom error message when this exception is thrown
        return "Updation failed. Please try again";
    }
}
