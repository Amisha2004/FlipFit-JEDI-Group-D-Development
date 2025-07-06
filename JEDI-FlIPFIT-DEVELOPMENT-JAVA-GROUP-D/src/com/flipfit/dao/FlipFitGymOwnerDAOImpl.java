package com.flipfit.dao;

import com.flipfit.bean.FlipFitGymCentre;
import com.flipfit.bean.FlipFitGymOwner;
import com.flipfit.bean.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.sql.*;

@SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
public class FlipFitGymOwnerDAOImpl implements FlipFitGymOwnerDAOInterface {
    /**
     * addCentre
     * @param centre
     * @return
     */
    @Override
    public FlipFitGymCentre addCentre(FlipFitGymCentre centre) {
        String sql = "INSERT INTO GymCentre (gymName, ownerId, approvalStatus, city, state, pincode) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = GetConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, centre.getGymName());
            stmt.setInt(2, centre.getOwnerID());
            stmt.setBoolean(3, centre.isApprovalStatus());
            stmt.setString(4, centre.getCity());
            stmt.setString(5, centre.getState());
            stmt.setString(6, centre.getPincode());
            int affectedRows = stmt.executeUpdate(); // Use executeUpdate() for INSERT
            if (affectedRows == 0) {
                throw new SQLException("Creating centre failed, no rows affected.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int gymID = generatedKeys.getInt(1);
                    centre.setGymID(gymID);
                } else {
                    throw new SQLException("Creating centre failed, no ID obtained.");
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return centre;
    }
    /**
     * getCustomerListByGymId
     * @param ownerId
     * @return
     */
    public List<FlipFitCustomer> getCustomerListByGymId(int ownerId) {
        List<FlipFitCustomer> customerList = new ArrayList<>();

        String sql = "SELECT DISTINCT U.userId, U.userName, U.emailId, U.phoneNumber " +
                "FROM User U " +
                "JOIN Booking B ON U.userId = B.userId " +
                "JOIN Slots S ON B.slotId = S.slotId " +
                "JOIN GymCentre GC ON S.gymId = GC.gymId " +
                "WHERE GC.ownerId = ?"; // Filter by the gym owner's user ID

        // Use try-with-resources to ensure Connection, PreparedStatement, and ResultSet are closed automatically
        try (Connection conn = GetConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, ownerId); // Set the gymId parameter

            try (ResultSet rs = stmt.executeQuery()) {
                // Populate the list of FlipFitCustomer objects
                while (rs.next()) {
                    int userId = rs.getInt("userId");
                    String name = rs.getString("userName");
                    String email = rs.getString("emailId");
                    String phone = rs.getString("phoneNumber");

                    FlipFitCustomer customer = new FlipFitCustomer();
                    customer.setUserId(userId);
                    customer.setUserName(name);
                    customer.setEmailID(email);
                    customer.setPhoneNumber(phone);
                    customerList.add(customer);
                }
            }

        } catch (SQLException e) {
            System.err.println("Database error while fetching customer list for gymId " + ownerId + ": " + e.getMessage());
        }
        // No need for a finally block as try-with-resources handles closing resources
        return customerList;
    }

    /**
     * viewOwnCentresByOwnerID
     * @param userId
     * @return
     */
    @Override
    public List<FlipFitGymCentre> viewOwnCentres(int userId) {
        List<FlipFitGymCentre> gymcentres = new ArrayList<>();
        String sql = "SELECT gymId, gymName, ownerId, city, state, pincode FROM GymCentre where ownerId=?";
        try (Connection conn = GetConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, userId);
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
     * editDetails
     * @param owner
     * @return
     */
    @Override
    public FlipFitGymOwner editDetails(FlipFitGymOwner owner) {
        int userId = owner.getUserId();
        String sql = "UPDATE GymOwner SET panId=?, aadharNumber=? ,gstNumber=? WHERE userId=?";

        try (Connection conn = GetConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, owner.getPanNumber());
            stmt.setString(2, owner.getAadharNumber());
            stmt.setString(3,owner.getGstNumber());
            stmt.setInt(4, userId);
            try(ResultSet rs = stmt.executeQuery()) {
                if(rs.next()) {
                    return owner;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }


    /**
     * addGymOwner
     * @param owner
     * @param user
     * @return
     */
    @Override
    public FlipFitGymOwner addGymOwner(FlipFitGymOwner owner, FlipFitUser user) {
        String sql = "INSERT INTO GymOwner (userId ,panId, aadharNumber, gstNumber, isApproved) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = GetConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, user.getUserId());
            stmt.setString(2, owner.getPanNumber());
            stmt.setString(3, owner.getAadharNumber());
            stmt.setString(4, owner.getGstNumber());
            stmt.setBoolean(5, false);
            int affectedRows = stmt.executeUpdate(); // Use executeUpdate() for INSERT
            if (affectedRows == 0) {
                throw new SQLException("Creating owner failed, no rows affected.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        owner.setUserId(user.getUserId());
        return owner;
    }

    /**
     * deletegymOwner
     * @param gymId
     * @return
     */
    @Override
    public boolean deleteGymOwner(int gymId) { // Renamed parameter for clarity
        // SQL query to delete a gym owner record based on their userId
        String sql = "DELETE FROM GymCentre WHERE gymId = ?";

        try (Connection conn = GetConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Set the userId parameter in the prepared statement
            stmt.setInt(1, gymId);

            // Execute the delete operation
            int affectedRows = stmt.executeUpdate();

            // Check if any rows were affected (meaning the deletion was successful)
            if (affectedRows > 0) {
                System.out.println("Gym Center with gym ID " + gymId + " successfully deleted.");
                return true;
            } else {
                System.out.println("No Gym Center found with Gym ID " + gymId + " or deletion failed.");
                return false;
            }

        } catch (SQLException e) {
            // Print the SQL exception message for debugging
            System.err.println("Error deleting Gym Center with Gym ID " + gymId + ": " + e.getMessage());
            // Optionally, log the exception for more detailed error tracking
            return false;
        }
    }
}