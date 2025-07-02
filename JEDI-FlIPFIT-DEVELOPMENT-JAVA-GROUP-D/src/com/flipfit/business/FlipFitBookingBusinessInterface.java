/**
 * 
 */
package com.flipfit.business;

import com.flipfit.bean.FlipFitBooking;

/**
 * 
 */
public interface FlipFitBookingBusinessInterface {
	 public FlipFitBooking makeBooking(int userID, int centreID, int startTime);

	 public boolean deleteBooking(int bookingId);
}
