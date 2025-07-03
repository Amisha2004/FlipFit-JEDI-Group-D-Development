/**
 * 
 */
package com.flipfit.business;

import com.flipfit.bean.FlipFitBooking;

import java.util.List;

/**
 * 
 */
public interface FlipFitBookingBusinessInterface {
	public FlipFitBooking makeBooking(int userID, int centreID, int startTime);
	public boolean deleteBooking(int bookingId);
	public List<FlipFitBooking> getAllBookings(int userId);

	public List<FlipFitBooking> getBookingDetailsByBookingId(int bookingId);
}
