package com.flipfit.exceptions;




public class SlotsNotFoundException extends Exception {

    public SlotsNotFoundException() {}
     @Override
    public String getMessage() {
        // Returns a custom error message when this exception is thrown
        return "Slot Not Found!";
    }
}