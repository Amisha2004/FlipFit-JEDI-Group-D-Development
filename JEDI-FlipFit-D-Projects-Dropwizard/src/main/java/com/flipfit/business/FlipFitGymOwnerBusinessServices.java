/**
 * 
 */
package com.flipfit.business;
import java.util.List;

import com.flipfit.bean.*;
import com.flipfit.dao.FlipFitGymOwnerDAOImpl;
import com.flipfit.dao.FlipFitGymOwnerDAOInterface;
import com.flipfit.exceptions.GymCentreNotFoundException;

/**
 * 
 */
public class FlipFitGymOwnerBusinessServices extends FlipFitUserBusinessServices implements FlipFitGymOwnerBusinessInterface{
    private final FlipFitGymOwnerDAOInterface flipFitGymOwnerDAOImpl;
    public FlipFitGymOwnerBusinessServices(FlipFitGymOwnerDAOInterface flipFitGymOwnerDAOImpl) {
        this.flipFitGymOwnerDAOImpl = flipFitGymOwnerDAOImpl; // <--- CRITICAL INITIALIZATION
//        System.out.println("DEBUG: FlipFitCustomerBusinessServices constructor called. DAO initialized.");
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
	public List<FlipFitGymCentre> viewOwnCentres(int userId) {
		return flipFitGymOwnerDAOImpl.viewOwnCentres(userId);
    }

    public FlipFitGymOwner editDetails(FlipFitGymOwner owner){
        return flipFitGymOwnerDAOImpl.editDetails(owner);
    }
    public FlipFitGymOwner addGymOwner(FlipFitGymOwner owner, FlipFitUser user){
        return flipFitGymOwnerDAOImpl.addGymOwner(owner, user);
    }
    public boolean deleteGymCentre(int gymId){
        return flipFitGymOwnerDAOImpl.deleteGymOwner(gymId);
    }
}
