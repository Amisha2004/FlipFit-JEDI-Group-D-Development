/**
 * 
 */

package com.flipfit.client;


import com.flipfit.bean.*;
import com.flipfit.business.*;

import java.util.*;

/**
 * 
 */
public class FlipFitMainApplicationMenu{
    
    /**
     * @param
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
    	
        try {
        	Scanner in = new Scanner(System.in);
			int choice = 0;
			
			System.out.println(" Welcome To FlipFit Application!" );
			    
			    System.out.println( """
			            Type:
			             1 -> Login
			             2 -> Registration
			             3 -> Exit
			            """ );

			    choice = in.nextInt();
			
			        switch (choice) {
			            case 1: {
			                System.out.println("Login");
			                System.out.print("Enter your userName:> " );
			                String username = in.next();
			                System.out.print("Enter your password:> ");
			                String password = in.next();

			                System.out.print("""
                                    Enter your role:>
                                    0 -> Admin
                                    1 -> Customer
                                    2 -> GymOwner
                                    """);

			                int role = in.nextInt();
			                
			                switch (role) {
			                case 0: {
			                	FlipFitAdmin admin = new FlipFitAdmin();
			                    admin.setUserName(username);
			                    admin.setPassword(password);
			                    FlipFitAdminBusinessServices flipFitAdminBusinessServices = new FlipFitAdminBusinessServices();
								flipFitAdminBusinessServices.logIn(admin);
			                    FlipFitAdminMenu.getFlipFitAdminMenu(admin);
			                    break;
			                   
			                 }
			                case 1: {
			                	 FlipFitCustomer gymCustomer = new FlipFitCustomer();
				                 gymCustomer.setUserName(username);
				                 gymCustomer.setPassword(password);
				                 FlipFitCustomerBusinessServices flipFitCustomerBusinessServices = new FlipFitCustomerBusinessServices();
								 flipFitCustomerBusinessServices.logIn(gymCustomer);
	                             FlipFitCustomerMenu.getFlipFitCustomerMenu(gymCustomer);
                                 break;
			                 }
			                case 2: {
			                    FlipFitGymOwner gymOwner = new FlipFitGymOwner();
			                    gymOwner.setUserName(username);
			                    gymOwner.setPassword(password);
			                    FlipFitGymOwnerBusinessServices flipFitGymOwnerBusinessServices = new FlipFitGymOwnerBusinessServices();
								flipFitGymOwnerBusinessServices.logIn(gymOwner);
			                    FlipFitGymOwnerMenu.getFlipFitGymOwnerMenu(gymOwner);
			                    break;
			                }
			            }
			            break;
			                
			            }
			            
			            case 2: {
			            	  System.out.println("Registration Page");
			                  System.out.println("Enter User Name: ");
			                  String userName = in.nextLine();
			                  System.out.println("Enter Email Id: ");
			                  String emailId = in.nextLine();
			                  System.out.println("Enter Phone Number: ");
			                  String phoneNumber = in.nextLine();
			                  System.out.println("Enter Password: ");
			                  String password = in.nextLine();
			                  System.out.println("Enter role: "
			                          + "1 -> Customer "
			                          + "2 -> Gym Owner");
			                  int role = in.nextInt();
			                  FlipFitUser user = new FlipFitUser();
			                  user.setUserName(userName);
			                  user.setEmailID(emailId);
			                  user.setPhoneNumber(phoneNumber);
			                  user.setPassword(password);
			                  user.setRole(role);
			                  switch(role) {
			                      case 1: {
			                          FlipFitCustomer customer = new FlipFitCustomer();
			                          customer.setUserName(userName);
			                          customer.setEmailID(emailId);
			                          customer.setPhoneNumber(phoneNumber);
			                          customer.setPassword(password);
			                          
			                          System.out.println(customer.getUserName()+ " is successfully registered as a FlipFit Customer!");
			                          System.out.println("You can Login now");
			                          break;
			                      }
			                      case 2: {
			                          System.out.println("Enter GST Number: ");
			                          String gstNumber = in.nextLine();
			                          System.out.println("Enter Aadhaar Number: ");
			                          String aadhaarNumber = in.nextLine();
			                          FlipFitGymOwner gymOwner = new FlipFitGymOwner();
			                          gymOwner.setUserName(userName);
			                          gymOwner.setEmailID(emailId);
			                          gymOwner.setPhoneNumber(phoneNumber);
			                          gymOwner.setPassword(password);
			                          gymOwner.setGstNumber(gstNumber);
			                          gymOwner.setAadharNumber(aadhaarNumber);
			                          gymOwner.setApproved(false);
			                          
			                          System.out.println(gymOwner.getUserName()+ " is successfully registered as a FlipFit Gym Owner!");
			                          System.out.println("You can Login now");
			                          break;
			                      }
			                      
			                  }
			                break;
			            }

			            case 3:{
			                System.out.println("Exit!");
			                break;
			            }
			        }
        }
        finally {
        	
        }
    }
}
                   