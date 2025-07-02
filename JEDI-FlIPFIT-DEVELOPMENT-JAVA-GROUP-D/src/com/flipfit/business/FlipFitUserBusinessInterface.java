/**
 * 
 */
package com.flipfit.business;

import java.util.List;

import com.flipfit.bean.FlipFitGymCentre;
import com.flipfit.bean.FlipFitUser;

/**
 * 
 */
public interface FlipFitUserBusinessInterface {
	 public boolean register(String userName,String password,String emailID,String phoneNumber,String role);
	 public FlipFitUser logIn(FlipFitUser FlipFitUser);
	 public boolean logOut();
	 public List<FlipFitGymCentre> viewCentres();
}
