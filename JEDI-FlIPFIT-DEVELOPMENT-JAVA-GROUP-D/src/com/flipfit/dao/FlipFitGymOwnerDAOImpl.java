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

    public List<FlipFitCustomer> getCustomerListByGymId(int gymId) {
        System.out.println("Here is the customer list");
        return null;
    }

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

    public boolean deleteGymOwner(int gymId) {
        return true;
    }
}