package com.flipfit.exceptions;


public class InvalidAadhaarCardException extends Exception {

    @Override
    public String getMessage() {
        // Returns a custom error message when this exception is thrown
        return "Invalid Aadhaar Card. Please enter correct Aadhaar Card";
    }
}