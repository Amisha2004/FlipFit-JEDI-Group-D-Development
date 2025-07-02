/**
 * 
 */
package com.flipfit.business;
import com.flipfit.bean.*;
import java.util.*;

/**
 * 
 */
public class FlipFitAdminBusinessServices extends FlipFitUserBusinessServices implements FlipFitAdminBusinessInterface{
    public List<FlipFitGymOwner> getPendingGymOwnerList(){
        System.out.println("Here is the pending owner list");
        List<FlipFitGymOwner> pendingOwners = new ArrayList<>();
        return pendingOwners;
    }
    public List<FlipFitGymOwner> getApprovedGymOwnerList(){
        System.out.println("Here is the approved gym owner list");
        List<FlipFitGymOwner> approvedOwners = new ArrayList<>();
        return approvedOwners;
    }
    public List<FlipFitCustomer> getCustomerList(){
        System.out.println("Here is the customer list");
        return null;
    }
    public boolean validateOwner(int ownerId){
        System.out.println("Validated owner -> " + ownerId);
        return true;
    }
    public boolean deleteOwner(int ownerId){
        System.out.println("Deleted owner " + ownerId);
        return true;
    }
    public List<FlipFitGymCentre> getGymCentreUsingOwnerId(int ownerId){
        System.out.println("Gym Centre using owner id " + ownerId);
        return null;
    }
}
