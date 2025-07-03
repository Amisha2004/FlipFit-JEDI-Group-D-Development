/**
 * 
 */
package com.flipfit.business;
import com.flipfit.bean.*;
import com.flipfit.dao.FlipFitAdminDAOImpl;
import com.flipfit.dao.FlipFitAdminDAOInterface;
import com.flipfit.dao.FlipFitUserDAOImpl;
import com.flipfit.dao.FlipFitUserDAOInterface;

import java.util.*;

/**
 * 
 */
public class FlipFitAdminBusinessServices extends FlipFitUserBusinessServices implements FlipFitAdminBusinessInterface{
    private final FlipFitAdminDAOInterface flipFitAdminDAOImpl;

    public FlipFitAdminBusinessServices() {
        this.flipFitAdminDAOImpl = new FlipFitAdminDAOImpl();
    }

    public List<FlipFitGymOwner> getPendingGymOwnerList(){
        return flipFitAdminDAOImpl.getPendingGymOwnerList();
    }

    public List<FlipFitGymOwner> getApprovedGymOwnerList(){
        return flipFitAdminDAOImpl.getApprovedGymOwnerList();
    }
    public List<FlipFitUser> getUserList(){
        return flipFitAdminDAOImpl.getUserList();
    }
    public boolean validateOwner(int ownerId){
        return flipFitAdminDAOImpl.validateOwner(ownerId);
    }
    public boolean deleteOwner(int ownerId){
        return flipFitAdminDAOImpl.deleteOwner(ownerId);
    }
    public List<FlipFitGymCentre> getGymCentreUsingOwnerId(int ownerId){
        return flipFitAdminDAOImpl.getGymCentreUsingOwnerId(ownerId);
    }
}
