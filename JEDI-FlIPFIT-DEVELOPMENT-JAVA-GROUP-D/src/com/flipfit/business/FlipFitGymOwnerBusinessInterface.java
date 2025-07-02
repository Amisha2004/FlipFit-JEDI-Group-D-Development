/**
 * 
 */
package com.flipfit.business;

import java.util.List;

import com.flipfit.bean.FlipFitGymCentre;
import com.flipfit.bean.FlipFitGymOwner;
import com.flipfit.bean.FlipFitPayments;
import com.flipfit.bean.FlipFitSlots;

/**
 * 
 */
public interface FlipFitGymOwnerBusinessInterface {
	public void addCentre(FlipFitGymCentre flipFitGymCentre);
	public void addSlot(int gymId, FlipFitSlots flipFitSlot);
	public List<FlipFitPayments> viewPayments();
	public List<FlipFitGymCentre> viewOwnCentres(FlipFitGymOwner flipFitGymOwner);
}
