/**
 * 
 */
package com.flipfit.business;

import java.util.List;

import com.flipfit.bean.*;
import com.flipfit.bean.FlipFitGymCentre;
import com.flipfit.bean.FlipFitGymOwner;

/**
 * 
 */
public interface FlipFitAdminBusinessInterface {
	    public List<FlipFitGymOwner> getPendingGymOwnerList();
	    public List<FlipFitGymOwner> getApprovedGymOwnerList();
	    public List<FlipFitUser> getUserList();
	    public boolean validateOwner(int ownerId);
	    public boolean deleteOwner(int ownerId);
	    public List<FlipFitGymCentre> getGymCentreUsingOwnerId(int ownerId);
}
