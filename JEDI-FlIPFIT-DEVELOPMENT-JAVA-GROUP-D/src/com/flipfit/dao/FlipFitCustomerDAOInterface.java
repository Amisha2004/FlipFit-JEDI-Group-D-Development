package com.flipfit.dao;

import com.flipfit.bean.BookingStatus;
import com.flipfit.bean.FlipFitBooking;
import com.flipfit.bean.*;

import java.util.ArrayList;
import java.util.List;

public interface FlipFitCustomerDAOInterface {
    public List<FlipFitSlots> viewBookedSlots(int userID);

    /**
     * checkBookingConflicts
     * @param userId The ID of the user.
     * @return A FlipFitBooking object
     */
    public FlipFitBooking checkBookingConflicts(int userId, int slotTime);

    /**
     * viewCentres
     * Retrieves a list of all gym centres.
     * @return A list of FlipFitGymCentre objects.
     */
    public List<FlipFitGymCentre> viewGymCentres();

    /**
     * makePayment
     * @param userID The ID of the user.
     * @return True
     */
    public boolean makePayment(int userID);

    /**
     * viewPaymentDetails
     * @param userID
     */
    public void viewPaymentDetails(int userID);
}
