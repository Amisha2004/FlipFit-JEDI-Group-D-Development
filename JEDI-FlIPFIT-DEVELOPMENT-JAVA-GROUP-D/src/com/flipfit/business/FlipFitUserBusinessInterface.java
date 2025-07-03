/**
 * 
 */
package com.flipfit.business;

import com.flipfit.bean.FlipFitUser;

/**
 * 
 */
public interface FlipFitUserBusinessInterface {
	 public FlipFitUser register(FlipFitUser flipFitUser);
	 public FlipFitUser logIn(FlipFitUser FlipFitUser);
	public void deleteUser(int userId);

	public FlipFitUser updateUser(FlipFitUser FFU);

	public FlipFitUser getUser(int userId);
}
