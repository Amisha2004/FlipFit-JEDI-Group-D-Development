/**
 * 
 */
package com.flipfit.business;
import com.flipfit.bean.*;
import com.flipfit.dao.FlipFitAdminDAO;

import java.util.*;

/**
 * 
 */
public class FlipFitAdminBusinessServices extends FlipFitUserBusinessServices implements FlipFitAdminBusinessInterface{

    public FlipFitAdminBusinessServices() {
        FlipFitAdminDAO flipFitAdminDAO = new FlipFitAdminDAO(); // Initialize the DAO
    }

    public List<FlipFitGymOwner> getPendingGymOwnerList(){
        return FlipFitAdminDAO.getPendingGymOwnerList();
    }

    public List<FlipFitGymOwner> getApprovedGymOwnerList(){
        return FlipFitAdminDAO.getApprovedGymOwnerList();
    }
    public List<FlipFitCustomer> getCustomerList(){
        return FlipFitAdminDAO.getCustomerList();
    }
    public boolean validateOwner(int ownerId){
        return FlipFitAdminDAO.validateOwner(ownerId);
    }
    public boolean deleteOwner(int ownerId){
        return FlipFitAdminDAO.deleteOwner(ownerId);
    }
    public List<FlipFitGymCentre> getGymCentreUsingOwnerId(int ownerId){
        return FlipFitAdminDAO.getGymCentreUsingOwnerId(ownerId);
    }
}
