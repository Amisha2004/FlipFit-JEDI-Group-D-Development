/**
 * 
 */
package com.flipfit.client;

import com.flipfit.business.*;

/**
 * 
 */
public class FlipFitCustomerMenu {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        System.out.println("I am Cutomer Client.");
        FlipFitCustomerBusinessServices business = new FlipFitCustomerBusinessServices();
 		business.checkBookingConflicts(12);
 		
 		System.out.println("Make Payment-> "+ business.makePayment(10));
	}

}
