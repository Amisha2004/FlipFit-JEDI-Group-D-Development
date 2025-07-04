package com.flipfit.dao;
import java.sql.*;
import java.time.LocalTime;
import java.util.*;

import com.flipfit.bean.*;
import com.flipfit.constants.DBConstants;
import com.flipfit.exceptions.*;

public class FlipFitSlotDAOImpl {

    public FlipFitSlots addSlot(FlipFitSlots slot){
        String sql = "INSERT INTO Slots (gymId, slotTime, seatsAvailable, maxCapacity, slotDate) VALUES ( ?, ?, ?, ?, ?)";
        try (Connection conn = GetConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, slot.getGymId());
            stmt.setObject(2, slot.getSlotStartTime());
            stmt.setInt(3, slot.getSeatsAvailable());
            stmt.setInt(4, slot.getMaxSeats());
            stmt.setObject(5, slot.getSlotDate());

            int affectedRows = stmt.executeUpdate(); // Use executeUpdate() for INSERT
            if (affectedRows == 0) {
                throw new SQLException("Creating slot failed, no rows affected.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int slotID = generatedKeys.getInt(1);
                    slot.setSlotId(slotID);
                } else {
                    throw new SlotInsertionFailedException("Creating slot failed, no ID obtained.");
                }
            }
        } catch (SlotInsertionFailedException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Error adding slot: " + e);
        }
        return slot;
    }

    public boolean deleteSlot(FlipFitSlots slot){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    DBConstants.DB_URL, DBConstants.USER, DBConstants.PASSWORD);

            PreparedStatement stmt = con.prepareStatement("DELETE FROM Slots WHERE gymId = ? AND slotId = ?");

            stmt.setInt(1, slot.getGymId());
            stmt.setObject(2, slot.getSlotId());

            int i = stmt.executeUpdate();
            System.out.println(i + " slot deleted");

            // Close the connection
            con.close();

            return i > 0;

        } catch (Exception e) {
            System.out.println("Error deleting slot: " + e);
        }
        return false;
    }

    public boolean updateSlot(FlipFitSlots slot){
        String sql = "UPDATE Slots SET gymId = ?, slotTime = ?, seatsAvailable = ?, maxCapacity = ?, slotDate = ? WHERE slotId = ?";
        try (Connection conn = GetConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, slot.getGymId());
            stmt.setObject(2, slot.getSlotStartTime());
            stmt.setInt(3, slot.getSeatsAvailable());
            stmt.setInt(4, slot.getMaxSeats());
            stmt.setObject(5, slot.getSlotDate());


            int affectedRows = stmt.executeUpdate(); // Use executeUpdate() for INSERT
            if (affectedRows == 0) {
                throw new UpdationFailedException("Updating slots failed");
            }
        } catch (SQLException | UpdationFailedException e) {
            System.out.println("Error updating slot: " + e);
            return false;
        }
        return true;
    }

    public List<FlipFitSlots> getAllSlots(int centreId){
        List<FlipFitSlots> slots = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    DBConstants.DB_URL, DBConstants.USER, DBConstants.PASSWORD);

            PreparedStatement stmt = con.prepareStatement("SELECT * FROM Slots WHERE gymId = ?");
            stmt.setInt(1, centreId);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int slotID = rs.getInt("slotId");
                Time StartTime = rs.getObject("slotTime", Time.class);;
                int SeatsAvailable = rs.getInt("seatsAvailable");

                FlipFitSlots slot = new FlipFitSlots();
                slot.setSlotId(slotID);
                slot.setGymId(centreId);
                slot.setSlotStartTime(StartTime);
                slot.setSeatsAvailable(SeatsAvailable);
                slots.add(slot);
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error retrieving slots: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return slots;
    }

    public FlipFitSlots getSlotById(int slotId){
        String sql = "SELECT * FROM Slots WHERE slotId=?";
        try (Connection conn = GetConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, slotId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int slotid = rs.getInt("slotId");
                    int seatsAvailable = rs.getInt("seatsAvailable");
                    Time slotTime = rs.getObject("slotTime", Time.class);
                    int centreID = rs.getInt("centreId");
                    FlipFitSlots slot = new FlipFitSlots();
                    slot.setSlotId(slotid);
                    slot.setSlotStartTime(slotTime);
                    slot.setSeatsAvailable(seatsAvailable);
                    slot.setGymId(centreID);

                    return slot;
                } else {
                    throw new SQLException("Slot details not found for slotID = " + slotId);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public FlipFitSlots getSlotDetails(Time startTime, int centreId){
        String sql = "SELECT * FROM Slots WHERE slotTime = ? AND gymId = ?";
        try (Connection conn = GetConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setObject(1, startTime);
            stmt.setInt(2, centreId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int slotid = rs.getInt("slotId");
                    int seatsAvailable = rs.getInt("seatsAvailable");

                    FlipFitSlots slot = new FlipFitSlots();
                    slot.setSlotId(slotid);
                    slot.setSlotStartTime(startTime);
                    slot.setSeatsAvailable(seatsAvailable);
                    slot.setGymId(centreId);

                    return slot;
                } else {
                    throw new SQLException("Slot details not found for startTime = " + startTime + " and centreID = " + centreId);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
