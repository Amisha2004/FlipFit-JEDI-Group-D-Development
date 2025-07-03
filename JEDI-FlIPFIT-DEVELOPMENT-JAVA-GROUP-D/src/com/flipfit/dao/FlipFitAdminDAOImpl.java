package com.flipfit.dao;

import com.flipfit.bean.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlipFitAdminDAOImpl implements FlipFitAdminDAOInterface {

    @Override
    public List<FlipFitGymOwner> getPendingGymOwnerList(){
        List<FlipFitGymOwner> pendingOwners = new ArrayList<>();
        String sql = "SELECT u.userId, u.userName, go.aadharNumber FROM User u JOIN GymOwner go ON u.userId = go.userId WHERE go.isApproved = 0";

        try (Connection conn = GetConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                FlipFitGymOwner owner = new FlipFitGymOwner();
                owner.setUserId(rs.getInt("userId"));
                owner.setUserName(rs.getString("userName"));
                pendingOwners.add(owner);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return pendingOwners;
    }

    @Override
    public List<FlipFitGymOwner> getApprovedGymOwnerList(){
        List<FlipFitGymOwner> approvedOwners = new ArrayList<>();

        String sql = "SELECT u.userId, u.userName, go.aadharNumber FROM User u JOIN GymOwner go ON u.userId = go.userId WHERE go.isApproved = 1";

        try (Connection conn = GetConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                FlipFitGymOwner owner = new FlipFitGymOwner();
                owner.setUserId(rs.getInt("userId"));
                owner.setAadharNumber(rs.getString("aadharNumber"));
                owner.setUserName(rs.getString("userName"));
                approvedOwners.add(owner);
            }
        } catch (SQLException e) {
            System.err.println("SQL Error in getApprovedGymOwnerList: " + e.getMessage());
            e.printStackTrace(); // Keep this for full error details
        }
        return approvedOwners;
    }

    @Override
    public List<FlipFitUser> getUserList(){
        List<FlipFitUser> users = new ArrayList<>();
        String sql = "SELECT * FROM User";

        try (Connection conn = GetConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                // Assuming you have a User class
                FlipFitUser user = new FlipFitUser(); // Replace with actual User class
                user.setUserId(rs.getInt("userId"));
                user.setUserName(rs.getString("userName"));
                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return users;
    }

    @Override
    public boolean validateOwner(int ownerId){
        String sql = "UPDATE GymOwner SET isApproved = 1 WHERE userId = ?";
        try (Connection conn = GetConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, ownerId);
            System.out.println("Validated!");
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteOwner(int ownerId){
        String sql = "DELETE FROM User WHERE userId = ?";
        try (Connection conn = GetConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, ownerId);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public List<FlipFitGymCentre> getGymCentreUsingOwnerId(int ownerId){
        List<FlipFitGymCentre> gymCentres = new ArrayList<>();
        String sql = "SELECT * FROM GymCentre WHERE ownerId = ? AND approvalStatus = 1";

        try (Connection conn = GetConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, ownerId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    FlipFitGymCentre gymCentre = new FlipFitGymCentre();
                    gymCentre.setGymID(rs.getInt("gymId"));
                    gymCentre.setGymName(rs.getString("gymName"));
                    gymCentre.setState(rs.getString("state"));
                    gymCentre.setCity(rs.getString("city"));
                    gymCentres.add(gymCentre);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            // Consider logging the error or throwing a custom exception
        }
        return gymCentres;
    }
}
