package com.flipfit.exceptions;


public class InvalidChoiceException extends Throwable{
    public InvalidChoiceException() {}
    @Override
    public String getMessage() {
        // Returns a custom error message when this exception is thrown
        return "Invalid Choice, try the mentioned choices!";
    }
}