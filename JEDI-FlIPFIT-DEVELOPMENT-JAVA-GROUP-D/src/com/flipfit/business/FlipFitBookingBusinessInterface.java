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
	public FlipFitBooking makeBooking(FlipFitBooking flipFitBooking);
	public boolean deleteBooking(int bookingId, int slotId);
	public List<FlipFitBooking> getAllBookings(int userId);

	public FlipFitBooking getBookingDetailsByBookingId(int bookingId);
}
