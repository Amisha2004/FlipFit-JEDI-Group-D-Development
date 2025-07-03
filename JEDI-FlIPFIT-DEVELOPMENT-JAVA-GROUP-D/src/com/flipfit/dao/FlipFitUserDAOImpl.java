package com.flipfit.dao;
import com.flipfit.bean.*;
import com.flipfit.constants.DBConstants;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.Random;

public class FlipFitUserDAOImpl implements FlipFitUserDAOInterface {
    Random rand = new Random();

    @Override
    public FlipFitUser login(String emailId, String password) {
        String sql = "SELECT * from User where emailID=? and password=? and roleId=0";
        try (Connection conn = GetConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, emailId);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    FlipFitUser flipFitUser = new FlipFitUser();
                    flipFitUser.setEmailID(emailId);
                    flipFitUser.setPassword(password);
                    flipFitUser.setUserId(rs.getInt("userId"));
                    flipFitUser.setRole(rs.getInt("roleId"));
                    flipFitUser.setUserName(rs.getString("userName"));
                    return flipFitUser;
                }
                else{
                    return null;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public boolean register(FlipFitUser FFU) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    DBConstants.DB_URL, DBConstants.USER, DBConstants.PASSWORD);

            PreparedStatement stmt = con.prepareStatement("INSERT INTO User VALUES (?, ?, ?, ?, ?, ?, ?, ?)");


            // Generate random integers in range 0 to 999
            FFU.setUserId(rand.nextInt(1000));
            stmt.setInt(1, FFU.getUserId());
            stmt.setString(2, FFU.getUserName());
            stmt.setString(3, FFU.getPassword());
            stmt.setString(4, FFU.getEmailID());
            stmt.setString(5, FFU.getPhoneNumber());
            stmt.setString(6, FFU.getCity());
            stmt.setString(7, FFU.getPinCode());
            stmt.setInt(8, FFU.getRole());



            int i = stmt.executeUpdate();
            if (i > 0) {
                System.out.println(i + " user added");
                return true;
            }
            else{
                throw new RegistrationFailedException();
                return false;
            }
            con.close();
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public void deleteUser(int userId){
        System.out.println("Deleting user");
    }

    public FlipFitUser updateUser(FlipFitUser FFU) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    DBConstants.DB_URL, DBConstants.USER, DBConstants.PASSWORD);

            PreparedStatement stmt = con.prepareStatement(("UPDATE User SET userName = ?, roleId =? , emailId = ?, phoneNumber = ?, password = ? WHERE userId = ?"));

            stmt.setString(1, FFU.getUserName());
            stmt.setInt(2, FFU.getRole());
            stmt.setString(3, FFU.getEmailID());
            stmt.setString(4, FFU.getPhoneNumber());
            stmt.setString(5, FFU.getPassword());
            stmt.setInt(6, FFU.getUserId());

            int i = stmt.executeUpdate();
            con.close();
            if(i > 0) {
                System.out.println(i + " user changed");
                return FFU;
            }
            else {
                throw new UpdationFailedException();
                return null;
            }
        } catch (UpdationFailedException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public FlipFitUser getUser(int userId){
        FlipFitUser FFU = new FlipFitUser();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    DBConstants.DB_URL, DBConstants.USER, DBConstants.PASSWORD);

            PreparedStatement stmt = con.prepareStatement("SELECT * FROM User WHERE userId = ?");
            stmt.setInt(1, userId);

            ResultSet rs = stmt.executeQuery();
            rs.next();
            FFU.setUserName(rs.getString("userName"));
            FFU.setUserId(rs.getInt("userId"));
            FFU.setPassword(rs.getString("password"));
            FFU.setPhoneNumber(rs.getString("phoneNumber"));
            FFU.setRole(rs.getInt("roleId"));
            FFU.setEmailID(rs.getString("emailId"));
            FFU.setCity(rs.getString("city"));
            FFU.setPinCode(rs.getString("pinCode"));

            int i = stmt.executeUpdate();
            System.out.println(i + " user fetched");

            con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return FFU;
    }
}


