package com.flipfit.exceptions;


public class BookingDetailsNotFoundException extends Exception{

    public BookingDetailsNotFoundException() {}
    @Override
    public String getMessage() {
        // Returns a custom error message when this exception is thrown
        return "Booking Details Not found";
    }
}
