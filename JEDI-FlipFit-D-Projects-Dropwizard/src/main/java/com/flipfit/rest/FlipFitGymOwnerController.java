package com.flipfit.rest;

import com.flipfit.bean.FlipFitCustomer;
import com.flipfit.bean.FlipFitGymCentre;
import com.flipfit.bean.FlipFitGymOwner;
import com.flipfit.bean.FlipFitSlots;
// import com.flipfit.business.FlipFitCustomerBusinessServices; // This import is not used
import com.flipfit.business.FlipFitGymOwnerBusinessInterface;
import com.flipfit.business.FlipFitGymOwnerBusinessServices;
import com.flipfit.business.FlipFitSlotsBusinessInterface;
import com.flipfit.business.FlipFitSlotsBusinessServices; // Added import for FlipFitSlotsBusinessServices
import com.flipfit.exceptions.InvalidChoiceException;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/gymowner")
@Produces(MediaType.APPLICATION_JSON)
public class FlipFitGymOwnerController {
    private final FlipFitGymOwnerBusinessInterface flipFitGymOwnerBusinessService;
    private final FlipFitSlotsBusinessInterface flipFitSlotsBusinessServices;
    // private FlipFitGymOwner flipFitOwner; // This field is declared but not used, consider removing or using it

    @Inject
    public FlipFitGymOwnerController(
            FlipFitGymOwnerBusinessServices flipFitGymOwnerService,
            FlipFitSlotsBusinessServices flipFitSlotsService // Add this parameter
    ) {
        this.flipFitGymOwnerBusinessService = flipFitGymOwnerService;
        this.flipFitSlotsBusinessServices = flipFitSlotsService; // Initialize this field
    }

    @POST
    @Path("/addCentre")
    @Consumes(MediaType.APPLICATION_JSON)
    public void addCentre(FlipFitGymCentre flipFitGymCentre) throws InvalidChoiceException {
        // flipFitGymCentre.setOwnerID(flipFitOwner.getUserId()); // Commented out as flipFitOwner is not initialized/used
        flipFitGymOwnerBusinessService.addCentre(flipFitGymCentre);
    }

    @POST
    @Path("/addSlot")
    @Consumes(MediaType.APPLICATION_JSON)
    public FlipFitSlots addSlot(FlipFitSlots flipFitSlot) throws InvalidChoiceException {
        // flipFitGymCentre.setOwnerID(flipFitOwner.getUserId()); // Commented out
        return flipFitSlotsBusinessServices.addSlot(flipFitSlot);
    }

    @DELETE
    @Path("/delete/centreID={centreID}")
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean deleteGymCentre(@PathParam("centreID") int centreID) {
        return flipFitGymOwnerBusinessService.deleteGymCentre(centreID);
    }

    @GET
    @Path("/viewowncentre/ownerID={ownerID}")
    public List<FlipFitGymCentre> viewOwnCentres(@PathParam("ownerID") int ownerID) {
        return flipFitGymOwnerBusinessService.viewOwnCentres(ownerID);
    }

    @GET
    @Path("/getCustomerList/ownerID={ownerID}")
    public List<FlipFitCustomer> getCustomerListByGymId(@PathParam("ownerID") int ownerID) {
        return flipFitGymOwnerBusinessService.getCustomerListByGymId(ownerID);
    }
}