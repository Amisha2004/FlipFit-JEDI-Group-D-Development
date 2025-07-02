/**
 * 
 */
package com.flipfit.business;

//import java.util.ArrayList;
import java.util.List;

import com.flipfit.bean.FlipFitGymCentre;

/**
 * 
 */
public class FlipFitUserBusinessServices {
   public boolean register(String userName,String password,String emailID,String phoneNumber,String role) {
	   System.out.println("Registered successfully;");
	   return true;
   }
   public boolean logIn(String emailId,String password) {
	   System.out.println("Login successfully;");
	   return true;
   }
   public boolean logOut() {
	   System.out.println("LogOut successfully;");
	   return true;
   }
   public List<FlipFitGymCentre> viewCentres() {
//	   List<FlipFitGymCentre> gymCentres = new ArrayList();
       System.out.println("Centres listed:> ");
       return null;
   }
}
