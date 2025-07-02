/**
 * 
 */
package com.flipfit.business;
import com.flipfit.bean.FlipFitSlots;
/**
 * 
 */
public class FlipFitSlotsBusinessServices implements FlipFitSlotsBusinessInterface{
	public boolean updateAvailability(FlipFitSlots flipFitSlots) {
        System.out.println("Updating Slot Availability");
        return true;
    }
    public void getSlotDetails() {
        System.out.println("Getting Slot Details");
    }
}
