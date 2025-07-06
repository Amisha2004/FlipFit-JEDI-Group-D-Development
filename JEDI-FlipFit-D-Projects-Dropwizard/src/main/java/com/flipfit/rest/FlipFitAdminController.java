/**
 *
 */
package com.flipfit.rest;

import com.flipfit.bean.*;
import com.flipfit.business.*;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.*;
import java.util.stream.Collectors;

/**
 *
 */
@Path("/admin")
@Produces(MediaType.APPLICATION_JSON)
public class FlipFitAdminController {

    private final FlipFitAdminBusinessInterface flipFitAdminBusinessServices;

    @Inject
    public FlipFitAdminController(FlipFitAdminBusinessServices flipFitAdmin) {
        this.flipFitAdminBusinessServices = flipFitAdmin;
    }

    @GET
    @Path("/getPendingOwnerList")
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Map<String, String>> getPendingOwnerList() {
        List<FlipFitGymOwner> owners=flipFitAdminBusinessServices.getPendingGymOwnerList();
        return owners.stream()
                .map(owner -> {
                    Map<String, String> ownerMap = new HashMap<>();
                    ownerMap.put("OwnerId", String.valueOf(owner.getUserId()));
                    ownerMap.put("UserName", String.valueOf(owner.getUserName()));
                    return ownerMap;
                })
                .collect(Collectors.toList());
    }

    @GET
    @Path("/getApprovedOwnerList")
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Map<String, String>> getApprovedGymOwnerList() {

        List<FlipFitGymOwner> approvedGymOwners=flipFitAdminBusinessServices.getApprovedGymOwnerList();
        return approvedGymOwners.stream()
                .map(owner -> {
                    Map<String, String> ownerMap = new HashMap<>();
                    ownerMap.put("OwnerId", String.valueOf(owner.getUserId()));
                    ownerMap.put("UserName", String.valueOf(owner.getUserName()));
                    return ownerMap;
                })
                .collect(Collectors.toList());
    }

    @GET
    @Path("/getUserList")
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Map<String, String>> getUserList() {

        List<FlipFitUser> users=flipFitAdminBusinessServices.getUserList();
        return users.stream()
                .map(owner -> {
                    Map<String, String> ownerMap = new HashMap<>();
                    ownerMap.put("UserId", String.valueOf(owner.getUserId()));
                    ownerMap.put("UserName", String.valueOf(owner.getUserName()));
                    return ownerMap;
                })
                .collect(Collectors.toList());
    }

    @POST
    @Path("/approveOwner/ownerId={ownerId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean validateOwner(@PathParam("ownerId") int ownerId) {
        return flipFitAdminBusinessServices.validateOwner(ownerId);
    }


    @GET
    @Path("/getGymCentreUsingOwnerId/ownerID={ownerID}")
    @Consumes(MediaType.APPLICATION_JSON)
    public List<FlipFitGymCentre> getGymCentreUsingOwnerId(@PathParam("ownerID") int ownerID) {
        return flipFitAdminBusinessServices.getGymCentreUsingOwnerId(ownerID);
    }

    @DELETE
    @Path("/deleteOwner/ownerID={ownerID}")
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean deleteOwner(@PathParam("ownerID") int ownerID) {
        return flipFitAdminBusinessServices.deleteOwner(ownerID);
    }

}
