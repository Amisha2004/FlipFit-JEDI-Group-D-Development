/**
 * 
 */
package com.flipfit.bean;

import java.util.List;

/**
 * 
 */
public class FlipFitGymCentre {
	private int gymID;
	private String gymName;
    private int ownerID;
    private boolean approvalStatus;
	private String city;
    private String state;
    private String pincode;
    
	/**
	 * @return the gymID
	 */
	public int getGymID() {
		return gymID;
	}
	/**
	 * @param gymID the gymID to set
	 */
	public void setGymID(int gymID) {
		this.gymID = gymID;
	}
	/**
	 * @return the gymName
	 */
	public String getGymName() {
		return gymName;
	}
	/**
	 * @param gymName the gymName to set
	 */
	public void setGymName(String gymName) {
		this.gymName = gymName;
	}
	/**
	 * @return the ownerID
	 */
	public int getOwnerID() {
		return ownerID;
	}
	/**
	 * @param ownerID the ownerID to set
	 */
	public void setOwnerID(int ownerID) {
		this.ownerID = ownerID;
	}
	  /**
		 * @return the approvalStatus
		 */
		public boolean isApprovalStatus() {
			return approvalStatus;
		}
		/**
		 * @param approvalStatus the approvalStatus to set
		 */
		public void setApprovalStatus(boolean approvalStatus) {
			this.approvalStatus = approvalStatus;
		}
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @return the pincode
	 */
	public String getPincode() {
		return pincode;
	}
	/**
	 * @param pincode the pincode to set
	 */
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
}
