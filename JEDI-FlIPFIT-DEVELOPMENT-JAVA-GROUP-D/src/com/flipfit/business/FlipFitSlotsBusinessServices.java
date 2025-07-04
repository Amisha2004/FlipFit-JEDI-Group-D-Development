/**
 * 
 */
package com.flipfit.business;
import com.flipfit.bean.FlipFitSlots;
import com.flipfit.dao.*;
import com.flipfit.exceptions.SlotBookingFailedException;
import com.flipfit.exceptions.SlotsNotFoundException;

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
        try{
            if(this.flipFitSlotDAOImpl.addSlot(slot)==null){
                throw new SlotBookingFailedException();
            }
            return this.flipFitSlotDAOImpl.addSlot(slot);
        }
        catch(SlotBookingFailedException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public boolean deleteSlot(FlipFitSlots slot){
        try {
            if(!this.flipFitSlotDAOImpl.deleteSlot(slot)){
                throw new SlotsNotFoundException();
            }
            return this.flipFitSlotDAOImpl.deleteSlot(slot);
        } catch (SlotsNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean updateSlot(FlipFitSlots slot) {

        try {
            if(!this.flipFitSlotDAOImpl.updateSlot(slot)){
                throw new SlotsNotFoundException();
            }
            return this.flipFitSlotDAOImpl.updateSlot(slot);
        } catch (SlotsNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public List<FlipFitSlots> getAllSlots(int centreId){
        return this.flipFitSlotDAOImpl.getAllSlots(centreId);
    }

    public FlipFitSlots getSlotById(int slotId){
        try {
            if(this.flipFitSlotDAOImpl.getSlotById(slotId)==null){
                throw new SlotsNotFoundException();
            }
            return this.flipFitSlotDAOImpl.getSlotById(slotId);
        } catch (SlotsNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public FlipFitSlots getSlotDetails(Time startTime, int centreId){
        return this.flipFitSlotDAOImpl.getSlotDetails(startTime, centreId);
    }
}
