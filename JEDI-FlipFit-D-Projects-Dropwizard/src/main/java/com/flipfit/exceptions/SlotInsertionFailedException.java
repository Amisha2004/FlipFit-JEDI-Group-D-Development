package com.flipfit.exceptions;

/**
 * Custom exception class for handling slot insertion failure scenarios.
 * This exception is thrown when slot insertion fails.
 *

 */
public class SlotInsertionFailedException extends Exception {

    public SlotInsertionFailedException() {}
    @Override
    public String getMessage() {
        // Returns a custom error message when this exception is thrown
        return "Slot insertion failed!";
    }
}
