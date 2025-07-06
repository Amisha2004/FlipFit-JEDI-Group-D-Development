package com.flipfit.rest;

import com.flipfit.bean.*;
import com.flipfit.business.*;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/customer")
@Produces(MediaType.APPLICATION_JSON)
public class FlipFitCustomerController {
    private FlipFitCustomerBusinessInterface flipFitCustomerBusinessServices;
    private FlipFitGymCentreBusinessInterface flipFitGymCentreBusiness;
    private FlipFitBookingBusinessInterface flipFitBookingBusinessServices;

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
    @Consumes(MediaType.APPLICATION_JSON)
    public List<FlipFitGymCentre> viewGymCentres() {
        return flipFitCustomerBusinessServices.viewGymCentres();
    }

    @GET
    @Path("/viewSlots/centreID={centreID}")
    @Consumes(MediaType.APPLICATION_JSON)
    public List<FlipFitSlots> viewAvailableSlots(@PathParam("centreID") int centreID) {
        FlipFitGymCentre centre = new FlipFitGymCentre();
        centre.setGymID(centreID);
        return flipFitGymCentreBusiness.viewAvailableSlots(centre);
    }

    @GET
    @Path("/viewBookedSlots/{userId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public List<FlipFitBooking> viewBookedSlots(@PathParam("userId") int userId) {
        return flipFitBookingBusinessServices.getAllBookings(userId);
    }

    @POST
    @Path("/bookSlot/centreID={centreID}/slotId={slotId}/userID={userID}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON) // Add produces as it returns JSON
    public FlipFitBooking makeBooking(
            @PathParam("centreID") int centreID,
            @PathParam("slotId") int slotId,
            @PathParam("userID") int userID) {

        FlipFitGymCentre centre = new FlipFitGymCentre();
        centre.setGymID(centreID);
        List<FlipFitSlots> availableSlots = flipFitGymCentreBusiness.viewAvailableSlots(centre);

        FlipFitSlots chosenSlot = availableSlots.stream()
                .filter(s -> s.getSlotId() == slotId)
                .findFirst()
                .orElse(null);

        if (chosenSlot == null) {
            System.out.println("Invalid Slot ID or Centre ID selected for booking.");
            return null; // Or throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        // 2. Check for Booking Conflicts (using the business service)
        FlipFitBooking existingConflictBooking = flipFitCustomerBusinessServices.checkBookingConflicts(userID, chosenSlot.getSlotStartTime());
        if (existingConflictBooking != null) {
            System.out.println("Booking failed! User already has another booking at this time.");
            return null;
        }

        // 3. Make the Booking
        FlipFitBooking newBooking = new FlipFitBooking();
        newBooking.setSlotId(chosenSlot.getSlotId());
        newBooking.setUserId(userID);

        FlipFitBooking bookingMade = flipFitBookingBusinessServices.makeBooking(newBooking);

        if (bookingMade == null) {
            System.out.println("Sorry, something went wrong while making the booking. The slot might have just been filled.");
            return null;
        }

        System.out.println("Booking successful!");
        return bookingMade;
    }
}