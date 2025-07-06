package com.flipfit.dao;
import com.flipfit.bean.*;
import com.flipfit.constants.DBConstants;
import com.flipfit.exceptions.RegistrationFailedException;
import com.flipfit.exceptions.UpdationFailedException;

import java.sql.*;
import java.util.Random;

/**
 * Provides DAO implementations for user-related database operations.
 */
public class FlipFitUserDAOImpl implements FlipFitUserDAOInterface {
    Random rand = new Random();

    /**
     * login
     * @param username
     * @param password
     * @param roleId
     * @return FlipFitUser
     */
    @Override
    public FlipFitUser login(String username, String password, int roleId) {
        String sql = "SELECT * from User where userName=? and password=? and roleId=?";
        try (Connection conn = GetConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setInt(3, roleId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    FlipFitUser flipFitUser = new FlipFitUser();
                    flipFitUser.setUserName(username);
                    flipFitUser.setPassword(password);
                    flipFitUser.setRole(roleId);
                    flipFitUser.setUserId(rs.getInt("userId"));
                    flipFitUser.setEmailID(rs.getString("emailId"));
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


    /**
     * register
     *
     * @param FFU
     * @return
     */
    public FlipFitUser register(FlipFitUser FFU) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    DBConstants.DB_URL, DBConstants.USER, DBConstants.PASSWORD);

            PreparedStatement stmt = con.prepareStatement(
                    "INSERT INTO User (userName, password, emailId, phoneNumber, city, pinCode, roleId) VALUES (?, ?, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS // This flag tells JDBC to make generated keys available
            );


            stmt.setString(1, FFU.getUserName());
            stmt.setString(2, FFU.getPassword());
            stmt.setString(3, FFU.getEmailID());
            stmt.setString(4, FFU.getPhoneNumber());
            stmt.setString(5, FFU.getCity());
            stmt.setString(6, FFU.getPinCode());
            stmt.setInt(7, FFU.getRole());

            int i = stmt.executeUpdate();
            if (i > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        FFU.setUserId(generatedKeys.getInt(1)); // Get the first (and usually only) generated key
                    } else {
                        System.err.println("Warning: User inserted, but failed to retrieve auto-generated userId.");
                    }
                }

                System.out.println(i + " user added, ID: " + FFU.getUserId());
                con.close();
                return FFU;
            }
            else{
                con.close();
                throw new SQLException("User registration affected 0 rows.");
            }
        } catch (SQLException e)
        {
            System.err.println("Error during user registration: " + e.getMessage());
            return null;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteUser(int userId){
        System.out.println("Deleting user");
    }


    /**
     * updateUser
     *
     * @param FFU
     * @return
     */
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
            }
        } catch (UpdationFailedException e) {
            System.out.println(e.getMessage());
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


    /**
     * getUser
     *
     * @param userId
     * @return
     */
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
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return FFU;
    }
}


