package com.flipfit.rest;

import com.flipfit.bean.*;
import com.flipfit.bean.FlipFitSlots;
import com.flipfit.business.*;
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

    @Inject
    public FlipFitGymOwnerController(
            FlipFitGymOwnerBusinessServices flipFitGymOwnerService,
            FlipFitSlotsBusinessServices flipFitSlotsService
    ) {
        this.flipFitGymOwnerBusinessService = flipFitGymOwnerService;
        this.flipFitSlotsBusinessServices = flipFitSlotsService;
    }

    @POST
    @Path("/addCentre")
    @Consumes(MediaType.APPLICATION_JSON)
    public void addCentre(FlipFitGymCentre flipFitGymCentre) throws InvalidChoiceException {
        flipFitGymOwnerBusinessService.addCentre(flipFitGymCentre);
    }

    @POST
    @Path("/addSlot")
    @Consumes(MediaType.APPLICATION_JSON)
    public FlipFitSlots addSlot(FlipFitSlots flipFitSlot) throws InvalidChoiceException {
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