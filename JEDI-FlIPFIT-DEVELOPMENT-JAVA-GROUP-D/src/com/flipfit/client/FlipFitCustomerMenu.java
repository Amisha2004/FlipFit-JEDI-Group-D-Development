/**
 * 
 */
package com.flipfit.client;

import com.flipfit.bean.*;
import com.flipfit.business.*;
import com.flipfit.constants.ColorConstants;
import com.flipfit.dao.FlipFitBookingDAOImpl;
import com.flipfit.dao.FlipFitCustomerDAOImpl;
import com.flipfit.dao.FlipFitGymCentreDAOImpl;
import com.flipfit.dao.FlipFitSlotDAOImpl;
import com.flipfit.exceptions.BookingCancellationFailedException;
import com.flipfit.exceptions.BookingDetailsNotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

/**
 * Provides the interactive command-line menu for FlipFit Customer functionalities.
 */
public class FlipFitCustomerMenu {
	private static final FlipFitCustomerBusinessInterface customerBusiness = new FlipFitCustomerBusinessServices();
	private static final FlipFitGymCentreBusinessInterface gymCentreBusiness = new FlipFitGymCentreBusinessServices();
	private static final FlipFitBookingBusinessInterface bookingBusiness = new FlipFitBookingBusinessServices();
	private static final FlipFitSlotsBusinessInterface slotsBusiness = new FlipFitSlotsBusinessServices();
	private static final Scanner sc = new Scanner(System.in);

	public static void getFlipFitCustomerMenu(FlipFitUser customer) {
		int userId = customer.getUserId();
		int choice;
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		String formattedDateTime = now.format(formatter);

		do {

			System.out.println(ColorConstants.CYAN + " ==============Admin Menu================" + ColorConstants.RESET);
			System.out.println(ColorConstants.BLUE + String.format("%-10s %10s", formattedDateTime, customer.getUserName()) + ColorConstants.RESET);
			System.out.println(ColorConstants.YELLOW + """
			1. Book a Slot
			2. View My Bookings
			3. Cancel a Booking
			4. View My Payment History
			5. Logout
			Enter your choice: """ + ColorConstants.RESET);

			choice = sc.nextInt();
			sc.nextLine(); // Consume the newline character

			switch (choice) {
				case 1:
					bookASlot(userId);
					break;
				case 2:
					viewMyBookings(userId);
					break;
				case 3:
					cancelBooking(userId);
					break;
				case 4:
					viewPaymentHistory(userId);
					break;
				case 5:
					System.out.println("Logging out. Thank you for using FlipFit!");
					return;
				default:
					System.out.println("Invalid choice entered. Please try again.");
			}
		} while (choice != 5);
	}

