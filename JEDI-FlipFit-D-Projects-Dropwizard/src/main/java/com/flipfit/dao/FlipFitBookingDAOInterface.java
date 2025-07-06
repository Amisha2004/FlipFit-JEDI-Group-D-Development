package com.flipfit.dao;

import com.flipfit.bean.FlipFitBooking;

import java.util.List;

public interface FlipFitBookingDAOInterface {
    public FlipFitBooking makeBooking(FlipFitBooking booking);

    public boolean deleteBooking(int bookingId, int slotId);

    public List<FlipFitBooking> getAllBookings(int userId);

    public FlipFitBooking getBookingDetailsByBookingId(int bookingId);
}
