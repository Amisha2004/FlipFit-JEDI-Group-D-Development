package com.flipfit.dao;

import com.flipfit.bean.FlipFitBooking;

import java.util.List;

public interface FlipFitBookingDAOInterface {
    public FlipFitBooking makeBooking(FlipFitBooking booking);

    public boolean deleteBooking(int bookingID);

    public List<FlipFitBooking> getAllBookings(int userId);

    public List<FlipFitBooking> getBookingDetailsByBookingId(int bookingId);
}