	/**
	 * Handles the entire workflow for a customer to book a slot.
	 * @param userId The ID of the logged-in customer.
	 */
	private static void bookASlot(int userId) {
		// 1. View Gym Centres
		System.out.println("\n--- Step 1: Select a Gym Centre ---");
		List<FlipFitGymCentre> centreList = customerBusiness.viewGymCentres();
		if (centreList.isEmpty()) {
			System.out.println("Sorry, no gym centres are available at the moment.");
			return;
		}
		centreList.forEach(centre ->
				System.out.println("Centre ID: " + centre.getGymID() + ", Name: " + centre.getGymName() + ", City: " + centre.getCity()));

		System.out.print("\nEnter the Centre ID you wish to book a slot in: ");
		int centreId = sc.nextInt();
		sc.nextLine();

		FlipFitGymCentre centre = centreList.stream()
				.filter(c -> c.getGymID() == centreId)
				.findFirst()
				.orElse(null);

		if(centre == null) {
			System.out.println("Please enter a valid Gym Centre ID.");
			return;
		}

		// 2. View Available Slots for the chosen centre

		List<FlipFitSlots> slotsList = gymCentreBusiness.viewAvailableSlots(centre);
		if (slotsList.isEmpty()) {
			System.out.println("No available slots for this centre.");
			return;
		}
		System.out.println("Available slots :");
		slotsList.forEach(slot ->
				System.out.println("Slot ID: " + slot.getSlotId() + ", Time: " + slot.getSlotStartTime() + ", Availability: " + slot.getSeatsAvailable()));

		System.out.print("\nEnter the Slot ID you wish to book: ");
		int slotId = sc.nextInt();
		sc.nextLine();

		// Validate the chosen slotId
		FlipFitSlots chosenSlot = slotsList.stream().filter(s -> s.getSlotId() == slotId).findFirst().orElse(null);
		if (chosenSlot == null) {
			System.out.println("Invalid Slot ID selected.");
			return;
		}

		// 3. Check for Booking Conflicts
		System.out.println("\n--- Step 3: Checking for Conflicts ---");
		FlipFitBooking hasConflict = customerBusiness.checkBookingConflicts(userId, chosenSlot.getSlotStartTime());

		if (hasConflict != null) {
			System.out.println("Booking failed! You already have another booking at this time.");
			return;
		}
		System.out.println("No conflicts found. You can proceed with the booking.");

		// 4. Make the Booking
		System.out.println("\n--- Step 4: Confirming Booking ---");
		FlipFitBooking newBooking = new FlipFitBooking();
		newBooking.setSlotId(chosenSlot.getSlotId());
		newBooking.setUserId(userId);
		FlipFitBooking bookingMade = bookingBusiness.makeBooking(newBooking);
		if (bookingMade == null) {
			System.out.println("Sorry, something went wrong while making the booking. The slot might have just been filled.");
			return;
		}
		System.out.println("Booking successful! Please complete the payment.");

		// 5. Make Payment
		System.out.println("\n--- Step 5: Processing Payment ---");
		System.out.print("Payment Amount:- 1000, Enter your payment method (e.g., Credit Card, UPI): ");
		String paymentInfo = sc.nextLine();
		boolean paymentSuccess = customerBusiness.makePayment(userId, paymentInfo);

		if (paymentSuccess) {
			System.out.println("Payment successful! Your booking is confirmed.");
		} else {
			System.out.println("Payment failed. Your booking has been cancelled. Please try again.");
			// In a real app, you'd have more robust logic to handle payment failure.
			bookingBusiness.deleteBooking(bookingMade.getBookingId(), slotId); // Rollback the booking
		}
	}

	/**
	 * Displays all bookings for the current user.
	 * @param userId The ID of the logged-in customer.
	 */
	private static void viewMyBookings(int userId) {
		System.out.println("\n--- Your Bookings ---");
		List<FlipFitBooking> bookings = bookingBusiness.getAllBookings(userId);
		if (bookings.isEmpty()) {
			System.out.println("You have no bookings at the moment.");
			return;
		}
		bookings.forEach(booking ->
				System.out.println("Booking ID: " + booking.getBookingId() + ", Slot ID: " + booking.getSlotId()));
	}

	/**
	 * Handles the logic for a customer to cancel one of their bookings.
	 * @param userId The ID of the logged-in customer.
	 */
	private static void cancelBooking(int userId) {
		System.out.println("\n--- Cancel a Booking ---");
		viewMyBookings(userId); // Show the user their bookings first
		System.out.print("\nEnter the Booking ID you wish to cancel: ");
		int bookingId = sc.nextInt();
		sc.nextLine();

		// We need the slotId to increment availability.
		// First, get the booking details.
		FlipFitBooking bookingToCancel = bookingBusiness.getBookingDetailsByBookingId(bookingId);
		try{
			if (bookingToCancel == null || bookingToCancel.getUserId() != userId)
                throw new BookingDetailsNotFoundException();
		} catch (BookingDetailsNotFoundException e) {
			System.out.println(e.getMessage());
			return;
        }


        boolean isCancelled = bookingBusiness.deleteBooking(bookingId, bookingToCancel.getSlotId());
		if (isCancelled) {
			System.out.println("Booking ID " + bookingId + " has been successfully cancelled.");
		} else {
			System.out.println("Failed to cancel the booking. Please try again.");
		}
	}

	/**
	 * Displays the payment history for the current user.
	 * @param userId The ID of the logged-in customer.
	 */
	private static void viewPaymentHistory(int userId) {
		System.out.println("\n--- Your Payment History ---");
		List<FlipFitPayments> payments = customerBusiness.viewPaymentDetails(userId);
		if (payments.isEmpty()) {
			System.out.println("You have no payment history.");
			return;
		}
		payments.forEach(payment ->
				System.out.println("Payment ID: " + payment.getPaymentID() + ", Info: " + payment.getPaymentInfo()));
	}
}
