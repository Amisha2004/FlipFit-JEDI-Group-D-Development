/**
 * 
 */
package com.flipfit.business;

import java.util.List;

import com.flipfit.bean.*;

/**
 * 
 */
public interface FlipFitGymOwnerBusinessInterface {
	public void addCentre(FlipFitGymCentre flipFitGymCentre);
	public List<FlipFitCustomer> getCustomerListByGymId(int gymId);
	public List<FlipFitPayments> viewPayments();
	public List<FlipFitGymCentre> viewOwnCentres(int userId);
	public FlipFitGymOwner editDetails(FlipFitGymOwner owner);
	public FlipFitGymOwner addGymOwner(FlipFitGymOwner owner, FlipFitUser user);
	public boolean deleteGymCentre(int gymId);
}
