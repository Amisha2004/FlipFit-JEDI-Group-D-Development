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
 * Implements the business logic for managing FlipFit gym slots.
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
        try {
            // Call the DAO method ONLY ONCE and store the result
            boolean isDeleted = this.flipFitSlotDAOImpl.deleteSlot(slot);

            // Check the result to decide whether to throw an exception or return
            if (!isDeleted) {
                // If the DAO returned false, it means the slot was not found/deleted
                // So, throw the custom exception
                throw new SlotsNotFoundException();
            }

            // If no exception was thrown, it means isDeleted must be true,
            // so return true to indicate success
            return true;

        } catch (SlotsNotFoundException e) {
            // Catch the specific exception and print its message
            System.out.println(e.getMessage());
            // Return false because the deletion failed (slot not found)
            return false;
        }
        // No need for a return false outside the try-catch if catch block handles the final return
    }

    public boolean updateSlot(FlipFitSlots slot) {
        try {
            boolean isUpdated = this.flipFitSlotDAOImpl.updateSlot(slot);

            if (!isUpdated) {
                throw new SlotsNotFoundException();
            }

            return true;

        } catch (SlotsNotFoundException e) {
            System.out.println(e.getMessage());
            return false;
        }
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
