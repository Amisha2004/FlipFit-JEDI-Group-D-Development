/**
 * 
 */
package com.flipfit.bean;

import java.util.List;

/**
 * Defines Flipfit Customer
 */
public class FlipFitCustomer extends FlipFitUser{

	public String paymentInfo;
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
    
}
