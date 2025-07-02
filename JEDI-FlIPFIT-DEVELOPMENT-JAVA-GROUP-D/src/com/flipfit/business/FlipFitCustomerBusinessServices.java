/**
 * 
 */
package com.flipfit.business;

//import java.util.ArrayList;
import java.util.List;

import com.flipfit.bean.FlipFitBooking;
import com.flipfit.bean.FlipFitGymCentre;

/**
 * 
 */
public class FlipFitCustomerBusinessServices extends FlipFitUserBusinessServices {
    public List<FlipFitGymCentre> viewGymCentres(){
        System.out.println("View centres: ");
//        List<FlipFitGymCentre> gymCentres = new ArrayList();
        return null;
    }
    
    public List<FlipFitBooking> viewBookedSlots(int userId){
        System.out.println("Booked Slots: ");
//        List<FlipFitBooking> slotsBooked = new ArrayList();
        return null;
        
    }
    
    public boolean checkBookingConflicts(int userId) {
    	System.out.println("All Booking Conflicts checked!");
    	return true;
    }
    public boolean makePayment(int userId) {
        System.out.println("Make payment called:> ");
        return true;
    }
    
}