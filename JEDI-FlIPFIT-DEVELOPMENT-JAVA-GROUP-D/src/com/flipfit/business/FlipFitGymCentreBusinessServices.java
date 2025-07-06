/**
 * 
 */
package com.flipfit.business;

import java.util.ArrayList;
import java.util.List;

import com.flipfit.bean.*;
import com.flipfit.dao.FlipFitAdminDAOImpl;
import com.flipfit.dao.FlipFitAdminDAOInterface;
import com.flipfit.dao.FlipFitCustomerDAOImpl;
import com.flipfit.dao.*;

/**
 * Implements the business logic for managing FlipFit gym centers
 */
public class FlipFitGymCentreBusinessServices implements FlipFitGymCentreBusinessInterface{
    private final FlipFitGymCentreDAOInterface flipFitGymCentreDAOImpl;
    public FlipFitGymCentreBusinessServices() {
        this.flipFitGymCentreDAOImpl = new FlipFitGymCentreDAOImpl();
    }
    public FlipFitGymCentre updateGymCentre(FlipFitGymCentre FFGC){
        return flipFitGymCentreDAOImpl.updateGymCentre(FFGC);
    }
    public void deleteGymCentre(FlipFitGymCentre FFGC){
        flipFitGymCentreDAOImpl.deleteGymCentre(FFGC);
    }
    public boolean isGymCentreAvailable(int centreID){
        return flipFitGymCentreDAOImpl.isGymCentreAvailable(centreID);
    }
    public ArrayList<FlipFitGymCentre> viewCentres(String city){
        return flipFitGymCentreDAOImpl.viewCentres(city);
    }
    public ArrayList<FlipFitSlots> viewAvailableSlots(FlipFitGymCentre FFGC){
        return flipFitGymCentreDAOImpl.viewAvailableSlots(FFGC);
    }
}
