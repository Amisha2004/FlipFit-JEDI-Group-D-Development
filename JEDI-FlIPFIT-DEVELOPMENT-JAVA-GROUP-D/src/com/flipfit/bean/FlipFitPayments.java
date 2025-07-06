/**
 * 
 */
package com.flipfit.bean;

/**
 * Define FlipFit Payments
 */
public class FlipFitPayments {
	private int paymentID;
	private int userID;
	private String paymentInfo;

	/**
	 * @return the userID
	 */
	public int getUserID() {
		return userID;
	}
	/**
	 * @param userID the userID to set
	 */
	public void setUserID(int userID) {
		this.userID = userID;
	}
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

	public int getPaymentID() {
		return paymentID;
	}


	public void setPaymentID(int paymentID) {
		this.paymentID = paymentID;
	}

}
