/**
 * 
 */
package com.flipfit.business;

import com.flipfit.bean.*;
import com.flipfit.dao.FlipFitBookingDAOImpl;
import com.flipfit.dao.FlipFitBookingDAOInterface;

import java.util.List;

/**
 * 
 */
public class FlipFitBookingBusinessServices implements FlipFitBookingBusinessInterface{
    private final FlipFitBookingDAOInterface flipFitBookingDAOImpl;

    public FlipFitBookingBusinessServices() {
        this.flipFitBookingDAOImpl = new FlipFitBookingDAOImpl();
    }

    public FlipFitBooking makeBooking(int userID, int centreID, int startTime) {
        System.out.println("Made a booking for " + centreID);
        return null;
    }

    public boolean deleteBooking(int bookingId) {
        System.out.println("Deleting a booking for " + bookingId);
        return false;
    }
    public List<FlipFitBooking> getAllBookings(int userId){
        return flipFitBookingDAOImpl.getAllBookings(userId);
    }

    public List<FlipFitBooking> getBookingDetailsByBookingId(int bookingId){
        return flipFitBookingDAOImpl.getBookingDetailsByBookingId(bookingId);
    }
}