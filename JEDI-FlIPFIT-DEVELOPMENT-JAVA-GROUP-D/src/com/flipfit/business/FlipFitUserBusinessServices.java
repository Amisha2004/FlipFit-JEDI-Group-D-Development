/**
 * 
 */
package com.flipfit.business;

//import java.util.ArrayList;
import java.util.List;

import com.flipfit.bean.*;

/**
 * 
 */
public class FlipFitUserBusinessServices implements FlipFitUserBusinessInterface{
   public boolean register(String userName,String password,String emailID,String phoneNumber,String role) {
	   System.out.println("Registered successfully;");
	   return true;
   }
   public void logIn(FlipFitUser FlipFitUser) {
	   System.out.println("Login successfully;");
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
