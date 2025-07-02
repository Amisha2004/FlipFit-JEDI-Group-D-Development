/**
 * 
 */
package com.flipfit.business;
import java.util.ArrayList;
import java.util.List;

import com.flipfit.bean.*;
/**
 * 
 */
public class FlipFitGymOwnerBusinessServices extends FlipFitUserBusinessServices{
	
	public void addCentre(FlipFitGymCentre flipFitGymCentre){
        System.out.println("Added");
    }
	public void addSlot(int gymId, FlipFitSlots flipFitSlot){
       System.out.println("Added slot");
    }
	public List<FlipFitPayments> viewPayments() {
        System.out.println("Payments listed:> ");
        return null;
    }
	public List<FlipFitGymCentre> viewOwnCentres(FlipFitGymOwner flipFitGymOwner) {
//		List<FlipFitGymCentre> gymCentres = new ArrayList();
		System.out.println("Centres listed:> ");
        return null;
    }
}
