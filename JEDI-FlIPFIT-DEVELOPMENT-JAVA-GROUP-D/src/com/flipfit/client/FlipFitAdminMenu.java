/**
 * 
 */
package com.flipfit.client;

import com.flipfit.bean.*;
import com.flipfit.business.FlipFitAdminBusinessServices;

import java.sql.SQLOutput;
import java.util.*;

/**
 * 
 */
public class FlipFitAdminMenu {

	/**
	 * @param admin
	 */
	public static void getFlipFitAdminMenu(FlipFitAdmin admin) {
		// TODO Auto-generated method stud

		try{
			Scanner in = new Scanner(System.in);
			FlipFitAdminBusinessServices adminBusinessServices = new FlipFitAdminBusinessServices();
			int choice = 0;
			while(true){
				System.out.println("Admin Menu");
				System.out.println("""
						Choose your choice:
						1 -> View Pending Gym Owner requests
						2 -> View Approved Gym Owner requests
						3 -> View FlipFit Customers
						4 -> View all Centres using Owner Id
						5 -> Logout
						""");
				choice = in.nextInt();
				switch(choice){
					case 1: {
						System.out.println("View Pending Gym Owner requests: ");
						List<FlipFitGymOwner> pendingGymOwners = adminBusinessServices.getPendingGymOwnerList();
						if(pendingGymOwners.isEmpty()) {
							System.out.println("There are no pending gym owner to view this menu.");
							break;
						}
						for(FlipFitGymOwner pendingGymOwner : pendingGymOwners) {
							System.out.println("Owner ID: " + pendingGymOwner.getUserId() + " Owner Name: " + pendingGymOwner.getUserName());

						}
						System.out.println("Enter Owner Id you wish to approve: ");
						int ownerId = in.nextInt();
						boolean validate = adminBusinessServices.validateOwner(ownerId);
						if(validate) System.out.println("Gym Owner with ID: " + ownerId + " is approved");
						else System.out.println("Gym Owner with ID: " + ownerId + " is not found");
						break;
					}
					case 2: {
						System.out.println("View Approved Gym Owner requests: ");
						List<FlipFitGymOwner> approvedGymOwners = adminBusinessServices.getApprovedGymOwnerList();
						if(approvedGymOwners.isEmpty()) {
							System.out.println("There are no approved gym owner to view this menu.");
							break;
						}
						for(FlipFitGymOwner approvedGymOwner : approvedGymOwners) {
							System.out.println("Owner ID: " + approvedGymOwner.getUserId() + " Owner Name: " + approvedGymOwner.getUserName());
						}
						System.out.println("Enter Owner Id you wish to delete: ");
						int ownerId = in.nextInt();
						boolean delete = adminBusinessServices.deleteOwner(ownerId);
						if(delete) System.out.println("Gym Owner with ID: " + ownerId + " is deleted");
						else System.out.println("Gym Owner with ID: " + ownerId + " is not found");
						break;
					}
					case 3: {
						System.out.println("View FlipFit Customers: ");
						List<FlipFitUser> users = adminBusinessServices.getUserList();
						if(users.isEmpty()) {
							System.out.println("There are no users to view this menu.");
							break;
						}
						for(FlipFitUser user : users) {
							System.out.println("Owner ID: " + user.getUserId() + " Owner Name: " + user.getUserName());
						}
						break;
					}
					case 4: {
						System.out.println("Enter Owner ID to View Centres: ");
						int ownerId = in.nextInt();
						List<FlipFitGymCentre> gymCentres = adminBusinessServices.getGymCentreUsingOwnerId(ownerId);
						if(gymCentres.isEmpty()) {
							System.out.println("There are no gym centre to view this menu.");
						}
						else{
							for(FlipFitGymCentre  gymCentre : gymCentres ){
								System.out.println(gymCentre.getGymID() + " " + gymCentre.getGymName());
							}
						}
						break;
					}
					case 5: {
						System.out.println("Successfully logged out");
						return;
					}
				}
			}
		}
		finally {

		}


	}

}
