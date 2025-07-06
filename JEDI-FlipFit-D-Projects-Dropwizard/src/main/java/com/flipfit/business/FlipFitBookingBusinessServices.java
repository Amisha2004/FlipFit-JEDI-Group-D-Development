/**
 * 
 */
package com.flipfit.business;

import com.flipfit.bean.*;
import com.flipfit.dao.FlipFitBookingDAOImpl;
import com.flipfit.dao.FlipFitBookingDAOInterface;
import com.flipfit.exceptions.BookingCancellationFailedException;
import com.flipfit.exceptions.BookingDetailsNotFoundException;
import com.flipfit.exceptions.SlotsNotFoundException;

import java.util.List;

/**
 * 
 */
public class FlipFitBookingBusinessServices implements FlipFitBookingBusinessInterface{
    private final FlipFitBookingDAOInterface flipFitBookingDAOImpl;

    public FlipFitBookingBusinessServices(FlipFitBookingDAOImpl flipFitBookingDAOImpl) {
        this.flipFitBookingDAOImpl = flipFitBookingDAOImpl;
    }

    public FlipFitBooking makeBooking(FlipFitBooking flipFitBooking) {
        try {
            if(flipFitBookingDAOImpl.makeBooking(flipFitBooking) == null){
                throw new SlotsNotFoundException();
            }
            return flipFitBookingDAOImpl.makeBooking(flipFitBooking);
        } catch (SlotsNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public boolean deleteBooking(int bookingId, int slotId) {
        try {
            boolean isDeleted = flipFitBookingDAOImpl.deleteBooking(bookingId, slotId);
            if (!isDeleted) {
                throw new BookingCancellationFailedException();
            }
            return true;
        } catch (BookingCancellationFailedException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public List<FlipFitBooking> getAllBookings(int userId){
        return flipFitBookingDAOImpl.getAllBookings(userId);
    }

    public FlipFitBooking getBookingDetailsByBookingId(int bookingId){
        try {
            FlipFitBooking booking = flipFitBookingDAOImpl.getBookingDetailsByBookingId(bookingId);
            if (booking == null) {
                throw new BookingDetailsNotFoundException();
            }
            return booking;
        } catch (BookingDetailsNotFoundException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}