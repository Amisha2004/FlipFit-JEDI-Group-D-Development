/**
 * 
 */
package com.flipfit.client;

import com.flipfit.bean.*;
import com.flipfit.business.*;

import java.util.List;
import java.util.Scanner;

/**
 * 
 */
public class FlipFitCustomerMenu {

	public static void getFlipFitCustomerMenu(FlipFitCustomer gymCustomer) {
		// TODO Auto-generated method stub
        System.out.println("I am Customer Client.");
        FlipFitCustomerBusinessServices services = new FlipFitCustomerBusinessServices();

		Scanner in = new Scanner(System.in);

		List<FlipFitGymCentre> gymCentres = services.viewGymCentres();
		if (gymCentres.isEmpty()) {
			System.out.println("No gym centres available at the moment.");
		} else {
			System.out.println("\n--- Available Gym Centres ---");
			for (FlipFitGymCentre centre : gymCentres) {
				System.out.println(centre.getGymName());
			}

		}
		System.out.println("\n-----------------------------");

		System.out.print("Enter User Id to see all booked slot: ");
		int userId = in.nextInt();
//		List<FlipFitBooking> bookedSlots = services.viewBookedSlots(userId);
//
//		if (bookedSlots.isEmpty()) {
//			System.out.println("No booking is available at the moment.");
//		} else {
//			System.out.println("\n--- Available Bookings ---");
//			for (FlipFitBooking booking : bookedSlots) {
//				System.out.println("Booking for user with User Id "+ booking.getUserId() +
//						" has been made with booking Id " + booking.getBookingId() + ".\n" +
//						"and the Status is: " + booking.getStatus());
//			}
//			System.out.println("\n-----------------------------");
//		}

	}

}
