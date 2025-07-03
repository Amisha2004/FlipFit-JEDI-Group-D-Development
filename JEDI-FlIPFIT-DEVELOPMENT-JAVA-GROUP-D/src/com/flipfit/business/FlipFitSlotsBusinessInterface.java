/**
 * 
 */
package com.flipfit.business;

import com.flipfit.bean.FlipFitSlots;

/**
 * 
 */
public interface FlipFitSlotsBusinessInterface {
	public boolean updateAvailability(FlipFitSlots flipFitSlots);
    public void getSlotDetails();
    public FlipFitSlots addSlot(FlipFitSlots flipFitSlots);
    public boolean deleteSlot(int slotId);
}
