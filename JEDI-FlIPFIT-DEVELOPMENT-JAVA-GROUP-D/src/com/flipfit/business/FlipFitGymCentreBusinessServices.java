/**
 * 
 */
package com.flipfit.business;

import java.util.List;

import com.flipfit.bean.*;
import com.flipfit.dao.FlipFitAdminDAOImpl;
import com.flipfit.dao.FlipFitAdminDAOInterface;

/**
 * 
 */
public class FlipFitGymCentreBusinessServices implements FlipFitGymCentreBusinessInterface{
	public FlipFitGymCentre updateGymCentre(FlipFitGymCentre flipFitGymCentre){
        System.out.println("Updating Gym Centre:> ");
        return flipFitGymCentre;
    }

    public boolean deleteGymCentre(int centreId){
        System.out.println("Deleting Gym Centre:> ");
        return true;
    }

    public boolean isGymCentreAvailable(int centreId){
        return true;
    }

    public List<FlipFitSlots> viewAvailableSlots(int centreId) {
        System.out.println("Viewing Available Slots:> ");

//        FlipFitGymCentre flipFitGymCentre = new FlipFitGymCentre();
        return null;
    }
}
