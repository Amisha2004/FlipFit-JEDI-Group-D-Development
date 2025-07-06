package com.flipfit.bean;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Define FlipFit Slots
 */
public class FlipFitSlots {
	private int slotId;
	private int gymId;
    private Time slotStartTime;
    private Date slotDate;
    private int seatsAvailable;
    private int maxSeats;
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
	 * @return the gymId
	 */
	public int getGymId() {
		return gymId;
	}
	/**
	 * @param gymId the gymId to set
	 */
	public void setGymId(int gymId) {
		this.gymId = gymId;
	}
	/**
	 * @return the slotStartTime
	 */
	public Time getSlotStartTime() {
		return slotStartTime;
	}
	/**
	 * @param slotStartTime the slotStartTime to set
	 */
	public void setSlotStartTime(Time slotStartTime) {
		this.slotStartTime = slotStartTime;
	}
	/**
	 * @return the slotDate
	 */
	public Date getSlotDate() {
		return slotDate;
	}
	/**
	 * @param slotDate the slotDate to set
	 */
	public void setSlotDate(Date slotDate) {
		this.slotDate = slotDate;
	}
	/**
	 * @return the seatsAvailable
	 */
	public int getSeatsAvailable() {
		return seatsAvailable;
	}
	/**
	 * @param seatsAvailable the seatsAvailable to set
	 */
	public void setSeatsAvailable(int seatsAvailable) {
		this.seatsAvailable = seatsAvailable;
	}
	/**
	 * @return the maxSeats
	 */
	public int getMaxSeats() {
		return maxSeats;
	}
	/**
	 * @param maxSeats the maxSeats to set
	 */
	public void setMaxSeats(int maxSeats) {
		this.maxSeats = maxSeats;
	}
	
}
