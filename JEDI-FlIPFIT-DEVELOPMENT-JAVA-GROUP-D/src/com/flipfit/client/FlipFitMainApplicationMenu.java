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

			System.out.println(" Welcome To FlipFit Application!");
			while (true) {
				System.out.println("""
						Type:
						 1 -> Login
						 2 -> Register as Gym Owner
						 3 -> Register as Gym Customer
						 4 -> Exit
						""");

				choice = in.nextInt();

				switch (choice) {
					case 1: {
						System.out.println("Login");
						System.out.print("Enter your userName:> ");
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
								FlipFitUser user = flipFitAdminBusinessServices.logIn(admin);
								if (user != null) FlipFitAdminMenu.getFlipFitAdminMenu(admin);
								break;

							}
							case 1: {
								FlipFitCustomer gymCustomer = new FlipFitCustomer();
								gymCustomer.setUserName(username);
								gymCustomer.setPassword(password);
								FlipFitCustomerBusinessServices flipFitCustomerBusinessServices = new FlipFitCustomerBusinessServices();
								FlipFitUser user = flipFitCustomerBusinessServices.logIn(gymCustomer);
								if (user != null) FlipFitCustomerMenu.getFlipFitCustomerMenu(gymCustomer);
								break;
							}
							case 2: {
								FlipFitGymOwner gymOwner = new FlipFitGymOwner();
								gymOwner.setUserName(username);
								gymOwner.setPassword(password);
								FlipFitGymOwnerBusinessServices flipFitGymOwnerBusinessServices = new FlipFitGymOwnerBusinessServices();
								FlipFitUser user = flipFitGymOwnerBusinessServices.logIn(gymOwner);
								if (user != null) FlipFitGymOwnerMenu.getFlipFitGymOwnerMenu(gymOwner);
								break;
							}
						}
						break;

					}

					case 2: {
						System.out.println("Registration Page");
						System.out.println("Enter User Name: ");
						String userName = in.next();
						System.out.println("Enter Email Id: ");
						String emailId = in.next();
						System.out.println("Enter Phone Number: ");
						String phoneNumber = in.next();
						System.out.println("Enter Password: ");
						String password = in.next();
						FlipFitGymOwner gymOwner = new FlipFitGymOwner();
						gymOwner.setUserName(userName);
						gymOwner.setEmailID(emailId);
						gymOwner.setPhoneNumber(phoneNumber);
						gymOwner.setPassword(password);
						gymOwner.setRole(2);
						System.out.println("Enter GST Number: ");
						String gstNumber = in.next();
						System.out.println("Enter Aadhaar Number: ");
						String aadhaarNumber = in.next();
						gymOwner.setGstNumber(gstNumber);
						gymOwner.setAadharNumber(aadhaarNumber);
						gymOwner.setApproved(false);

						FlipFitGymOwnerBusinessServices flipFitGymOwnerBusinessServices = new FlipFitGymOwnerBusinessServices();
						if(flipFitGymOwnerBusinessServices.register(gymOwner)){
							System.out.println(gymOwner.getUserName() + " is successfully registered as a FlipFit Gym Owner!");
							System.out.println("You can Login now");
						}
						else{
							System.out.println("Please try again");
						}
						break;
					}

					case 3: {
						System.out.println("Registration Page");
						System.out.println("Enter User Name: ");
						String userName = in.next();
						System.out.println("Enter Email Id: ");
						String emailId = in.next();
						System.out.println("Enter Phone Number: ");
						String phoneNumber = in.next();
						System.out.println("Enter Password: ");
						String password = in.next();
						FlipFitCustomer customer = new FlipFitCustomer();
						customer.setUserName(userName);
						customer.setEmailID(emailId);
						customer.setPhoneNumber(phoneNumber);
						customer.setPassword(password);
						customer.setRole(3);
						FlipFitCustomerBusinessServices flipFitCustomerBusinessServices = new FlipFitCustomerBusinessServices();
						if(flipFitCustomerBusinessServices.register(customer)) {
							System.out.println(customer.getUserName() + " is successfully registered as a FlipFit Customer!");
							System.out.println("You can Login now");
						}
						else{
							System.out.println("Please try again");
						}
						break;
					}

					case 4: {
						System.out.println("Exit!");
						return;
					}
				}
			}
			}

        finally {
        	
        }
    }
}
                   