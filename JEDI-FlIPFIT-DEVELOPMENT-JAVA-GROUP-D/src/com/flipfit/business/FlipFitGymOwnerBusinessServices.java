/**
 * 
 */
package com.flipfit.business;
import java.util.List;

import com.flipfit.bean.*;
import com.flipfit.dao.FlipFitGymOwnerDAOImpl;
import com.flipfit.dao.FlipFitGymOwnerDAOInterface;
import com.flipfit.dao.FlipFitUserDAOInterface;

/**
 * 
 */
public class FlipFitGymOwnerBusinessServices extends FlipFitUserBusinessServices implements FlipFitGymOwnerBusinessInterface{
    private final FlipFitGymOwnerDAOInterface flipFitGymOwnerDAOImpl;
    public FlipFitGymOwnerBusinessServices() {
        this.flipFitGymOwnerDAOImpl = new FlipFitGymOwnerDAOImpl(); // <--- CRITICAL INITIALIZATION
        System.out.println("DEBUG: FlipFitCustomerBusinessServices constructor called. DAO initialized.");
    }

    public void addCentre(FlipFitGymCentre flipFitGymCentre){
        flipFitGymOwnerDAOImpl.addCentre(flipFitGymCentre);
    }
	public List<FlipFitPayments> viewPayments() {
        System.out.println("Payments listed:> ");
        return null;
    }
    public List<FlipFitCustomer> getCustomerListByGymId(int gymId){
        return flipFitGymOwnerDAOImpl.getCustomerListByGymId(gymId);
    }
	public List<FlipFitGymCentre> viewOwnCentres(FlipFitGymOwner owner) {
//		List<FlipFitGymCentre> gymCentres = new ArrayList();
		return flipFitGymOwnerDAOImpl.viewOwnCentres(owner);
    }

    public FlipFitGymOwner editDetails(FlipFitGymOwner owner){
        return flipFitGymOwnerDAOImpl.editDetails(owner);
    }
    public FlipFitGymOwner addGymOwner(FlipFitGymOwner owner, FlipFitUser user){
        return flipFitGymOwnerDAOImpl.addGymOwner(owner, user);
    }
}
