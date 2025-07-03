package com.flipfit.dao;

import com.flipfit.bean.*;
import com.flipfit.constants.DBConstants;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlipFitBookingDAOImpl implements FlipFitBookingDAOInterface{

    @Override
    public FlipFitBooking makeBooking(FlipFitBooking booking){
        String sql = "INSERT INTO Booking (bookingId, userID, slotID) VALUES (?, ?, ?, ?)";
        try (Connection conn = GetConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, booking.getBookingId());
            stmt.setInt(2, booking.getUserId());
            stmt.setInt(3, booking.getSlotId());

            int affectedRows = stmt.executeUpdate(); // Use executeUpdate() for INSERT
            if (affectedRows == 0) {
                throw new SlotBookingFailedException("Creating booking failed, no rows affected.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int bookingID = generatedKeys.getInt(1);
                    booking.setBookingId(bookingID);
                } else {
                    throw new SlotBookingFailedException("Creating booking failed, no ID obtained.");
                }
            }

        } catch (SlotBookingFailedException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return booking;
    }

    @Override
    public boolean deleteBooking(int bookingID){
        String sql = "DELETE FROM Booking WHERE bookingId = ?";
        try(Connection conn = GetConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, bookingID);
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new BookingCancellationFailedException("Deleting booking failed, no rows affected.");
            }
            return true;
        } catch (exception | SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public List<FlipFitBooking> getAllBookings(int userId){
        List<FlipFitBooking> bookings = new ArrayList<>(); // Initialize the list to an empty list

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    DBConstants.DB_URL, DBConstants.USER, DBConstants.PASSWORD);

            PreparedStatement stmt = con.prepareStatement("SELECT * FROM Booking WHERE userID = ?");
            stmt.setInt(1, userId);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int UserId = rs.getInt("userID");
                int slotId = rs.getInt("slotID");
                int bookingId = rs.getInt("bookingID");

                FlipFitBooking booking = new FlipFitBooking();
                booking.setUserId(UserId);
                booking.setSlotId(slotId);
                booking.setBookingId(bookingId);

                bookings.add(booking);
            }

            con.close();
        } catch (SQLException e) {
            System.out.println("Error getting all bookings: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return bookings;
    }

    @Override
    public List<FlipFitBooking> getBookingDetailsByBookingId(int bookingId){
        List bookings = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    DBConstants.DB_URL, DBConstants.USER, DBConstants.PASSWORD);

            PreparedStatement stmt = con.prepareStatement("SELECT * FROM Booking WHERE id = ?");
            stmt.setInt(1, bookingId);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int UserId = rs.getInt("userId");
                int SlotId = rs.getInt("slotId");

                FlipFitBooking booking = new FlipFitBooking();
                booking.setUserId(UserId);
                booking.setSlotId(SlotId);

                bookings.add(booking);
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error getting booking details: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return bookings;
    }
}
