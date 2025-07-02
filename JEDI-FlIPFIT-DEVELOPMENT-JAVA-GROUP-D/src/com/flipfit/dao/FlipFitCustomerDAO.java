package com.flipfit.dao;

import com.flipfit.bean.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class FlipFitCustomerDAO {

    private static final List<FlipFitGymCentre> gymCentres = new ArrayList<>();
    private static final List<FlipFitBooking> bookings = new ArrayList<>();

    private static final AtomicInteger gymIdCounter = new AtomicInteger(0);
    private static final AtomicInteger bookingIdCounter = new AtomicInteger(100);

    static {
        // Initialize Gym Centres
        FlipFitGymCentre centre1 = new FlipFitGymCentre();
        centre1.setGymID(gymIdCounter.incrementAndGet());
        centre1.setGymName("Fitness Hub Koramangala");
        centre1.setOwnerID(2);
        centre1.setApprovalStatus(true);
        centre1.setCity("Bengaluru");
        centre1.setState("Karnataka");
        centre1.setPincode("560034");
        centre1.setSlot(Arrays.asList(900, 1000, 1100, 1700, 1800));
        gymCentres.add(centre1);

        FlipFitGymCentre centre2 = new FlipFitGymCentre();
        centre2.setGymID(gymIdCounter.incrementAndGet());
        centre2.setGymName("Power House Marathahalli");
        centre2.setOwnerID(2); // Also owned by owner1
        centre2.setApprovalStatus(true);
        centre2.setCity("Bengaluru");
        centre2.setState("Karnataka");
        centre2.setPincode("560037");
        centre2.setSlot(Arrays.asList(800, 900, 1600));
        gymCentres.add(centre2);

        FlipFitGymCentre centre3 = new FlipFitGymCentre();
        centre3.setGymID(gymIdCounter.incrementAndGet());
        centre3.setGymName("Mumbai Strength Gym");
        centre3.setOwnerID(3); // Owned by owner2
        centre3.setApprovalStatus(false);
        centre3.setCity("Mumbai");
        centre3.setState("Maharashtra");
        centre3.setPincode("400001");
        centre3.setSlot(Arrays.asList(700, 800, 1200, 1900));
        gymCentres.add(centre3);

        FlipFitBooking fb1 = new FlipFitBooking();
        fb1.setBookingId(bookingIdCounter.incrementAndGet());
        fb1.setUserId(4);
        fb1.setSlotId(12);
        fb1.setStatus(BookingStatus.CONFIRMED);
        bookings.add(fb1);
    }


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
        System.out.println("Payment simulation: Payment for User ID " + userID + " processed.");
        return true;
    }
}