package com.flipfit.rest;

import com.flipfit.bean.FlipFitCustomer;
import com.flipfit.bean.FlipFitGymOwner;
import com.flipfit.bean.FlipFitUser;
import com.flipfit.business.*;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/mainApplication")
@Produces(MediaType.APPLICATION_JSON)
public class FlipFitMainApplicationController {

    private final FlipFitUserBusinessInterface flipFitUserBusinessService;
    private final FlipFitGymOwnerBusinessInterface flipFitGymOwnerBusinessService;
    private final FlipFitCustomerBusinessInterface flipFitCustomerBusinessService;
     private FlipFitUser flipFitUser;

    @Inject
    public FlipFitMainApplicationController(
            FlipFitUserBusinessInterface flipFitUserBusinessService,
            FlipFitGymOwnerBusinessInterface flipFitGymOwnerBusinessService,
            FlipFitCustomerBusinessInterface flipFitCustomerBusinessService
    ) {
        this.flipFitUserBusinessService = flipFitUserBusinessService;
        this.flipFitGymOwnerBusinessService = flipFitGymOwnerBusinessService;
        this.flipFitCustomerBusinessService = flipFitCustomerBusinessService;
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Map<String, String> login(FlipFitUser user) {
        FlipFitUser loggedInUser = flipFitUserBusinessService.logIn(user);

        Map<String, String> responseMap = new HashMap<>();
        if (loggedInUser != null) {
            responseMap.put("userName", loggedInUser.getUserName());
            responseMap.put("emailID", loggedInUser.getEmailID());
            responseMap.put("role", String.valueOf(loggedInUser.getRole()));
            responseMap.put("status", "success");
        } else {
            responseMap.put("status", "failure");
            responseMap.put("message", "Invalid credentials.");
        }
        return responseMap;
    }

    @POST
    @Path("/register/gymowner")
    @Consumes(MediaType.APPLICATION_JSON)
    public FlipFitUser registerGymOwner(FlipFitGymOwner gymOwnerToRegister) {
        gymOwnerToRegister.setRole(2);
        gymOwnerToRegister.setApproved(false);
        FlipFitUser newUser = flipFitUserBusinessService.register(gymOwnerToRegister);

        if (newUser != null) {
            FlipFitGymOwner addedGymOwner = flipFitGymOwnerBusinessService.addGymOwner(gymOwnerToRegister, newUser);

            if (addedGymOwner != null) {
                newUser.setPassword(null);
                return newUser;
            }
        }
        return null;
    }

    @POST
    @Path("/register/customer")
    @Consumes(MediaType.APPLICATION_JSON)
    public FlipFitUser registerCustomer(FlipFitCustomer customerToRegister) {

        FlipFitUser newUser = flipFitUserBusinessService.register(customerToRegister);

        if (newUser != null) {
            newUser.setPassword(null);
            return newUser;
        }
        return null;
    }
}