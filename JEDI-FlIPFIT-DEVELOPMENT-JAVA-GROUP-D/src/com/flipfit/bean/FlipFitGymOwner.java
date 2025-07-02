/**
 * 
 */
package com.flipfit.bean;

import java.util.List;
/**
 * 
 */
public class FlipFitGymOwner extends FlipFitUser {
	public String gstNumber;
	public String aadharNumber;
    public boolean isApproved;
	public List<Integer>gymsOwned;
    
    /**
	 * @return the gymsOwned
	 */
	public List<Integer> getGymsOwned() {
		return gymsOwned;
	}
	/**
	 * @param gymsOwned the gymsOwned to set
	 */
	public void setGymsOwned(List<Integer> gymsOwned) {
		this.gymsOwned = gymsOwned;
	}
	/**
	 * @return the gstNumber
	 */
	public String getGstNumber() {
		return gstNumber;
	}
	/**
	 * @param gstNumber the gstNumber to set
	 */
	public void setGstNumber(String gstNumber) {
		this.gstNumber = gstNumber;
	}
	/**
	 * @return the aadharNumber
	 */
	public String getAadharNumber() {
		return aadharNumber;
	}
	/**
	 * @param aadharNumber the aadharNumber to set
	 */
	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}
	/**
	 * @return the isApproved
	 */
	public boolean isApproved() {
		return isApproved;
	}
	/**
	 * @param isApproved the isApproved to set
	 */
	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}
    
}
