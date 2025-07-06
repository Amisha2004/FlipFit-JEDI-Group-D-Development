/**
 * 
 */
package com.flipfit.bean;

// sravya first comnmit
/**
 * Defines FlipFit Booking
 */
public class FlipFitBooking {
	private int bookingId;
	public int userId;
    private int slotId;
    private BookingStatus status;
    /**
	 * @return the bookingId
	 */
	public int getBookingId() {
		return bookingId;
	}
	/**
	 * @param bookingId the bookingId to set
	 */
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}
	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	/**
	 * @return the slotId
	 */
	public int getSlotId() {
		return slotId;
	}
	/**
	 * @param slotId the slotId to set
	 */
	public void setSlotId(int slotId) {
		this.slotId = slotId;
	}
	/**
	 * @return the status
	 */
	public BookingStatus getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(BookingStatus status) {
		this.status = status;
	}
    
}
