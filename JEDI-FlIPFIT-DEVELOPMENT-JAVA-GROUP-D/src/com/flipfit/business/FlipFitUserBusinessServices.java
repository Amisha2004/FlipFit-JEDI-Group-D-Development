// ===================================================================================
// File: com/flipfit/business/FlipFitUserBusinessServices.java
// ===================================================================================
package com.flipfit.business;

import com.flipfit.bean.*;
import com.flipfit.dao.FlipFitUserDao;

import java.util.List;

/**
 * Implements the business logic for general user functionalities like
 * login, registration, and viewing gym centres.
 */
public class FlipFitUserBusinessServices implements FlipFitUserBusinessInterface {

    // Instantiate the DAOs that this service will interact with.
    private final FlipFitUserDao userDao = new FlipFitUserDao();

    @Override
    public boolean register(String userName, String password, String emailID, String phoneNumber, String role) {
        FlipFitUser newUser;

        // Business Logic: Create the correct type of user object based on the role string.
        if ("Customer".equalsIgnoreCase(role)) {
            FlipFitCustomer customer = new FlipFitCustomer();
            customer.setRole(3); // 3 is the Role ID for Customer
            newUser = customer;
        } else if ("GymOwner".equalsIgnoreCase(role)) {
            FlipFitGymOwner owner = new FlipFitGymOwner();
            owner.setRole(2); // 2 is the Role ID for GymOwner
            owner.setApproved(false); // New owners must be approved by an admin
            // In a real app, you would prompt for GST/Aadhar here or on a separate screen.
            newUser = owner;
        } else {
            System.out.println("Business Service: Invalid role specified for registration.");
            return false;
        }

        // Set the common properties shared by all users.
        newUser.setUserName(userName);
        newUser.setPassword(password);
        newUser.setEmailID(emailID);
        newUser.setPhoneNumber(phoneNumber);

        // Pass the fully-formed object to the DAO to handle the database insertion.
        System.out.println("Business Service: Attempting to register user with DAO...");
        return userDao.register(newUser);
    }

    public FlipFitUser logIn(FlipFitUser FlipFitUser) {
        System.out.println("Business Service: Verifying credentials via DAO...");
        FlipFitUser user = userDao.login(FlipFitUser.getEmailID(), FlipFitUser.getPassword());
        if (user != null) {
            System.out.println("Business Service: Login successful for " + user.getUserName());
        } else {
            System.out.println("Business Service: Login failed.");
        }
        return user;
    }

    /**
     * Logs the user out. In a real application, this would invalidate a session.
     *
     * @return Always returns true.
     */
    @Override
    public boolean logOut() {
        System.out.println("You have been logged out successfully.");
        return true;
    }

    /**
     * Retrieves a list of all approved gym centres.
     *
     * @return A list of FlipFitGymCentre objects.
     */
    @Override
    public List<FlipFitGymCentre> viewCentres() {
        System.out.println("Business Service: Fetching all approved gym centres...");
        return null;
    }
}
