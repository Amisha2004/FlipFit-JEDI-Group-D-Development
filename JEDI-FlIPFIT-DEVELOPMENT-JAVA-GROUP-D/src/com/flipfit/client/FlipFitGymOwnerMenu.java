/**
 * 
 */
package com.flipfit.client;

import com.flipfit.bean.*;
import com.flipfit.business.*;
import com.flipfit.constants.ColorConstants;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

/**
 * Provides the interactive command-line menu for Gym Owner functionalities.
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
			FlipFitSlotsBusinessServices flipFitSlotsBusinessServices = new FlipFitSlotsBusinessServices();
			int choice = 0;
			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
			String formattedDateTime = now.format(formatter);

			while(true){
				System.out.println(ColorConstants.CYAN + "==========Gym Owner Menu==========" + ColorConstants.RESET);
				// Print the date/time and admin name
				System.out.println(ColorConstants.BLUE + String.format("%-10s %10s", formattedDateTime, gymOwner.getUserName()) + ColorConstants.RESET);
				System.out.println(ColorConstants.YELLOW + """
						Choose your choice:
						1 -> Add Gym Centre
						2 -> Add Slot
						3 -> Delete Slot
						4 -> View FlipFit Customers
						5 -> View my Centres
						6 -> Logout
						""" + ColorConstants.RESET);
				choice = in.nextInt();
				switch(choice){
					case 1: {
						System.out.println(ColorConstants.CYAN + "==========Add Gym Centre=========" + ColorConstants.RESET);
						System.out.println(ColorConstants.YELLOW + "Enter Gym name: " + ColorConstants.RESET);
						String gymName = in.next();
						System.out.println(ColorConstants.YELLOW + "Enter City name: "  + ColorConstants.RESET);
						String cityName = in.next();
						System.out.println(ColorConstants.YELLOW + "Enter State: " + ColorConstants.RESET);
						String state = in.next();
						System.out.println(ColorConstants.YELLOW + "Enter Pin Code: " + ColorConstants.RESET);
						String pincode = in.next();
						FlipFitGymCentre flipFitGymCentre = new FlipFitGymCentre();
						flipFitGymCentre.setGymName(gymName);
						flipFitGymCentre.setOwnerID(gymOwner.getUserId());
						flipFitGymCentre.setCity(cityName);
						flipFitGymCentre.setState(state);
						flipFitGymCentre.setPincode(pincode);
						flipFitGymCentre.setApprovalStatus(true);
						gymOwnerBusinessServices.addCentre(flipFitGymCentre);
						System.out.println("Gym Centre added, Now you can add slots.");
						break;
					}
					case 2: {
						System.out.println(ColorConstants.CYAN + "==========Add Slot in a Gym==========" + ColorConstants.RESET);
						System.out.println(ColorConstants.YELLOW + "Enter Gym centre ID: " + ColorConstants.RESET);
						int gymId = in.nextInt();
						System.out.println(ColorConstants.YELLOW + "Enter slot time: " + ColorConstants.RESET);
						System.out.print("Enter time (HH:MM:SS, e.g., 14:30:00): ");
						String timeString = in.next();

						Time sqlTime = null;
						try {
							// The valueOf() method expects "HH:MM:SS" format by default
							sqlTime = Time.valueOf(timeString);
							System.out.println("Parsed SQL Time: " + sqlTime);
						} catch (IllegalArgumentException e) {
							System.out.println("Invalid time format for java.sql.Time. Please use HH:MM:SS.");
							return;
						}
						System.out.println(ColorConstants.YELLOW + "Enter max capacity: "  + ColorConstants.RESET);
						int maxCapacity = in.nextInt();
						FlipFitSlots flipFitSlots = new FlipFitSlots();
						flipFitSlots.setGymId(gymId);
						flipFitSlots.setSlotStartTime(sqlTime);
						flipFitSlots.setMaxSeats(maxCapacity);
						flipFitSlots.setSeatsAvailable(maxCapacity);
						LocalDate today = LocalDate.now(); // Gets the current date (e.g., 2025-07-04)
						Date sqlDate = Date.valueOf(today); // Converts LocalDate to java.sql.Date
						flipFitSlots.setSlotDate(sqlDate);
						flipFitSlots = flipFitSlotsBusinessServices.addSlot(flipFitSlots);
						if(flipFitSlots==null) System.out.println(ColorConstants.RED + "Gym Slot not added, try again." + ColorConstants.RESET);
						System.out.println(ColorConstants.GREEN + "Gym Slot Added." + ColorConstants.RESET);
						break;
					}
					case 3: {
						System.out.println(ColorConstants.CYAN + "=========== Delete Slot ===========" + ColorConstants.RESET);
						System.out.print(ColorConstants.YELLOW + "Enter Gym Centre ID: "  + ColorConstants.RESET);
						int centreId = in.nextInt();
						System.out.print(ColorConstants.YELLOW + "Enter slot ID: " + ColorConstants.RESET);
						int slotId = in.nextInt();
						FlipFitSlots flipFitSlots = new FlipFitSlots();
						flipFitSlots.setGymId(centreId);
						flipFitSlots.setSlotId(slotId);
						if(flipFitSlotsBusinessServices.deleteSlot(flipFitSlots)){
							System.out.println(ColorConstants.GREEN + "Slot deleted successfully." + ColorConstants.RESET);
							break;
						}
						System.out.println(ColorConstants.RED + "Slot not deleted, try again." + ColorConstants.RESET);
						break;
					}
					case 4: {
						System.out.println(ColorConstants.CYAN + "=====View FlipFit Customers=====" + ColorConstants.RESET);
						List<FlipFitCustomer> customers = gymOwnerBusinessServices.getCustomerListByGymId(gymOwner.getUserId());
						if(customers.isEmpty()) {
							System.out.println("There are no customers to view this menu.");
							break;
						}
						for(FlipFitUser customer : customers) {
							System.out.println(ColorConstants.PURPLE + "Customer ID: " + customer.getUserId() + " Customer Name: " + customer.getUserName()+ ColorConstants.RESET);
						}
						break;
					}
					case 5: {
						System.out.println(ColorConstants.CYAN + "========View my Centres========" + ColorConstants.RESET);
						List<FlipFitGymCentre> gymCentres = gymOwnerBusinessServices.viewOwnCentres(gymOwner.getUserId());
						if(gymCentres.isEmpty()) {
							System.out.println("There are no centres to view this menu.");
						}
						for(FlipFitGymCentre gymCentre : gymCentres) {
							System.out.println(ColorConstants.PURPLE + "Gym Centre ID: " + gymCentre.getGymID() + " Gym Name: " + gymCentre.getGymName() + ColorConstants.RESET);
						}
//						System.out.println(ColorConstants.YELLOW + "Do you want to delete any Gym Centre? (Y/N): " + ColorConstants.RESET);
//						if(in.nextLine().equalsIgnoreCase("N")) break;
//						System.out.println(ColorConstants.YELLOW + "Enter Gym Centre ID: " + ColorConstants.RESET);
//						int gymId = in.nextInt();
//						boolean delete = gymOwnerBusinessServices.deleteGymCentre(gymId);
//						if(delete) System.out.println(ColorConstants.GREEN + "Gym Owner with ID: " + gymId + " is deleted" + ColorConstants.RESET);
//						else System.out.println(ColorConstants.GREEN + "Gym Owner with ID: " + gymId + " is not found" + ColorConstants.RESET);
						break;
					}
					case 6: {
						System.out.println(ColorConstants.GREEN + "Successfully Logged out" + ColorConstants.RESET);
						return;
					}
				}
			}
		}
		finally {

		}

         
	}

}
