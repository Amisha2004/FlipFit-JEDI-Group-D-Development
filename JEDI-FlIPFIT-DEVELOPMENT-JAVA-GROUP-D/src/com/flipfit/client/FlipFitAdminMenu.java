/**
 * 
 */
package com.flipfit.client;

import com.flipfit.business.*;
/**
 * 
 */
public class FlipFitAdminMenu {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FlipFitUserBusinessServices userBusiness = new FlipFitUserBusinessServices();
        userBusiness.logIn("Amisha", "password");
        
        FlipFitAdminBusinessServices adminBusiness = new FlipFitAdminBusinessServices();
        adminBusiness.getPendingGymOwnerList();
        
        adminBusiness.getApprovedGymOwnerList();
        
        adminBusiness.getCustomerList();
        
        adminBusiness.validateOwner(1);
        
        adminBusiness.deleteOwner(1);
        
        adminBusiness.getGymCentreUsingOwnerId(1);
	}

}
