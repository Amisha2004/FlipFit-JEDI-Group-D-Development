package com.flipfit.dao;

import com.flipfit.bean.*;

import java.util.ArrayList;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class FlipFitAdminDAO {
    private static List<FlipFitUser> userList = new ArrayList();
    private static List<FlipFitGymCentre> gymCentres = new ArrayList<>();

    // Counters for simulating auto-incrementing IDs
    private static AtomicInteger gymIdCounter = new AtomicInteger(0);
    public FlipFitAdminDAO() {
        // Initialize with hardcoded data using setters
        if(userList.isEmpty()) {
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

            FlipFitGymCentre centre1 = new FlipFitGymCentre();
            centre1.setGymID(gymIdCounter.incrementAndGet());
            centre1.setGymName("Fitness Hub Koramangala");
            centre1.setOwnerID(owner1.getUserId()); // Owned by owner1
            centre1.setApprovalStatus(true);
            centre1.setCity("Bengaluru");
            centre1.setState("Karnataka");
            centre1.setPincode("560034");
            centre1.setSlot(Arrays.asList(900, 1000, 1100, 1700, 1800)); // Sample slots
            gymCentres.add(centre1);

            FlipFitGymCentre centre2 = new FlipFitGymCentre();
            centre2.setGymID(gymIdCounter.incrementAndGet());
            centre2.setGymName("Power House Marathahalli");
            centre2.setOwnerID(owner1.getUserId()); // Also owned by owner1
            centre2.setApprovalStatus(true);
            centre2.setCity("Bengaluru");
            centre2.setState("Karnataka");
            centre2.setPincode("560037");
            centre2.setSlot(Arrays.asList(800, 900, 1600)); // Sample slots
            gymCentres.add(centre2);

            FlipFitGymCentre centre3 = new FlipFitGymCentre();
            centre3.setGymID(gymIdCounter.incrementAndGet());
            centre3.setGymName("Mumbai Strength Gym");
            centre3.setOwnerID(owner2.getUserId()); // Owned by owner2
            centre3.setApprovalStatus(false);
            centre3.setCity("Mumbai");
            centre3.setState("Maharashtra");
            centre3.setPincode("400001");
            centre3.setSlot(Arrays.asList(700, 800, 1200, 1900)); // Sample slots
            gymCentres.add(centre3);
        }
    }
    public static List<FlipFitGymOwner> getPendingGymOwnerList(){
        System.out.println("Here is the pending owner list");
        List<FlipFitGymOwner> pendingGymOwners = new ArrayList<>();
        for(FlipFitUser user: userList){
            if(user.getRole() == 2) {
                FlipFitGymOwner gymOwner = (FlipFitGymOwner) user;
                if (!gymOwner.isApproved) {
                    pendingGymOwners.add(gymOwner);
                }
            }
        }
        return pendingGymOwners;
    }
    public static List<FlipFitGymOwner> getApprovedGymOwnerList(){
        System.out.println("Here is the approved gym owner list");
        List<FlipFitGymOwner> approvedOwners = new ArrayList<>();
        for(FlipFitUser user: userList){
            if(user.getRole() == 2) {
                FlipFitGymOwner gymOwner = (FlipFitGymOwner) user;
                if (gymOwner.isApproved) approvedOwners.add(gymOwner);
            }
        }
        return approvedOwners;
    }
    public static List<FlipFitCustomer> getCustomerList(){
        System.out.println("Here is the customer list");
        List<FlipFitCustomer> customers = new ArrayList<>();
        for(FlipFitUser user: userList){
            if(user.getRole() == 3){
                FlipFitCustomer customer = (FlipFitCustomer) user;
                customers.add(customer);
            }
        }
        return customers;
    }
    public static boolean validateOwner(int ownerId){
        for(FlipFitUser user: userList){
            if(user.getUserId() == ownerId) {
                FlipFitGymOwner gymOwner = (FlipFitGymOwner) user;
                gymOwner.setApproved(true);
                System.out.println("Validated owner -> " + ownerId);
                return true;
            }
        }
        System.out.println("Owner not found");
        return false;
    }
    public static boolean deleteOwner(int ownerId){
        for(FlipFitUser user: userList) {
            if (user.getUserId() == ownerId) {
                userList.remove(user);
                System.out.println("Deleted owner -> " + ownerId);
                return true;
            }
        }
        System.out.println("Owner not found");
        return false;
    }
    public static List<FlipFitGymCentre> getGymCentreUsingOwnerId(int ownerId){
        List<FlipFitGymCentre> centres = new ArrayList<>();
        for(FlipFitGymCentre gymCenter: gymCentres){
            if(gymCenter.getOwnerID() == ownerId){
                centres.add(gymCenter);
            }
        }
        return centres;
    }
}
