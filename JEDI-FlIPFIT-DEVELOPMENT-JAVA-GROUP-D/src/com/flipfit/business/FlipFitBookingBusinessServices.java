/**
 * 
 */
package com.flipfit.business;

import com.flipfit.bean.*;
/**
 * 
 */
public class FlipFitBookingBusinessServices implements FlipFitBookingBusinessInterface{
    public FlipFitBooking makeBooking(int userID, int centreID, int startTime) {
        System.out.println("Made a booking for " + centreID);
        return null;
    }

    public boolean deleteBooking(int bookingId) {
        System.out.println("Deleting a booking for " + bookingId);
        return false;
    }
}