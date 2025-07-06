/**
 * 
 */
package com.flipfit.bean;

import java.util.List;
/**
 * Defines FlipFit Gym owner
 */
public class FlipFitGymOwner extends FlipFitUser {
	public String gstNumber;
	public String panNumber;
	public String aadharNumber;
    public boolean isApproved;

	public String getPanNumber() {
		return panNumber;
	}

	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
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
