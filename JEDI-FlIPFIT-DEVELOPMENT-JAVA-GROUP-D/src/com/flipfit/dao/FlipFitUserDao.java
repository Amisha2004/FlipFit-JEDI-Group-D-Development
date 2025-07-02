package com.flipfit.dao;
import com.flipfit.bean.*;
import java.util.ArrayList;
import java.util.List;

public class FlipFitUserDao {
    private final List<FlipFitUser> userList = new ArrayList<>();

    public void FlipFitUserDAO() {
        // Initialize with hardcoded data using setters
        FlipFitAdmin admin = new FlipFitAdmin();
        admin.setUserId(1);
        admin.setUserName("admin");
        admin.setPassword("admin123");
        admin.setEmailID("admin@flipfit.com");
        admin.setPhoneNumber("9999999999");
        admin.setRole(1); // 1 for Admin
        userList.add(admin);

        FlipFitGymOwner owner1 = new FlipFitGymOwner();
        owner1.setUserId(2);
        owner1.setUserName("gymowner1");
        owner1.setPassword("owner123");
        owner1.setEmailID("owner1@flipfit.com");
        owner1.setPhoneNumber("8888888888");
        owner1.setRole(2); // 2 for GymOwner
        owner1.setGstNumber("GSTIN12345");
        owner1.setAadharNumber("AADHAR12345");
        owner1.setApproved(true);
        userList.add(owner1);

        FlipFitGymOwner owner2 = new FlipFitGymOwner();
        owner2.setUserId(3);
        owner2.setUserName("gymowner2_pending");
        owner2.setPassword("owner456");
        owner2.setEmailID("owner2@flipfit.com");
        owner2.setPhoneNumber("7777777777");
        owner2.setRole(2);
        owner2.setGstNumber("GSTIN67890");
        owner2.setAadharNumber("AADHAR67890");
        owner2.setApproved(false); // This owner is pending approval
        userList.add(owner2);

        FlipFitCustomer customer1 = new FlipFitCustomer();
        customer1.setUserId(4);
        customer1.setUserName("customer1");
        customer1.setPassword("cust123");
        customer1.setEmailID("customer1@flipfit.com");
        customer1.setPhoneNumber("6666666666");
        customer1.setRole(3); // 3 for Customer
        customer1.setPaymentInfo("UPI: customer1@bank");
        userList.add(customer1);
    }

    // Method to get ALL users
    public List<FlipFitUser> getAllUsers() {
        return userList;
    }

    public FlipFitUser login(String email, String password) {
        for (FlipFitUser user : userList) {
            if (user.getEmailID().equalsIgnoreCase(email) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public boolean register(FlipFitUser user) {
        for (FlipFitUser existingUser : userList) {
            if (existingUser.getEmailID().equalsIgnoreCase(user.getEmailID())) {
                System.out.println("DAO: Registration failed. Email already in use.");
                return false;
            }
        }
        user.setUserId(userList.size() + 1);
        userList.add(user);
        return true;
    }
}
