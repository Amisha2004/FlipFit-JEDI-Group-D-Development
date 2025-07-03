package com.flipfit.dao;

import com.flipfit.bean.*;
import java.util.*;
import java.sql.*;

public class FlipFitCustomerDAOImpl implements FlipFitCustomerDAOInterface{
    /**
     * viewBookedSlots
     * @param userID The ID of the user.
     * @return A list of FlipFitBooking objects for the specified user.
     */
    public List<FlipFitSlots> viewBookedSlots(int userID) {
        List<FlipFitSlots> bookedSlots = new ArrayList<>();
        String sql = "SELECT * FROM Booking WHERE userID = ?";
        try (Connection conn = GetConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, userID);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                FlipFitSlots slot = new FlipFitSlots();
                slot.setSlotId(rs.getInt("slotID"));
                bookedSlots.add(slot);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return bookedSlots;
    }

    /**
     * checkBookingConflicts
     * @param userId The ID of the user.
     * @return A FlipFitBooking object
     */
    public FlipFitBooking checkBookingConflicts(int userId, int slotTime) {
        String sql = "SELECT b.* FROM Booking b " +
                "JOIN Slots s ON b.slotID = s.slotID " +
                "WHERE b.userID = ? AND s.slotTime = ?)";

        try (Connection conn = GetConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Set the parameters for the query
            stmt.setInt(1, userId);
            stmt.setInt(2, slotTime);

            try (ResultSet rs = stmt.executeQuery()) {
                // If rs.next() is true, it means we found at least one conflicting booking.
                if (rs.next()) {
                    System.out.println("Conflict found! User already has a booking at this time.");
                    // Create a FlipFitBooking object and populate it with data from the conflicting booking.
                    FlipFitBooking conflictingBooking = new FlipFitBooking();
                    conflictingBooking.setBookingId(rs.getInt("bookingID"));
                    conflictingBooking.setUserId(rs.getInt("userID"));
                    conflictingBooking.setSlotId(rs.getInt("slotID"));

                    return conflictingBooking;
                }
            }
        } catch (SQLException e) {
            // It's good practice to log the exception
            System.err.println("SQL Error while checking for booking conflicts: " + e.getMessage());
            e.printStackTrace();
        }

        // If we reach this point, no conflicting booking was found.
        System.out.println("No conflicts found for this time slot.");
        return null;
    }

    /**
     * viewCentres
     * Retrieves a list of all gym centres.
     * @return A list of FlipFitGymCentre objects.
     */
    public List<FlipFitGymCentre> viewGymCentres() {
        List<FlipFitGymCentre> gymcentres = new ArrayList<>();
        String sql = "SELECT * FROM GymCentre";
        try (Connection conn = GetConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                FlipFitGymCentre gymcentre = new FlipFitGymCentre();
                gymcentre.setGymID(rs.getInt("gymID"));
                gymcentre.setGymName(rs.getString("gymName"));
                gymcentre.setOwnerID(rs.getInt("ownerID"));
                gymcentre.setCity(rs.getString("city"));
                gymcentre.setState(rs.getString("state"));
                gymcentre.setPincode(rs.getString("pincode"));
                gymcentres.add(gymcentre);
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return gymcentres;
    }

    /**
     * makePayment
     * @param userID The ID of the user.
     * @return True
     */
    public boolean makePayment(int userID) {
        return false;
    }

    /**
     * viewPaymentDetails
     * @param userID
     */
    public void viewPaymentDetails(int userID) {

    }
}