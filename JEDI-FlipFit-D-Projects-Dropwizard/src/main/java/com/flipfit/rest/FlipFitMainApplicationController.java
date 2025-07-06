package com.flipfit.rest;

import com.flipfit.bean.FlipFitCustomer;
import com.flipfit.bean.FlipFitGymOwner;
import com.flipfit.bean.FlipFitUser;
import com.flipfit.business.FlipFitCustomerBusinessInterface; // Import the customer business interface
import com.flipfit.business.FlipFitCustomerBusinessServices; // Import the customer business services (if needed for direct instantiation, but preferably use interface)
import com.flipfit.business.FlipFitGymOwnerBusinessInterface;
import com.flipfit.business.FlipFitGymOwnerBusinessServices;
import com.flipfit.business.FlipFitUserBusinessInterface;

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
    private final FlipFitGymOwnerBusinessInterface flipFitGymOwnerBusinessService; // Correctly declared
    private final FlipFitCustomerBusinessInterface flipFitCustomerBusinessService; // Correctly declared

    // No need for these instance variables if they are only used within a method's scope
    // private FlipFitCustomer flipFitustomer;
     private FlipFitUser flipFitUser;

    @Inject
    public FlipFitMainApplicationController(
            FlipFitUserBusinessInterface flipFitUserBusinessService,
            FlipFitGymOwnerBusinessInterface flipFitGymOwnerBusinessService, // Injected
            FlipFitCustomerBusinessInterface flipFitCustomerBusinessService // Injected
    ) {
        this.flipFitUserBusinessService = flipFitUserBusinessService;
        this.flipFitGymOwnerBusinessService = flipFitGymOwnerBusinessService; // Assigned
        this.flipFitCustomerBusinessService = flipFitCustomerBusinessService; // Assigned
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Map<String, String> login(FlipFitUser user) {
        FlipFitUser loggedInUser = flipFitUserBusinessService.logIn(user); // Renamed to avoid confusion
        // this.flipFitUser = loggedInUser; // No need to store in instance variable if not used later

        Map<String, String> responseMap = new HashMap<>();
        if (loggedInUser != null) {
            responseMap.put("userName", loggedInUser.getUserName());
            responseMap.put("emailID", loggedInUser.getEmailID());
            responseMap.put("role", String.valueOf(loggedInUser.getRole()));
            // Optionally, you might want to return a success status or message
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
        gymOwnerToRegister.setRole(2); // Role for Gym Owner
        gymOwnerToRegister.setApproved(false); // Not approved by default

        // Use flipFitUserBusinessService for generic user registration first
        FlipFitUser newUser = flipFitUserBusinessService.register(gymOwnerToRegister);

        if (newUser != null) {
            // Then use flipFitGymOwnerBusinessService to add specific gym owner details
            FlipFitGymOwner addedGymOwner = flipFitGymOwnerBusinessService.addGymOwner(gymOwnerToRegister, newUser);

            if (addedGymOwner != null) {
                newUser.setPassword(null); // Clear password before returning
                return newUser;
            }
        }
        return null; // Return null if any step failed
    }

    @POST
    @Path("/register/customer")
    @Consumes(MediaType.APPLICATION_JSON)
    public FlipFitUser registerCustomer(FlipFitCustomer customerToRegister) {
        // Role for Customer

        // Use flipFitUserBusinessService for generic user registration first
        FlipFitUser newUser = flipFitUserBusinessService.register(customerToRegister);

        if (newUser != null) {
            // If the customer business service has an additional "add" step, use it here.
            // Otherwise, the initial registration through the user business service might be sufficient.
            // For now, assuming direct registration via user service is enough for customers.
            // If there's a specific `flipFitCustomerBusinessService.addCustomer` method, you'd call it here.

            // For simplicity, assuming the 'register' method in the user service is sufficient
            // for the basic customer setup, and no further 'add' step is needed specific to customers
            // beyond what FlipFitUserBusinessService handles.

            // If customerBusinessServices has a method to "add" or finalize customer details
            // after the user registration, you would call it here:
            // FlipFitCustomer addedCustomer = flipFitCustomerBusinessService.addCustomer(customerToRegister, newUser);
            // if (addedCustomer != null) { ... } else { return null; }

            newUser.setPassword(null); // Clear password before returning
            return newUser;
        }
        return null; // Return null if registration failed
    }
}