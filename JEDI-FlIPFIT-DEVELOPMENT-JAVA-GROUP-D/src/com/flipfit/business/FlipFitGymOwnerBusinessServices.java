/**
 * 
 */
package com.flipfit.business;
import java.util.ArrayList;
import java.util.List;

import com.flipfit.bean.*;
import com.flipfit.dao.FlipFitGymOwnerDAO;

/**
 * 
 */
public class FlipFitGymOwnerBusinessServices extends FlipFitUserBusinessServices implements FlipFitGymOwnerBusinessInterface{
    private FlipFitGymOwnerDAO flipFitGymOwnerDAO;
    public FlipFitGymOwnerBusinessServices() {
        // THIS LINE IS THE SOLUTION. IT MUST BE PRESENT AND CORRECT.
        this.flipFitGymOwnerDAO = new FlipFitGymOwnerDAO(); // <--- CRITICAL INITIALIZATION
        System.out.println("DEBUG: FlipFitCustomerBusinessServices constructor called. DAO initialized."); // Add this for debugging
    }

    public void addCentre(FlipFitGymCentre flipFitGymCentre){
        System.out.println("Added");
    }
	public void addSlot(int gymId, FlipFitSlots flipFitSlot){
       System.out.println("Added slot");
    }
	public List<FlipFitPayments> viewPayments() {
        System.out.println("Payments listed:> ");
        return null;
    }
	public List<FlipFitGymCentre> viewOwnCentres(FlipFitGymOwner flipFitGymOwner) {
//		List<FlipFitGymCentre> gymCentres = new ArrayList();
		System.out.println("Centres listed:> ");
        return this.flipFitGymOwnerDAO.viewCentresByOwnerID(flipFitGymOwner);
    }
}
