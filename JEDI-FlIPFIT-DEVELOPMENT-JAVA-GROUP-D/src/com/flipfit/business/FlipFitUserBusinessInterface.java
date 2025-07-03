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
	 public boolean register(FlipFitUser flipFitUser);
	 public FlipFitUser logIn(FlipFitUser FlipFitUser);
	public void deleteUser(int userId);

	public FlipFitUser updateUser(FlipFitUser FFU);

	public FlipFitUser getUser(int userId);
}
