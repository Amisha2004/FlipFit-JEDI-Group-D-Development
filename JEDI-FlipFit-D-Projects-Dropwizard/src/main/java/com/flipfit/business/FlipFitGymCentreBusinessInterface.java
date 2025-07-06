/**
 * 
 */
package com.flipfit.business;

import java.util.ArrayList;
import java.util.List;

import com.flipfit.bean.FlipFitGymCentre;
import com.flipfit.bean.FlipFitSlots;

/**
 * 
 */
public interface FlipFitGymCentreBusinessInterface {
    public FlipFitGymCentre updateGymCentre(FlipFitGymCentre FFGC);
    public void deleteGymCentre(FlipFitGymCentre FFGC);
    public boolean isGymCentreAvailable(int centreID);
    public ArrayList<FlipFitGymCentre> viewCentres(String city);
    public ArrayList<FlipFitSlots> viewAvailableSlots(FlipFitGymCentre FFGC);
}
