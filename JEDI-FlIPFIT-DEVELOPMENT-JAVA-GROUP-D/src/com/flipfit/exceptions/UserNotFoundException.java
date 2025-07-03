package com.flipfit.exceptions;

public class UserNotFoundException extends Exception {

    @Override
    public String getMessage() {
        // Returns a custom error message when this exception is thrown
        return "User not found. Please register or login using a different account";
    }
}