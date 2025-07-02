/**
 * 
 */
package com.flipfit.business;

import java.util.List;

import com.flipfit.bean.FlipFitGymCentre;
import com.flipfit.bean.FlipFitSlots;

/**
 * 
 */
public interface FlipFitGymCentreBusinessInterface {
	public FlipFitGymCentre updateGymCentre(FlipFitGymCentre flipFitGymCentre);

    public boolean deleteGymCentre(int centreId);

    public boolean isGymCentreAvailable(int centreId);

    public List<FlipFitSlots> viewAvailableSlots(int centreId);
}
