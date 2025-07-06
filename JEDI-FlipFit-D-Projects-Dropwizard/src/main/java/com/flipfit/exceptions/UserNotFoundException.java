package com.flipfit.exceptions;

public class UserNotFoundException extends Exception {

    public UserNotFoundException() {}
 @Override
    public String getMessage() {
        // Returns a custom error message when this exception is thrown
        return "User Not found";
    }
}