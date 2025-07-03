package com.flipfit.exceptions;

public class InvalidPhoneNumberException extends Exception {

    @Override
    public String getMessage() {
        // Returns a custom error message when this exception is thrown
        return "Invalid phone number. Please enter correct phone number";
    }
}