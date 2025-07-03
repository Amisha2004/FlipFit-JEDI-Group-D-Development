// ===================================================================================
// File: com/flipfit/business/FlipFitUserBusinessServices.java
// ===================================================================================
package com.flipfit.business;

import com.flipfit.bean.*;
import com.flipfit.constants.DBConstants;
import com.flipfit.dao.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 * Implements the business logic for general user functionalities like
 * login, registration, and viewing gym centres.
 */
public class FlipFitUserBusinessServices implements FlipFitUserBusinessInterface {
    private final FlipFitUserDAOInterface flipFitUserDAOImpl;
    public FlipFitUserBusinessServices() {
        this.flipFitUserDAOImpl = new FlipFitUserDAOImpl(); // <--- CRITICAL INITIALIZATION
        System.out.println("DEBUG: FlipFitCustomerBusinessServices constructor called. DAO initialized.");
    }



    @Override
    public boolean register(FlipFitUser flipFitUser) {

        // Pass the fully-formed object to the DAO to handle the database insertion.
        System.out.println("Business Service: Attempting to register user with DAO...");
        return flipFitUserDAOImpl.register(flipFitUser);
    }

    public FlipFitUser logIn(FlipFitUser FlipFitUser) {
        System.out.println("Business Service: Verifying credentials via DAO...");
        FlipFitUser user = flipFitUserDAOImpl.login(FlipFitUser.getUserName(), FlipFitUser.getPassword());
        if (user != null) {
            System.out.println("Business Service: Login successful for " + user.getUserName());
        } else {
            System.out.println("Business Service: Login failed.");
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
