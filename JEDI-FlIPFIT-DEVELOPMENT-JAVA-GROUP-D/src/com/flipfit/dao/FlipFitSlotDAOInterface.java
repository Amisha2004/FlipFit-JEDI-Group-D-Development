package com.flipfit.dao;

import com.flipfit.bean.FlipFitSlots;

import java.util.List;

public interface FlipFitSlotDAOInterface {
    public FlipFitSlots addSlot(FlipFitSlots slot);

    public boolean deleteSlot(FlipFitSlots slot);

    public boolean updateSlot(FlipFitSlots slot);

    public List<FlipFitSlots> getAllSlots(int centreId);

    public FlipFitSlots getSlotById(int slotId);

    public FlipFitSlots getSlotDetails(int startTime, int centreId);
}
