/**
 * 
 */
package com.flipfit.business;

import java.sql.Time;
import java.time.LocalTime;
import java.util.List;

import com.flipfit.bean.FlipFitBooking;
import com.flipfit.bean.FlipFitGymCentre;
import com.flipfit.bean.FlipFitPayments;
import com.flipfit.bean.FlipFitSlots;

/**
 * 
 */
public interface FlipFitCustomerBusinessInterface {
	  public List<FlipFitGymCentre> viewGymCentres();
	    public List<FlipFitSlots> viewBookedSlots(int userId);
	    public FlipFitBooking checkBookingConflicts(int userId, Time slotTime);
	    public boolean makePayment(int userId, String paymentInfo);
		public List<FlipFitPayments> viewPaymentDetails(int userID);
}
