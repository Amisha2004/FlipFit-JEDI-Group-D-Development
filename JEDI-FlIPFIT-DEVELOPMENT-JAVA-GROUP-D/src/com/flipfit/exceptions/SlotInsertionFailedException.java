package com.flipfit.exceptions;
public class SlotInsertionFailedException extends Exception {

    private String message = "Slot insertion failed!";
    public SlotInsertionFailedException() {}
    public SlotInsertionFailedException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        // Returns a custom error message when this exception is thrown
        return message;
    }
}