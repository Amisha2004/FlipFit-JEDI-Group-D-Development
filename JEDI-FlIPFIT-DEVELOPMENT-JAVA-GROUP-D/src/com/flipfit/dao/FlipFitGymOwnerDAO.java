package com.flipfit.dao;

import com.flipfit.bean.FlipFitGymCentre;
import com.flipfit.bean.FlipFitGymOwner;
import com.flipfit.bean.*;

import java.util.ArrayList;
import java.util.Arrays; // For easily creating List<Integer>
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger; // For generating unique IDs

@SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
public class FlipFitGymOwnerDAO {

    // In-memory data stores
    private static final List<FlipFitGymCentre> gymCentres = new ArrayList<>();
    private static final List<FlipFitUser> users = new ArrayList<>(); // Stores all users (including gym owners as users)

    // Counters for simulating auto-incrementing IDs
    private static final AtomicInteger gymIdCounter = new AtomicInteger(0); // For FlipFitGymCentre
    private static final AtomicInteger userIdCounter = new AtomicInteger(0); // For FlipFitUser

    // --- Sample Hardcoded Data (Initialization Block) ---
    static {
        // Sample Users
        // User 1: Acts as a Gym Owner
        FlipFitUser user1 = new FlipFitUser();
        user1.setUserId(userIdCounter.incrementAndGet());
        user1.setUserName("OwnerAlpha");
        user1.setRole(2); // Assuming 2 is the role for Gym Owner
        user1.setEmailID("owner.alpha@example.com");
        user1.setPhoneNumber("9876512340");
        user1.setPassword("ownerpass1");
        users.add(user1);

        // User 2: Acts as another Gym Owner
        FlipFitUser user2 = new FlipFitUser();
        user2.setUserId(userIdCounter.incrementAndGet());
        user2.setUserName("OwnerBeta");
        user2.setRole(2); // Assuming 2 is the role for Gym Owner
        user2.setEmailID("owner.beta@example.com");
        user2.setPhoneNumber("9988776655");
        user2.setPassword("ownerpass2");
        users.add(user2);

        // User 3: Regular user (not a gym owner)
        FlipFitUser user3 = new FlipFitUser();
        user3.setUserId(userIdCounter.incrementAndGet());
        user3.setUserName("RegularUser1");
        user3.setRole(1); // Assuming 1 is the role for a regular User
        user3.setEmailID("user1@example.com");
        user3.setPhoneNumber("7778889990");
        user3.setPassword("userpass");
        users.add(user3);


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
        users.add(owner1);

        FlipFitGymOwner owner2 = new FlipFitGymOwner();
        owner2.setUserId(3);
        owner2.setUserName("gymowner2_pending");
        owner2.setPassword("owner456");
        owner2.setEmailID("owner2@flipfit.com");
        owner2.setPhoneNumber("7777777777");
        owner2.setRole(2);
        owner2.setGstNumber("GSTIN67890");
        owner2.setAadharNumber("AADHAAR67890");
        owner2.setApproved(false); // This owner is pending approval
        users.add(owner2);

        FlipFitCustomer customer1 = new FlipFitCustomer();
        customer1.setUserId(4);
        customer1.setUserName("customer1");
        customer1.setPassword("custom123");
        customer1.setEmailID("customer1@flipfit.com");
        customer1.setPhoneNumber("6666666666");
        customer1.setRole(3); // 3 for Customer
        customer1.setPaymentInfo("UPI: customer1@bank");
        users.add(customer1);


        // Sample Gym Centres (linked to the owners above)
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

        // Update gymsOwned list for owners
        owner1.setGymsOwned(Arrays.asList(centre1.getGymID(), centre2.getGymID()));
        owner2.setGymsOwned(List.of(centre3.getGymID()));
    }

    public static void addCentre(FlipFitGymCentre flipFitGymCentre) {

    }

    public static List<FlipFitCustomer> getCustomerList() {
        System.out.println("Here is the customer list");
        List<FlipFitCustomer> customers = new ArrayList<>();
        for(FlipFitUser user: users){
            if(user.getRole() == 3){
                FlipFitCustomer customer = (FlipFitCustomer) user;
                customers.add(customer);
            }
        }
        return customers;
    }

    public static void addSlot(FlipFitSlots flipFitSlot) {
    }

    public static List<FlipFitGymCentre> viewOwnCentres(int ownerId) {
        List<FlipFitGymCentre> ownerCentres = new ArrayList<>();
        for (FlipFitGymCentre centre : gymCentres) {
            if (centre.getOwnerID() == ownerId) {
                ownerCentres.add(centre);
            }
        }
        System.out.println("Viewing centres for owner ID: " + ownerId + ". Found " + ownerCentres.size() + " centres.");
        return ownerCentres;
    }

}