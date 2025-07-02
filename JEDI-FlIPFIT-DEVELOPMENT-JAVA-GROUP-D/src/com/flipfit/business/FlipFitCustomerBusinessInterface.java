/**
 * 
 */
package com.flipfit.business;

import java.util.List;

import com.flipfit.bean.FlipFitBooking;
import com.flipfit.bean.FlipFitGymCentre;

/**
 * 
 */
public interface FlipFitCustomerBusinessInterface {
	  public List<FlipFitGymCentre> viewGymCentres();
	    public List<FlipFitBooking> viewBookedSlots(int userId);
	    public FlipFitBooking checkBookingConflicts(int userId);
	    public boolean makePayment(int userId);
}
