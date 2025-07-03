/**
 * 
 */
package com.flipfit.business;

//import java.util.ArrayList;
import java.util.List;

import com.flipfit.bean.*;
import com.flipfit.dao.FlipFitCustomerDAOImpl;
import com.flipfit.dao.FlipFitCustomerDAOInterface;

/**
 * 
 */
public class FlipFitCustomerBusinessServices extends FlipFitUserBusinessServices implements FlipFitCustomerBusinessInterface{
    private final FlipFitCustomerDAOInterface flipFitCustomerDAOImpl;
    public FlipFitCustomerBusinessServices() {
        this.flipFitCustomerDAOImpl = new FlipFitCustomerDAOImpl();
    }

    public List<FlipFitGymCentre> viewGymCentres(){
        return this.flipFitCustomerDAOImpl.viewGymCentres();
    }
    
    public List<FlipFitSlots> viewBookedSlots(int userId){
        return this.flipFitCustomerDAOImpl.viewBookedSlots(userId);
    }
    
    public FlipFitBooking checkBookingConflicts(int userId, int slotTime) {
    	System.out.println("All Booking Conflicts checked!");
    	return this.flipFitCustomerDAOImpl.checkBookingConflicts(userId, slotTime);
    }
    public boolean makePayment(int userId) {
        System.out.println("Make payment called:> ");
        return this.flipFitCustomerDAOImpl.makePayment(userId);
    }
    
}