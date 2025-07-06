package com.flipfit.exceptions;


/**
 * Custom exception class for handling slot booking failure scenarios.
 * This exception is thrown when a slot booking fails.
 *

 */
public class SlotBookingFailedException extends Exception {

    public SlotBookingFailedException() {}
    @Override
    public String getMessage() {
        // Returns a custom error message with color formatting when this exception is thrown
        return "Unable to book slot. Please try again";
    }
}