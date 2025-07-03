/**
 * 
 */
package com.flipfit.client;

import com.flipfit.bean.*;
import com.flipfit.business.*;

import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

/**
 * 
 */
public class FlipFitGymOwnerMenu {

	/**
	 * @param gymOwner
	 */
	public static void getFlipFitGymOwnerMenu(FlipFitUser gymOwner) {
		// TODO Auto-generated method stub
		try{
			Scanner in = new Scanner(System.in);
			FlipFitGymOwnerBusinessServices gymOwnerBusinessServices = new FlipFitGymOwnerBusinessServices();
			FlipFitCustomerBusinessServices customerBusinessServices = new FlipFitCustomerBusinessServices();
			int choice = 0;
			while(true){
				System.out.println("Gym Owner Menu");
				System.out.println("""
						Choose your choice:
						1 -> Add Centre
						2 -> Add Slot
						3 -> View FlipFit Customers
						4 -> View my Centres
						5 -> Logout
						""");
				choice = in.nextInt();
				switch(choice){
					case 1: {
						System.out.println("Add Gym");
						System.out.println("Enter Gym name: ");
						String gymName = in.next();
						System.out.println("Enter city name: ");
						String cityName = in.next();
						System.out.println("Enter state: ");
						String state = in.next();
						System.out.println("Enter pincode: ");
						String pincode = in.next();
						FlipFitGymCentre flipFitGymCentre = new FlipFitGymCentre();
						flipFitGymCentre.setGymName(gymName);
						flipFitGymCentre.setOwnerID(gymOwner.getUserId());
						flipFitGymCentre.setCity(cityName);
						flipFitGymCentre.setState(state);
						flipFitGymCentre.setPincode(pincode);
						gymOwnerBusinessServices.addCentre(flipFitGymCentre);
						System.out.println("Gym Centre added, Now you can add slots.");
						break;
					}
					case 2: {
						System.out.println("Add slot in a gym");
						System.out.println("Enter Gym centre ID: ");
						int gymId = in.nextInt();
						System.out.println("Enter slot time: ");
						LocalTime localTime = LocalTime.parse(in.next());
						System.out.println("Enter max capacity: ");
						int maxCapacity = in.nextInt();
						FlipFitSlots flipFitSlots = new FlipFitSlots();
						flipFitSlots.setGymId(gymId);
						flipFitSlots.setSlotStartTime(localTime);
						flipFitSlots.setMaxSeats(maxCapacity);
						flipFitSlots.setSeatsAvailable(maxCapacity);
						System.out.println("Slot added.");
						break;
					}
					case 3: {
						System.out.println("View FlipFit Customers: ");
						List<FlipFitCustomer> customers = gymOwnerBusinessServices.getCustomerListByGymId(gymOwner.getUserId());
						if(customers.isEmpty()) {
							System.out.println("There are no customers to view this menu.");
							break;
						}
						for(FlipFitCustomer customer : customers) {
							System.out.println("Owner ID: " + customer.getUserId() + " Owner Name: " + customer.getUserName());
						}
						break;
					}
					case 4: {
						System.out.println("View my Centres: ");
//						List<FlipFitGymCentre> gymCentres = gymOwnerBusinessServices.viewOwnCentres(gymOwner.getUserId());
//						if(gymCentres.isEmpty()) {
//							System.out.println("There are no centres to view this menu.");
//						}
//						for(FlipFitGymCentre gymCentre : gymCentres) {
//							System.out.println("Gym Centre ID: " + gymCentre.getGymID() + " Gym Name: " + gymCentre.getGymName());
//						}
//						break;
					}
					case 5: {
						System.out.println("Logout");
						return;
					}
				}
			}
		}
		finally {

		}

         
	}

}
