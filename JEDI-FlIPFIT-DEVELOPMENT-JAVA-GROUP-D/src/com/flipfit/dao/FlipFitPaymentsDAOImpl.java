package com.flipfit.dao;
import com.flipfit.bean.*;
import com.flipfit.constants.DBConstants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FlipFitPaymentsDAOImpl implements FlipFitPaymentsDAOInterface {
    public void setPaymentInfo(FlipFitPayments flipFitPayments){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    DBConstants.DB_URL,DBConstants.USER,DBConstants.PASSWORD);

            //PreparedStatement stmt = con.prepareStatement("REPLACE INTO Payments (userID, paymentType, paymentInfo) VALUES (?, ?, ?)");


            // Generate random integers in range 0 to 999
            //stmt.setInt(1, FFP.getUserID());
            //stmt.setInt(2, FFP.getPaymentType());
            //stmt.setString(3, FFP.getPaymentInfo());

            //int i = stmt.executeUpdate();
            //if(i==0){
            //    throw new SQLException("Creating payment failed. No rows affected");
            //}

            con.close();
        } catch(SQLException | ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
    }
    public void deletePaymentInfo(FlipFitPayments FFP) {

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    DBConstants.DB_URL,DBConstants.USER,DBConstants.PASSWORD);

            PreparedStatement stmt = con.prepareStatement("DELETE FROM Payments WHERE userId = ?");

            stmt.setInt(1, FFP.getUserID());

            int i = stmt.executeUpdate();
            System.out.println( i + " payment info deleted");

            con.close();
        } catch(SQLException | ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
    }
}
