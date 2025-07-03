package com.flipfit.dao;

import com.flipfit.bean.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlipFitAdminDAOImpl implements FlipFitAdminDAOInterface {

    @Override
    public List<FlipFitGymOwner> getPendingGymOwnerList(){
        List<FlipFitGymOwner> pendingOwners = new ArrayList<>();
        String sql = "SELECT ownerID, aadharNumber FROM GymOwner WHERE approved = 0";

        try (Connection conn = GetConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                FlipFitGymOwner owner = new FlipFitGymOwner();
                owner.setUserId(rs.getInt("ownerID"));
                owner.setAadharNumber(rs.getString("aadharNumber"));
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
        String sql = "SELECT ownerID, aadharNumber FROM GymOwner WHERE approved = 1";

        try (Connection conn = GetConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                FlipFitGymOwner owner = new FlipFitGymOwner();
                owner.setUserId(rs.getInt("ownerID"));
                owner.setAadharNumber(rs.getString("aadharNumber"));
                approvedOwners.add(owner);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
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
        String sql = "UPDATE GymOwner SET approved = 1 WHERE ownerID = ?";
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
    public boolean deleteOwner(int ownerId){
        String sql = "DELETE FROM GymOwner WHERE ownerID = ?";
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
        String sql = "SELECT * FROM GymCentre WHERE ownerID = ? AND approved = 1";

        try (Connection conn = GetConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, ownerId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    FlipFitGymCentre gymCentre = new FlipFitGymCentre();
                    gymCentre.setGymID(rs.getInt("gymID"));
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
