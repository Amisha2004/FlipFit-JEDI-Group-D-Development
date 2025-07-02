/**
 * 
 */
package com.flipfit.client;

import com.flipfit.bean.*;
import com.flipfit.business.FlipFitAdminBusinessServices;

import java.util.List;

/**
 * 
 */
public class FlipFitAdminMenu {

	/**
	 * @param admin
	 */
	public static void getFlipFitAdminMenu(FlipFitAdmin admin) {
		// TODO Auto-generated method stub
		System.out.println("You are in Admin menu.");
		FlipFitAdminBusinessServices adminBusinessServices = new FlipFitAdminBusinessServices();
		List<FlipFitGymOwner> pendingGymOwners = adminBusinessServices.getPendingGymOwnerList();
		if(pendingGymOwners.isEmpty()) {
			System.out.println("There are no pending gym owner to view this menu.");
		}
		else{
			for(FlipFitGymOwner pendingGymOwner : pendingGymOwners){
				System.out.println(pendingGymOwner.getUserId() + " " + pendingGymOwner.getUserName());
            }
		}
		List<FlipFitGymCentre> gymCentres = adminBusinessServices.getGymCentreUsingOwnerId(101);
		if(gymCentres.isEmpty()) {
			System.out.println("There are no gym centre to view this menu.");
		}
		else{
			for(FlipFitGymCentre  gymCentre : gymCentres ){
				System.out.println(gymCentre.getGymID() + " " + gymCentre.getGymName());
			}
		}
	}

}
