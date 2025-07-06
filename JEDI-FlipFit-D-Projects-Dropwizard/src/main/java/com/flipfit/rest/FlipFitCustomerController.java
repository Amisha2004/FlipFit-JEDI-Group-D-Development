package com.flipfit.rest;

import com.flipfit.bean.*;
import com.flipfit.business.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map; // Duplicate import, will remove
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/customer")
@Produces(MediaType.APPLICATION_JSON)
public class FlipFitCustomerController {
    private FlipFitCustomerBusinessInterface flipFitCustomerBusinessServices;
    private FlipFitGymCentreBusinessInterface flipFitGymCentreBusiness;
    // private FlipFitCustomer flipFitCustomer; // These are typically passed as arguments or DTOs in REST
    // private FlipFitGymCentre flipFitGymCentre; // These are typically passed as arguments or DTOs in REST
    // private FlipFitBooking flipFitBooking; // These are typically passed as arguments or DTOs in REST
    private FlipFitBookingBusinessInterface flipFitBookingBusinessServices;

    // It's generally better to have a single constructor for @Inject if possible,
    // or use @Context for JAX-RS injections if you can't use a single constructor.
    // For simplicity, I'm adjusting to a single constructor that takes all necessary business services.
    // In a real application, you might use a DI framework (like Guice or CDI)
    // that handles multiple @Inject constructors or field injection more gracefully.
    @Inject
    public FlipFitCustomerController(
            FlipFitBookingBusinessInterface flipFitBookingBusinessServices,
            FlipFitCustomerBusinessInterface flipFitCustomerBusinessServices,
            FlipFitGymCentreBusinessInterface flipFitGymCentreBusiness) {
        this.flipFitBookingBusinessServices = flipFitBookingBusinessServices;
        this.flipFitCustomerBusinessServices = flipFitCustomerBusinessServices;
        this.flipFitGymCentreBusiness = flipFitGymCentreBusiness;
    }


    @GET
    @Path("/viewGymCentres")
    @Consumes(MediaType.APPLICATION_JSON) // This Consumes annotation is usually not needed for GET with no body
    public List<FlipFitGymCentre> viewGymCentres() {
        return flipFitCustomerBusinessServices.viewGymCentres();
    }

    @GET
    @Path("/viewSlots/centreID={centreID}")
    @Consumes(MediaType.APPLICATION_JSON) // This Consumes annotation is usually not needed for GET with no body
    public List<FlipFitSlots> viewAvailableSlots(@PathParam("centreID") int centreID) {
        // You generally don't set properties on a class-level instance for request-scoped data
        // FlipFitGymCentre flipFitGymCentre = new FlipFitGymCentre(); // Create new instance if needed for business logic
        // flipFitGymCentre.setGymID(centreID);
        // Instead, pass the ID directly if the business method supports it, or
        // create a temporary object if the business method requires it.
        // Assuming viewAvailableSlots can take the ID directly or internally handles object creation
        FlipFitGymCentre centre = new FlipFitGymCentre();
        centre.setGymID(centreID);
        return flipFitGymCentreBusiness.viewAvailableSlots(centre);
    }

    @GET
    @Path("/viewBookedSlots/{userId}") // Use path param for ID in GET requests
    @Consumes(MediaType.APPLICATION_JSON) // Not typically for GET with path param
    public List<FlipFitBooking> viewBookedSlots(@PathParam("userId") int userId) { // Changed return type to FlipFitBooking assuming it returns bookings
        // You were passing flipFitCustomer.getUserId() but flipFitCustomer was not initialized.
        // Pass the userId from the path parameter.
        return flipFitBookingBusinessServices.getAllBookings(userId); // Assuming this method exists for fetching all bookings
    }

    @POST
    @Path("/bookSlot/centreID={centreID}/slotId={slotId}/userID={userID}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON) // Add produces as it returns JSON
    public FlipFitBooking makeBooking(
            @PathParam("centreID") int centreID,
            @PathParam("slotId") int slotId,
            @PathParam("userID") int userID) {

        // 1. Get the chosen slot details (similar to how bookASlot validates the slot)
        // You might need a business method to fetch a slot by ID and Centre ID
        // For simplicity, let's assume gymCentreBusiness.viewAvailableSlots(centre)
        // returns a list from which we can find the slot, or there's a direct method.

        FlipFitGymCentre centre = new FlipFitGymCentre();
        centre.setGymID(centreID);
        List<FlipFitSlots> availableSlots = flipFitGymCentreBusiness.viewAvailableSlots(centre);

        FlipFitSlots chosenSlot = availableSlots.stream()
                .filter(s -> s.getSlotId() == slotId)
                .findFirst()
                .orElse(null);

        if (chosenSlot == null) {
            // In a real REST API, you'd throw a WebApplicationException with a 404 or 400 status
            // For now, returning null or a specific error object
            System.out.println("Invalid Slot ID or Centre ID selected for booking.");
            return null; // Or throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        // 2. Check for Booking Conflicts (using the business service)
        FlipFitBooking existingConflictBooking = flipFitCustomerBusinessServices.checkBookingConflicts(userID, chosenSlot.getSlotStartTime());
        if (existingConflictBooking != null) {
            System.out.println("Booking failed! User already has another booking at this time.");
            // In a real REST API, return a conflict status (e.g., 409 Conflict)
            return null; // Or throw new WebApplicationException(Response.Status.CONFLICT);
        }

        // 3. Make the Booking
        FlipFitBooking newBooking = new FlipFitBooking();
        newBooking.setSlotId(chosenSlot.getSlotId());
        newBooking.setUserId(userID);

        FlipFitBooking bookingMade = flipFitBookingBusinessServices.makeBooking(newBooking);

        if (bookingMade == null) {
            System.out.println("Sorry, something went wrong while making the booking. The slot might have just been filled.");
            // In a real REST API, return a server error (e.g., 500 Internal Server Error) or a conflict if slot filled
            return null; // Or throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }

        // Note: Payment handling is usually a separate step in a REST API,
        // often triggered after a successful booking creation.
        // For a simple example, we might assume payment is handled implicitly or separately.
        // If payment is integral to booking in this API, you'd need to extend this.
        // The console application's payment method is interactive and not suitable for a REST endpoint directly.

        System.out.println("Booking successful!");
        return bookingMade;
    }
}