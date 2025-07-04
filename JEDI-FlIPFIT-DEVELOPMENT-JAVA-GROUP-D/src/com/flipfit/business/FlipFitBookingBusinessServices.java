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

    public FlipFitBookingBusinessServices() {
        this.flipFitBookingDAOImpl = new FlipFitBookingDAOImpl();
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
       try{
           if(!flipFitBookingDAOImpl.deleteBooking(bookingId, slotId)){
               throw new BookingCancellationFailedException();
           }
           return flipFitBookingDAOImpl.deleteBooking(bookingId, slotId);
       }
        catch(BookingCancellationFailedException e){
           System.out.println(e.getMessage());
        }
       return false;
    }
    public List<FlipFitBooking> getAllBookings(int userId){
        return flipFitBookingDAOImpl.getAllBookings(userId);
    }

    public FlipFitBooking getBookingDetailsByBookingId(int bookingId){
        try {
            if (flipFitBookingDAOImpl.getBookingDetailsByBookingId(bookingId) == null) {
                throw new BookingDetailsNotFoundException();
            }
            return flipFitBookingDAOImpl.getBookingDetailsByBookingId(bookingId);
        }
        catch (BookingDetailsNotFoundException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}