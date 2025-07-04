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
        try{
            if(flipFitUserDAOImpl.register(flipFitUser) == null){
                throw new RegistrationFailedException();
            }
            return flipFitUserDAOImpl.register(flipFitUser);
        } catch (RegistrationFailedException e) {
            System.out.println(e.getMessage());
        }
        return null;
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
        try{
            if(flipFitUserDAOImpl.updateUser(FFU) == null){
                throw new UserNotFoundException();
            }
            return flipFitUserDAOImpl.updateUser(FFU);
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public FlipFitUser getUser(int userId){
        try{
            if(flipFitUserDAOImpl.getUser(userId) == null){
                throw new UserNotFoundException();
            }
            return flipFitUserDAOImpl.getUser(userId);
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
