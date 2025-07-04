package com.flipfit.exceptions;

import com.flipfit.constants.ColorConstants;

public class InvalidChoiceException extends Throwable{
    private String message = "Invalid Choice, try the mentioned choices!";
    public InvalidChoiceException() {}
    public InvalidChoiceException(String message) {
        this.message = message;
    }
    /**
     * Overrides the getMessage() method from the Exception class.
     * Provides a custom error message to be returned when this exception is thrown.
     *
     * @return a custom error message indicating that booking cancellation has failed
     * */
    @Override
    public String getMessage() {
        // Returns a custom error message when this exception is thrown
        return ColorConstants.RED + message + ColorConstants.RESET;
    }
}