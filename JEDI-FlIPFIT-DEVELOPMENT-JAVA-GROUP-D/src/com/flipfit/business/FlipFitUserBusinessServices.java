// ===================================================================================
// File: com/flipfit/business/FlipFitUserBusinessServices.java
// ===================================================================================
package com.flipfit.business;

import com.flipfit.bean.*;
import com.flipfit.dao.FlipFitAdminDAO;
import com.flipfit.dao.FlipFitUserDao;

import java.util.List;

/**
 * Implements the business logic for general user functionalities like
 * login, registration, and viewing gym centres.
 */
public class FlipFitUserBusinessServices implements FlipFitUserBusinessInterface {

    // Instantiate the DAOs that this service will interact with.
    public FlipFitUserBusinessServices() {
        FlipFitUserDao flipFitUserDao = new FlipFitUserDao();
    }



    @Override
    public boolean register(FlipFitUser flipFitUser) {

        // Pass the fully-formed object to the DAO to handle the database insertion.
        System.out.println("Business Service: Attempting to register user with DAO...");
        return FlipFitUserDao.register(flipFitUser);
    }

    public FlipFitUser logIn(FlipFitUser FlipFitUser) {
        System.out.println("Business Service: Verifying credentials via DAO...");
        FlipFitUser user = FlipFitUserDao.login(FlipFitUser.getUserName(), FlipFitUser.getPassword());
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
