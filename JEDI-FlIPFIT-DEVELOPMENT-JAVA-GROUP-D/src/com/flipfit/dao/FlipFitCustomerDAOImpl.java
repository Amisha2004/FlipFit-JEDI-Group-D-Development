package com.flipfit.dao;

import com.flipfit.bean.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class FlipFitCustomerDAOImpl implements FlipFitCustomerDAOInterface{

    private final List<FlipFitGymCentre> gymCentres = new ArrayList<>();
    private final List<FlipFitBooking> bookings = new ArrayList<>();

    private final AtomicInteger gymIdCounter = new AtomicInteger(0);
    private final AtomicInteger bookingIdCounter = new AtomicInteger(100);


    /**
     * viewBookedSlots
     * @param userID The ID of the user.
     * @return A list of FlipFitBooking objects for the specified user.
     */
    public List<FlipFitBooking> viewBookedSlots(int userID) {
        List<FlipFitBooking> userBookings = new ArrayList<>();
        for (FlipFitBooking booking : bookings) {
            if (booking.getUserId() == userID && booking.getStatus() == BookingStatus.CONFIRMED) {
                userBookings.add(booking);
            }
        }
        return userBookings;
    }

    /**
     * checkBookingConflicts
     * @param userId The ID of the user.
     * @return A FlipFitBooking object
     */
    public FlipFitBooking checkBookingConflicts(int userId) {

        for (FlipFitBooking booking : bookings) {
            if (booking.getUserId() == userId) {
                return booking;
            }
        }
        return null;
    }

    /**
     * viewCentres
     * Retrieves a list of all gym centres.
     * @return A list of FlipFitGymCentre objects.
     */
    public List<FlipFitGymCentre> viewGymCentres() {
        return gymCentres;
    }

    /**
     * makePayment
     * @param userID The ID of the user.
     * @return True
     */
    public boolean makePayment(int userID) {
        return false;
    }

    /**
     * viewPaymentDetails
     * @param userID
     */
    public void viewPaymentDetails(int userID) {

    }
}