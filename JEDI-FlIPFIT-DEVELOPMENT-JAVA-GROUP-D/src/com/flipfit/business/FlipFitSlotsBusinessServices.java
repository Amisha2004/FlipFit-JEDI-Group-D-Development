/**
 * 
 */
package com.flipfit.business;
import com.flipfit.bean.FlipFitSlots;
import com.flipfit.dao.*;

import java.sql.Time;
import java.time.LocalTime;
import java.util.List;

/**
 * 
 */
public class FlipFitSlotsBusinessServices implements FlipFitSlotsBusinessInterface{
    private final FlipFitSlotDAOImpl flipFitSlotDAOImpl;
    public FlipFitSlotsBusinessServices() {
        this.flipFitSlotDAOImpl = new FlipFitSlotDAOImpl(); // <--- CRITICAL INITIALIZATION
//        System.out.println("DEBUG: FlipFitCustomerBusinessServices constructor called. DAO initialized.");
    }

    public FlipFitSlots addSlot(FlipFitSlots slot){
        return this.flipFitSlotDAOImpl.addSlot(slot);
    }

    public boolean deleteSlot(FlipFitSlots slot){
        return this.flipFitSlotDAOImpl.deleteSlot(slot);
    }

    public boolean updateSlot(FlipFitSlots slot) {
        return this.flipFitSlotDAOImpl.updateSlot(slot);
    }

    public List<FlipFitSlots> getAllSlots(int centreId){
        return this.flipFitSlotDAOImpl.getAllSlots(centreId);
    }

    public FlipFitSlots getSlotById(int slotId){
        return this.flipFitSlotDAOImpl.getSlotById(slotId);
    }

    public FlipFitSlots getSlotDetails(Time startTime, int centreId){
        return this.flipFitSlotDAOImpl.getSlotDetails(startTime, centreId);
    }
}
