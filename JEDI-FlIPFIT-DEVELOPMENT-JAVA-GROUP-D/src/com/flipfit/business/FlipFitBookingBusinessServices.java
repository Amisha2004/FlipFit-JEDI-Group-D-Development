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

    public FlipFitBooking makeBooking(FlipFitBooking flipFitBooking) {
        return flipFitBookingDAOImpl.makeBooking(flipFitBooking);
    }

    public boolean deleteBooking(int bookingId, int slotId) {
        return flipFitBookingDAOImpl.deleteBooking(bookingId, slotId);
    }
    public List<FlipFitBooking> getAllBookings(int userId){
        return flipFitBookingDAOImpl.getAllBookings(userId);
    }

    public FlipFitBooking getBookingDetailsByBookingId(int bookingId){
        return flipFitBookingDAOImpl.getBookingDetailsByBookingId(bookingId);
    }
}