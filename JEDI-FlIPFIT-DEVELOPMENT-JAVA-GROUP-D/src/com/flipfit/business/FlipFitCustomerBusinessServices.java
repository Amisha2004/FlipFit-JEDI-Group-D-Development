/**
 * 
 */
package com.flipfit.business;

//import java.util.ArrayList;
import java.util.List;

import com.flipfit.bean.*;
import com.flipfit.dao.FlipFitCustomerDAO;

/**
 * 
 */
public class FlipFitCustomerBusinessServices extends FlipFitUserBusinessServices implements FlipFitCustomerBusinessInterface{
    private final FlipFitCustomerDAO flipFitCustomerDAO;
    public FlipFitCustomerBusinessServices() {
        this.flipFitCustomerDAO = new FlipFitCustomerDAO();
    }

    public List<FlipFitGymCentre> viewGymCentres(){
        return this.flipFitCustomerDAO.viewGymCentres();
    }
    
    public List<FlipFitBooking> viewBookedSlots(int userId){
        return this.flipFitCustomerDAO.viewBookedSlots(userId);
    }
    
    public FlipFitBooking checkBookingConflicts(int userId) {
    	System.out.println("All Booking Conflicts checked!");
    	return this.flipFitCustomerDAO.checkBookingConflicts(userId);
    }
    public boolean makePayment(int userId) {
        System.out.println("Make payment called:> ");
        return this.flipFitCustomerDAO.makePayment(userId);
    }
    
}