/**
 * 
 */
package com.flipfit.business;

import java.util.List;

import com.flipfit.bean.FlipFitCustomer;
import com.flipfit.bean.FlipFitGymCentre;
import com.flipfit.bean.FlipFitPayments;
import com.flipfit.bean.FlipFitSlots;

/**
 * 
 */
public interface FlipFitGymOwnerBusinessInterface {
	public void addCentre(FlipFitGymCentre flipFitGymCentre);
	public List<FlipFitCustomer> getCustomerListByGymId(int gymId);
	public List<FlipFitPayments> viewPayments();
	public List<FlipFitGymCentre> viewOwnCentres(int ownerId);
}
