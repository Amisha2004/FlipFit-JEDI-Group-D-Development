// ===================================================================================
// File: com/flipfit/business/FlipFitUserBusinessServices.java
// ===================================================================================
package com.flipfit.business;

import com.flipfit.bean.*;
import com.flipfit.constants.ColorConstants;
import com.flipfit.dao.*;
import com.flipfit.exceptions.InvalidLoginException;
import com.flipfit.exceptions.RegistrationFailedException;
import com.flipfit.exceptions.UserNotFoundException;

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
            return this.flipFitUserDAOImpl.register(flipFitUser);
    }

    public FlipFitUser logIn(FlipFitUser FlipFitUser) {
        try {
            FlipFitUser user = flipFitUserDAOImpl.login(FlipFitUser.getUserName(), FlipFitUser.getPassword(), FlipFitUser.getRole());
            if (user == null) {
                throw new InvalidLoginException();
            }
            return user;
        } catch (InvalidLoginException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void deleteUser(int userId){
        flipFitUserDAOImpl.deleteUser(userId);
    }

    public FlipFitUser updateUser(FlipFitUser FFU) {
        try {
            FlipFitUser updatedUser = flipFitUserDAOImpl.updateUser(FFU);
            if (updatedUser == null) {
                throw new UserNotFoundException();
            }
            return updatedUser;
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public FlipFitUser getUser(int userId) {
        try {
            FlipFitUser user = flipFitUserDAOImpl.getUser(userId);
            if (user == null) {
                throw new UserNotFoundException();
            }
            return user;
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
