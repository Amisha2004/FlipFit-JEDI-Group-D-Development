package com.flipfit.exceptions;

import com.flipfit.constants.ColorConstants;

import static com.flipfit.constants.ColorConstants.*;

/**
 * Custom exception class for handling slot booking failure scenarios.
 * This exception is thrown when a slot booking fails.
 *

 */
public class SlotBookingFailedException extends Exception {

    private String message = "Unable to book slot. Please try again";
    public SlotBookingFailedException() {}
    public SlotBookingFailedException(String message) {
        this.message = message;
    }
    @Override
    public String getMessage() {
        // Returns a custom error message with color formatting when this exception is thrown
        return CYAN +  message + ColorConstants.RESET;
    }
}