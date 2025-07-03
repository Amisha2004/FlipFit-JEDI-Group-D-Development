package com.flipfit.dao;

import com.flipfit.bean.*;

import java.util.List;

public class FlipFitBookingDAOImpl implements FlipFitBookingDAOInterface{
    public FlipFitBooking makeBooking(FlipFitBooking booking){
        System.out.println("Making Booking");
        return null;
    }

    public boolean deleteBooking(int bookingID){
        System.out.println("Deleting Booking");
        return true;
    }

    public List<FlipFitBooking> getAllBookings(int userId){
        System.out.println("Getting All Bookings");
        return null;
    }

    public FlipFitBooking getBookingDetailsByBookingId(int bookingId){
        System.out.println("Getting Booking Details by Id");
        return null;
    }
}
