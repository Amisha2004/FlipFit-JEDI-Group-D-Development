/**
 * 
 */
package com.flipfit.business;
import java.util.ArrayList;
import java.util.List;

import com.flipfit.bean.*;
import com.flipfit.dao.FlipFitAdminDAO;
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
        FlipFitGymOwnerDAO.addCentre(flipFitGymCentre);
    }
	public void addSlot(FlipFitSlots flipFitSlot){
       FlipFitGymOwnerDAO.addSlot(flipFitSlot);
    }
	public List<FlipFitPayments> viewPayments() {
        System.out.println("Payments listed:> ");
        return null;
    }
    public List<FlipFitCustomer> getCustomerList(){
        return FlipFitGymOwnerDAO.getCustomerList();
    }
	public List<FlipFitGymCentre> viewOwnCentres(int ownerId) {
//		List<FlipFitGymCentre> gymCentres = new ArrayList();
		return FlipFitGymOwnerDAO.viewOwnCentres(ownerId);
    }
}
