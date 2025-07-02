/**
 * 
 */
package com.flipfit.bean;

import java.util.List;

/**
 * 
 */
public class FlipFitCustomer extends FlipFitUser{

	public String paymentInfo;
    public List<Integer>bookedSlotId;
    /**
	 * @return the paymentInfo
	 */
	public String getPaymentInfo() {
		return paymentInfo;
	}
	/**
	 * @param paymentInfo the paymentInfo to set
	 */
	public void setPaymentInfo(String paymentInfo) {
		this.paymentInfo = paymentInfo;
	}
	/**
	 * @return the bookedSlotId
	 */
	public List<Integer> getBookedSlotId() {
		return bookedSlotId;
	}
	/**
	 * @param bookedSlotId the bookedSlotId to set
	 */
	public void setBookedSlotId(List<Integer> bookedSlotId) {
		this.bookedSlotId = bookedSlotId;
	}
    
}
