// ===================================================================================
// File: com/flipfit/business/FlipFitUserBusinessServices.java
// ===================================================================================
package com.flipfit.business;

import com.flipfit.bean.*;
import com.flipfit.constants.ColorConstants;
import com.flipfit.dao.*;

/**
 * Implements the business logic for general user functionalities like
 * login, registration, and viewing gym centres.
 */
public class FlipFitUserBusinessServices implements FlipFitUserBusinessInterface {
    private final FlipFitUserDAOInterface flipFitUserDAOImpl;
    public FlipFitUserBusinessServices() {
        this.flipFitUserDAOImpl = new FlipFitUserDAOImpl(); // <--- CRITICAL INITIALIZATION
//        System.out.println("DEBUG: FlipFitCustomerBusinessServices constructor called. DAO initialized.");
    }



    @Override
    public FlipFitUser register(FlipFitUser flipFitUser) {
        System.out.println("Business Service: Attempting to register user with DAO...");
        return flipFitUserDAOImpl.register(flipFitUser);
    }

    public FlipFitUser logIn(FlipFitUser FlipFitUser) {
        FlipFitUser user = flipFitUserDAOImpl.login(FlipFitUser.getUserName(), FlipFitUser.getPassword(),  FlipFitUser.getRole());
        if (user != null) {
            System.out.println(ColorConstants.GREEN + "Business Service: Login successful for " + user.getUserName() + ColorConstants.RESET);
        } else {
            System.out.println(ColorConstants.RED + "Business Service: Login failed." + ColorConstants.RESET);
        }
        return user;
    }

    public void deleteUser(int userId){
        flipFitUserDAOImpl.deleteUser(userId);
    }

    public FlipFitUser updateUser(FlipFitUser FFU) {
        return flipFitUserDAOImpl.updateUser(FFU);
    }

    public FlipFitUser getUser(int userId){
        return flipFitUserDAOImpl.getUser(userId);
    }
}
