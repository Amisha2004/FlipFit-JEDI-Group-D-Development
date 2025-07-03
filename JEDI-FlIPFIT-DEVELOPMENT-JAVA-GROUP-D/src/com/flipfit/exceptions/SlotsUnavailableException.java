package com.flipfit.exceptions;

import com.flipfit.constants.ColorConstants;

import static com.flipfit.constants.ColorConstants.*;


public class SlotsUnavailableException extends Exception {

    @Override
    public String getMessage() {
        // Returns a custom error message with color formatting when this exception is thrown
        return CYAN+ "Slots unavailable. Please try another gym or different slot in the gym" + ColorConstants.RESET;
    }
}