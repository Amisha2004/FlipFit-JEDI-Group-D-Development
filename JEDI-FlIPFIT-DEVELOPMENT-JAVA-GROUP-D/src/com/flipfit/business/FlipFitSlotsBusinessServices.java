/**
 * 
 */
package com.flipfit.business;
import com.flipfit.bean.FlipFitSlots;
import com.flipfit.dao.*;

/**
 * 
 */
public class FlipFitSlotsBusinessServices implements FlipFitSlotsBusinessInterface{
    private final FlipFitSlotDAOImpl flipFitSlotDAOImpl;
    public FlipFitSlotsBusinessServices() {
        this.flipFitSlotDAOImpl = new FlipFitSlotDAOImpl(); // <--- CRITICAL INITIALIZATION
        System.out.println("DEBUG: FlipFitCustomerBusinessServices constructor called. DAO initialized.");
    }

    public boolean updateAvailability(FlipFitSlots flipFitSlots) {
        System.out.println("Updating Slot Availability");
        return true;
    }
    public void getSlotDetails() {
        System.out.println("Getting Slot Details");
    }
    public FlipFitSlots addSlot(FlipFitSlots flipFitSlots) {
        System.out.println("Adding new Slot Details");
        return flipFitSlots;
    };
    public boolean deleteSlot(int slotId) {
        System.out.println("Deleting Slot Details");
        return true;
    };
}
