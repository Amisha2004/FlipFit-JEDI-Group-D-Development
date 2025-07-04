/**
 *
 */
package com.flipfit.client;

import com.flipfit.bean.*;
import com.flipfit.business.FlipFitAdminBusinessServices;
import com.flipfit.constants.ColorConstants;

import java.sql.SQLOutput;
import java.util.*;

/**
 *
 */
public class FlipFitAdminMenu {

	/**
	 * @param admin
	 */
	public static void getFlipFitAdminMenu(FlipFitUser admin) {
		// TODO Auto-generated method stud

		try{
			Scanner in = new Scanner(System.in);
			FlipFitAdminBusinessServices adminBusinessServices = new FlipFitAdminBusinessServices();
			int choice = 0;
			while(true){
				System.out.println(ColorConstants.CYAN + "==========Admin Menu==========" + ColorConstants.CYAN);
				System.out.println(ColorConstants.YELLOW + """
						Choose your choice:
						1 -> View Pending Gym Owner requests
						2 -> View Approved Gym Owner requests
						3 -> View FlipFit Customers
						4 -> View all Centres using Owner Id
						5 -> Logout
						""" + ColorConstants.RESET);
				choice = in.nextInt();
				switch(choice){
					case 1: {
						System.out.println(ColorConstants.CYAN + "======View Pending Gym Owner requests====="  + ColorConstants.RESET);
						List<FlipFitGymOwner> pendingGymOwners = adminBusinessServices.getPendingGymOwnerList();
						if(pendingGymOwners.isEmpty()) {
							System.out.println("There are no pending gym owner to view this menu.");
							break;
						}
						for(FlipFitGymOwner pendingGymOwner : pendingGymOwners) {
							System.out.println(ColorConstants.PURPLE + "Owner ID: " + pendingGymOwner.getUserId() + " Owner Name: " + pendingGymOwner.getUserName() + ColorConstants.RESET);

						}
						System.out.println(ColorConstants.YELLOW + "Do you want to approve any Gym Owner? (Y/N): " + ColorConstants.RESET);
						if(in.next().equalsIgnoreCase("N")) break;
						System.out.println(ColorConstants.YELLOW + "Enter Gym Owner ID you want to approve: " + ColorConstants.RESET);
						int ownerId = in.nextInt();
						boolean validate = adminBusinessServices.validateOwner(ownerId);
						if(validate) System.out.println(ColorConstants.GREEN + "Gym Owner with ID: " + ownerId + " is approved" + ColorConstants.RESET);
						else System.out.println(ColorConstants.RED + "Gym Owner with ID: " + ownerId + " is not found" + ColorConstants.RESET);
						break;
					}
					case 2: {
						System.out.println(ColorConstants.CYAN + "=====View Approved Gym Owner requests=====" + ColorConstants.RESET);
						List<FlipFitGymOwner> approvedGymOwners = adminBusinessServices.getApprovedGymOwnerList();
						if(approvedGymOwners.isEmpty()) {
							System.out.println("There are no approved gym owner to view this menu.");
							break;
						}
						for(FlipFitGymOwner approvedGymOwner : approvedGymOwners) {
							System.out.println(ColorConstants.PURPLE + "Owner ID: " + approvedGymOwner.getUserId() + " Owner Name: " + approvedGymOwner.getUserName() + ColorConstants.RESET);
						}
						System.out.println(ColorConstants.YELLOW + "Do you want to delete any Gym Owner? (Y/N): " + ColorConstants.RESET);
						if(in.next().equalsIgnoreCase("N")) break;
						System.out.println(ColorConstants.YELLOW + "Enter Gym Owner ID you want to delete: " + ColorConstants.RESET);
						int ownerId = in.nextInt();
						boolean delete = adminBusinessServices.deleteOwner(ownerId);
						if(delete) System.out.println(ColorConstants.GREEN + "Gym Owner with ID: " + ownerId + " is deleted" + ColorConstants.RESET);
						else System.out.println(ColorConstants.GREEN + "Gym Owner with ID: " + ownerId + " is not found" + ColorConstants.RESET);
						break;
					}
					case 3: {
						System.out.println(ColorConstants.CYAN + "=====View FlipFit Customers=====" + ColorConstants.RESET);
						List<FlipFitUser> users = adminBusinessServices.getUserList();
						if(users.isEmpty()) {
							System.out.println("There are no users to view this menu.");
							break;
						}
						for(FlipFitUser user : users) {
							System.out.println(ColorConstants.PURPLE + "Owner ID: " + user.getUserId() + " Owner Name: " + user.getUserName()+ ColorConstants.RESET);
						}
						break;
					}
					case 4: {
						System.out.println(ColorConstants.CYAN + "=====View My Centres=====" + ColorConstants.RESET);
						System.out.println(ColorConstants.YELLOW + "Enter Owner ID to View Centres: " + ColorConstants.RESET);
						int ownerId = in.nextInt();
						List<FlipFitGymCentre> gymCentres = adminBusinessServices.getGymCentreUsingOwnerId(ownerId);
						if(gymCentres.isEmpty()) {
							System.out.println("There are no gym centre to view this menu.");
						}
						else{
							// --- ADD THE TABLE HEADING HERE ---
							System.out.println(ColorConstants.CYAN + "--------------------------------------" + ColorConstants.RESET);
							System.out.printf(ColorConstants.CYAN + "%-10s %-25s%n", "Gym ID", "Gym Name" + ColorConstants.RESET);
							System.out.println(ColorConstants.CYAN + "--------------------------------------" + ColorConstants.RESET);
                            // --- END TABLE HEADING ---
							for(FlipFitGymCentre  gymCentre : gymCentres ){
								System.out.println(ColorConstants.PURPLE + gymCentre.getGymID() + " " + gymCentre.getGymName()+ ColorConstants.RESET);
							}
						}
						break;
					}
					case 5: {
						System.out.println(ColorConstants.GREEN + "Successfully logged out" + ColorConstants.RESET);
						return;
					}
				}
			}
		}
		finally {

		}


	}

}
