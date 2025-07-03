package com.flipfit.bean;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class FlipFitSlots {
	private int slotId;
	private int gymId;
    private LocalTime slotStartTime;
    private LocalDate slotDate;
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
	public LocalTime getSlotStartTime() {
		return slotStartTime;
	}
	/**
	 * @param slotStartTime the slotStartTime to set
	 */
	public void setSlotStartTime(LocalTime slotStartTime) {
		this.slotStartTime = slotStartTime;
	}
	/**
	 * @return the slotDate
	 */
	public LocalDate getSlotDate() {
		return slotDate;
	}
	/**
	 * @param slotDate the slotDate to set
	 */
	public void setSlotDate(LocalDate slotDate) {
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
