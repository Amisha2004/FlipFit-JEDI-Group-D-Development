package com.flipfit.exceptions;

import com.flipfit.constants.ColorConstants;

import static com.flipfit.constants.ColorConstants.*;


public class SlotsNotFoundException extends Exception {

    private String message = "Slot Not Found!";
    public SlotsNotFoundException() {}
    public SlotsNotFoundException(String message) {
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