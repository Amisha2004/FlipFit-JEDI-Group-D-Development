package com.flipfit.dao;

import com.flipfit.bean.*;

import java.time.LocalTime;
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
        String sql = "SELECT * FROM Booking WHERE userId = ?";
        try (Connection conn = GetConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
            System.out.println("Executing query: ");
            stmt.setInt(1, userID);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                FlipFitSlots slot = new FlipFitSlots();
                slot.setSlotId(rs.getInt("slotId"));
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
    public FlipFitBooking checkBookingConflicts(int userId, Time slotTime) {
        String sql = "SELECT b.* FROM Booking b " +
                "JOIN Slots s ON b.slotId = s.slotId " +
                "WHERE b.userId = ? AND s.slotTime = ?"; // <-- HERE IS THE EXTRA ')'

        try (Connection conn = GetConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Set the parameters for the query
            stmt.setInt(1, userId);
            stmt.setObject(2, slotTime);

            try (ResultSet rs = stmt.executeQuery()) {
                // If rs.next() is true, it means we found at least one conflicting booking.
                if (rs.next()) {
                    System.out.println("Conflict found! User already has a booking at this time.");
                    // Create a FlipFitBooking object and populate it with data from the conflicting booking.
                    FlipFitBooking conflictingBooking = new FlipFitBooking();
                    conflictingBooking.setBookingId(rs.getInt("bookingId"));
                    conflictingBooking.setUserId(rs.getInt("userId"));
                    conflictingBooking.setSlotId(rs.getInt("slotId"));

                    return conflictingBooking;
                }
            }
        } catch (SQLException e) {
            // It's good practice to log the exception
//            System.err.println("SQL Error while checking for booking conflicts: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("SQL Error while checking for booking conflicts: " + e.getMessage());
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
        String sql = "SELECT * FROM GymCentre WHERE approvalStatus = 1";
        try (Connection conn = GetConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                FlipFitGymCentre gymcentre = new FlipFitGymCentre();
                gymcentre.setGymID(rs.getInt("gymId"));
                gymcentre.setGymName(rs.getString("gymName"));
                gymcentre.setOwnerID(rs.getInt("ownerId"));
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
    public boolean makePayment(int userID, String paymentInfo) {
        Connection conn = null;
        try {
            conn = GetConnection.getConnection();
            // Start the transaction by disabling auto-commit
            conn.setAutoCommit(false);

            // --- Query 1: Insert a new record into the Customer table ---
            // WARNING: This assumes your Customer table allows multiple rows for the same userID.
            // If userID is a PRIMARY KEY, this will fail on the second payment for the same user.
            String insertCustomerSql = "INSERT INTO GymCustomer (userId, paymentInfo) VALUES (?, ?)";
            try (PreparedStatement insertCustomerStmt = conn.prepareStatement(insertCustomerSql)) {
                insertCustomerStmt.setInt(1, userID);
                insertCustomerStmt.setString(2, paymentInfo);
                insertCustomerStmt.executeUpdate();
            }

            // --- Query 2: Insert into the Payments table ---
            // The 'paymentID' is not specified here because it is set to AUTO_INCREMENT
            // in the database, which generates it automatically.
            String insertPaymentSql = "INSERT INTO Payments (userId, paymentInfo) VALUES (?, ?)";
            try (PreparedStatement insertPaymentStmt = conn.prepareStatement(insertPaymentSql)) {
                insertPaymentStmt.setInt(1, userID);
                insertPaymentStmt.setString(2, paymentInfo);
                insertPaymentStmt.executeUpdate();
            }

            // If both queries succeed without error, commit the transaction to save the changes.
            conn.commit();
            System.out.println("Payment successful. New customer and payment records created for user ID: " + userID);
            return true;

        } catch (SQLException e) {
            // If any error occurs (like a primary key violation), roll back all changes.
            System.err.println("Transaction failed for user ID: " + userID + ". Rolling back changes.");
            System.err.println("SQL Error: " + e.getMessage());
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex) {
                System.err.println("Error during transaction rollback: " + ex.getMessage());
            }
            return false;

        } finally {
            // Always clean up by restoring auto-commit and closing the connection.
            try {
                if (conn != null) {
                    conn.setAutoCommit(true);
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }



    /**
     * viewPaymentDetails
     * @param userID
     */
    public List<FlipFitPayments> viewPaymentDetails(int userID) {
        // Create a list to hold the payment objects.
        List<FlipFitPayments> paymentList = new ArrayList<>();

        // The SQL query selects all columns from the Payments table for a specific user.
        // "ORDER BY paymentDate DESC" ensures the newest payments appear first.
        String sql = "SELECT * FROM Payments WHERE userId = ?";

        try (Connection conn = GetConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            // Set the userID parameter in the query.
            stmt.setInt(1, userID);

            // Execute the query and get the results.
            try (ResultSet rs = stmt.executeQuery()) {
                // Loop through each row in the result set.
                while (rs.next()) {
                    // For each row, create a new FlipFitPayments object.
                    FlipFitPayments payment = new FlipFitPayments();

                    // Populate the object with data from the current row.
                    payment.setPaymentID(rs.getInt("paymentId"));
                    payment.setUserID(rs.getInt("userId"));
                    payment.setPaymentInfo(rs.getString("paymentInfo"));

                    // Add the populated object to our list.
                    paymentList.add(payment);
                }
            }
        } catch (SQLException e) {
            // Log any potential database errors.
            System.err.println("SQL Error while viewing payment details: " + e.getMessage());
            e.printStackTrace();
        }

        // Return the list of payments. If no payments were found, the list will be empty.
        return paymentList;
    }

}