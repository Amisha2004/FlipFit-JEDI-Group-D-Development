/**
 * 
 */
package com.flipfit.business;

import java.util.List;

import com.flipfit.bean.FlipFitBooking;
import com.flipfit.bean.FlipFitGymCentre;
import com.flipfit.bean.FlipFitSlots;

/**
 * 
 */
public interface FlipFitCustomerBusinessInterface {
	  public List<FlipFitGymCentre> viewGymCentres();
	    public List<FlipFitSlots> viewBookedSlots(int userId);
	    public FlipFitBooking checkBookingConflicts(int userId, int slotTime);
	    public boolean makePayment(int userId);
}
