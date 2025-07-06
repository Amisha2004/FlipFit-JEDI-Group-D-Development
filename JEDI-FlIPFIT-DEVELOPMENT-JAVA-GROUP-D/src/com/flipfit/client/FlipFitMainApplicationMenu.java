/**
 *
 */

package com.flipfit.client;


import com.flipfit.bean.*;
import com.flipfit.business.*;
import com.flipfit.constants.ColorConstants;
import com.flipfit.dao.FlipFitCustomerDAOImpl;
import com.flipfit.exceptions.InvalidChoiceException;
import com.flipfit.exceptions.InvalidLoginException;

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

			while (true) {
				System.out.println(ColorConstants.BLUE + "===============================" + ColorConstants.RESET);
				System.out.println(ColorConstants.BLUE + "      WELCOME TO FLIPFIT       " + ColorConstants.RESET);
				System.out.println(ColorConstants.BLUE + "===============================" + ColorConstants.RESET);
				System.out.println(ColorConstants.CYAN + """
						Type:
						 1 -> Login
						 2 -> Register as Gym Owner
						 3 -> Register as Gym Customer
						 4 -> Exit
						""" + ColorConstants.RESET);

				choice = in.nextInt();

				switch (choice) {
					case 1: {
						System.out.println(ColorConstants.CYAN + "==========Login Page==========" + ColorConstants.RESET);
            
						System.out.print(ColorConstants.YELLOW + "Enter your User Name : " + ColorConstants.RESET);
						String userName = in.next();
						System.out.print(ColorConstants.YELLOW + "Enter your password: " + ColorConstants.RESET);
						String password = in.next();

						System.out.print(ColorConstants.YELLOW +"""
								Enter your role:>
								1 -> Admin
								2 -> GymOwner
								3 -> Customer
								"""+ ColorConstants.RESET);


						int role = in.nextInt();

						switch (role) {
							case 1: {
								FlipFitAdmin admin = new FlipFitAdmin();
								admin.setUserName(userName);
								admin.setPassword(password);
								admin.setRole(role);
								FlipFitAdminBusinessServices flipFitAdminBusinessServices = new FlipFitAdminBusinessServices();
								FlipFitUser user = flipFitAdminBusinessServices.logIn(admin);
								if (user != null) {
									System.out.println(ColorConstants.GREEN + "Login Successful" + ColorConstants.RESET);
									FlipFitAdminMenu.getFlipFitAdminMenu(user);
								}
								break;

							}
							case 2: {
								FlipFitGymOwner gymOwner = new FlipFitGymOwner();
								gymOwner.setUserName(userName);
								gymOwner.setPassword(password);
								gymOwner.setRole(role);
								FlipFitGymOwnerBusinessServices flipFitGymOwnerBusinessServices = new FlipFitGymOwnerBusinessServices();
								FlipFitUser user = flipFitGymOwnerBusinessServices.logIn(gymOwner);
								if (user != null) {
									System.out.println(ColorConstants.GREEN + "Login Successful" + ColorConstants.RESET);
									FlipFitGymOwnerMenu.getFlipFitGymOwnerMenu(user);
								}
								break;
							}
							case 3: {
								FlipFitCustomer gymCustomer = new FlipFitCustomer();
								gymCustomer.setUserName(userName);
								gymCustomer.setPassword(password);
								gymCustomer.setRole(role);
								FlipFitCustomerBusinessServices flipFitCustomerBusinessServices = new FlipFitCustomerBusinessServices();
								FlipFitUser user = flipFitCustomerBusinessServices.logIn(gymCustomer);
								if (user != null) {
									System.out.println(ColorConstants.GREEN + "Login Successful" + ColorConstants.RESET);
									FlipFitCustomerMenu.getFlipFitCustomerMenu(user);
								}
								break;
							}
						}
						break;

					}

					case 2: {
						System.out.println(ColorConstants.CYAN + "==========Registration Page for Gym Owner==========" + ColorConstants.RESET);
						System.out.println(ColorConstants.YELLOW + "Enter User Name: " + ColorConstants.RESET);
						String userName = in.next();
						System.out.println(ColorConstants.YELLOW + "Enter Email Id: "  + ColorConstants.RESET);
						String emailId = in.next();
						System.out.println(ColorConstants.YELLOW + "Enter Phone Number: " + ColorConstants.RESET);
						String phoneNumber = in.next();
						System.out.println(ColorConstants.YELLOW + "Enter Password: " + ColorConstants.RESET);
						String password = in.next();
						System.out.println(ColorConstants.YELLOW + "Enter City: " + ColorConstants.RESET);
						String city = in.next();
						System.out.println(ColorConstants.YELLOW + "Enter Pin Code: " + ColorConstants.RESET);
						String pincode = in.next();
						FlipFitUser user = new FlipFitUser();
						user.setUserName(userName);
						user.setEmailID(emailId);
						user.setPhoneNumber(phoneNumber);
						user.setPassword(password);
						user.setCity(city);
						user.setPinCode(pincode);
						user.setRole(2);
						FlipFitGymOwner gymOwner = new FlipFitGymOwner();
						System.out.println(ColorConstants.YELLOW + "Enter GST Number: " + ColorConstants.RESET);
						String gstNumber = in.next();
						System.out.println(ColorConstants.YELLOW + "Enter Aadhaar Number: " + ColorConstants.RESET);
						String aadhaarNumber = in.next();
						System.out.println(ColorConstants.YELLOW + "Enter PAN Number: " + ColorConstants.RESET);
						String panNumber = in.next();
						gymOwner.setGstNumber(gstNumber);
						gymOwner.setAadharNumber(aadhaarNumber);
						gymOwner.setPanNumber(panNumber);
						gymOwner.setApproved(false);

						FlipFitGymOwnerBusinessServices flipFitGymOwnerBusinessServices = new FlipFitGymOwnerBusinessServices();
						FlipFitUser newUser = flipFitGymOwnerBusinessServices.register(user);
						if(newUser!=null && flipFitGymOwnerBusinessServices.addGymOwner(gymOwner, newUser)!=null){
							System.out.println(ColorConstants.GREEN + newUser.getUserName() + " is successfully registered as a FlipFit Gym Owner!" + ColorConstants.RESET);
							System.out.println(ColorConstants.GREEN + "You can Login now" + ColorConstants.RESET);
						}
						else{
							System.out.println(ColorConstants.RED + "Registration failed, Please try again" + ColorConstants.RESET);
						}
						break;
					}

					case 3: {
						System.out.println(ColorConstants.CYAN + "==========Registration Page for Gym Customer==========" + ColorConstants.RESET);
						System.out.println(ColorConstants.YELLOW + "Enter User Name: " + ColorConstants.RESET);
						String userName = in.next();
						System.out.println(ColorConstants.YELLOW + "Enter Email Id: "  + ColorConstants.RESET);
						String emailId = in.next();
						System.out.println(ColorConstants.YELLOW + "Enter Phone Number: " + ColorConstants.RESET);
						String phoneNumber = in.next();
						System.out.println(ColorConstants.YELLOW + "Enter Password: " + ColorConstants.RESET);
						String password = in.next();
						System.out.println(ColorConstants.YELLOW + "Enter City: " + ColorConstants.RESET);
						String city = in.next();
						System.out.println(ColorConstants.YELLOW + "Enter Pin Code: " + ColorConstants.RESET);
						String pincode = in.next();
						FlipFitCustomer customer = new FlipFitCustomer();
						customer.setUserName(userName);
						customer.setEmailID(emailId);
						customer.setPhoneNumber(phoneNumber);
						customer.setPassword(password);
						customer.setCity(city);
						customer.setPinCode(pincode);
						customer.setRole(3);
						FlipFitCustomerBusinessServices flipFitCustomerBusinessServices = new FlipFitCustomerBusinessServices();
						if(flipFitCustomerBusinessServices.register(customer)!=null) {
							System.out.println(ColorConstants.GREEN + customer.getUserName() + " is successfully registered as a FlipFit Customer!" + ColorConstants.RESET);
							System.out.println(ColorConstants.GREEN + "You can Login now" + ColorConstants.RESET);
						}
						else{
							System.out.println(ColorConstants.RED + "Registration failed, Please try again" + ColorConstants.RESET);
						}
						break;
					}

					case 4: {
						System.out.println(ColorConstants.RED + "Exit!" + ColorConstants.RESET);
						return;
					}
					default: {
						throw new InvalidChoiceException(ColorConstants.RED + "Invalid choice entered: " + choice + ColorConstants.RESET);
					}
				}
			}
		} catch (InvalidChoiceException e) {
			throw new RuntimeException(e);
		}
    }
}
                   