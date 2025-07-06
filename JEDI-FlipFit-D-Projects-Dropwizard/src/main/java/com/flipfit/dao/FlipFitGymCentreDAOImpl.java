package com.flipfit.dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.Random;
import com.flipfit.bean.FlipFitGymCentre;
import com.flipfit.constants.DBConstants;
import com.flipfit.bean.FlipFitSlots;
import com.flipfit.exceptions.UpdationFailedException;

public class FlipFitGymCentreDAOImpl implements FlipFitGymCentreDAOInterface{
    Random rand = new Random();
    /**
     * updateGymCentre
     * @param FFGC
     * @return
     */
    public FlipFitGymCentre updateGymCentre(FlipFitGymCentre FFGC){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    DBConstants.DB_URL,DBConstants.USER,DBConstants.PASSWORD);

            PreparedStatement stmt = con.prepareStatement(("UPDATE GymCentre SET ownerId = ?, approvalStatus = ?, city = ?, state = ?, pincode = ? WHERE gymId = ?"));


            stmt.setInt(1, FFGC.getOwnerID());
            stmt.setBoolean(2, FFGC.isApprovalStatus());
            stmt.setString(3, FFGC.getCity());
            stmt.setString(4, FFGC.getState());
            stmt.setString(5, FFGC.getPincode());
            stmt.setInt(6, FFGC.getGymID());

            int i = stmt.executeUpdate();
            if (i > 0)
                System.out.println( i + " centre updated");
            else {
                throw new UpdationFailedException();
            }
            con.close();
        } catch (UpdationFailedException e) {
            System.out.println(e.getMessage());
        }
        catch(SQLException | ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        return FFGC;
    };

    /**
     * deleteGymCentre
     * @param FFGC
     */
    public void deleteGymCentre(FlipFitGymCentre FFGC){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    DBConstants.DB_URL,DBConstants.USER,DBConstants.PASSWORD);

            PreparedStatement stmt = con.prepareStatement("DELETE FROM GymCentre WHERE gymId=(?)");

            stmt.setInt(1, FFGC.getGymID());

            int i = stmt.executeUpdate();
            System.out.println( i + " centre deleted");

            con.close();
        } catch(SQLException | ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
    };

    /**
     * viewGymCentre
     * @param centreID
     */
    public boolean isGymCentreAvailable(int centreID){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    DBConstants.DB_URL,DBConstants.USER,DBConstants.PASSWORD);

            PreparedStatement stmt = con.prepareStatement("SELECT * FROM GymCentre WHERE gymId=(?)");

            stmt.setInt(1, centreID);

            ResultSet rs = stmt.executeQuery();
            boolean res = rs.next();
            con.close();
            return res;

        } catch(SQLException | ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        return false;
    };

    /**
     * viewCentres
     * @param city
     * @return
     */
    public ArrayList<FlipFitGymCentre> viewCentres(String city){
        ArrayList<FlipFitGymCentre> ffarray = new ArrayList<FlipFitGymCentre>();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    DBConstants.DB_URL,DBConstants.USER,DBConstants.PASSWORD);

            PreparedStatement stmt = con.prepareStatement("SELECT * FROM GymCentre WHERE city = ?");
            stmt.setString(1, city);

            ResultSet rs = stmt.executeQuery();


            while(rs.next()){
                FlipFitGymCentre FFGC = new FlipFitGymCentre();
                FFGC.setGymID(rs.getInt("gymId"));
                FFGC.setOwnerID(rs.getInt("ownerId"));
                FFGC.setApprovalStatus(rs.getBoolean("approvalStatus"));
                FFGC.setCity(rs.getString("city"));
                FFGC.setState(rs.getString("state"));
                FFGC.setPincode(rs.getString("pincode"));

                ffarray.add(FFGC);
            };


            con.close();
        } catch(SQLException | ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        return ffarray;
    }

    /**
     * viewAvailableSlots
     * @param FFGC
     * @return
     */
    public ArrayList<FlipFitSlots> viewAvailableSlots(FlipFitGymCentre FFGC){
        ArrayList<FlipFitSlots> ffarray = new ArrayList<FlipFitSlots>();

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    DBConstants.DB_URL,DBConstants.USER,DBConstants.PASSWORD);

            PreparedStatement stmt = con.prepareStatement("SELECT * FROM Slots WHERE gymId = ? and seatsAvailable > 0");
            stmt.setInt(1, FFGC.getGymID());

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                FlipFitSlots FFS = new FlipFitSlots();
                FFS.setSlotId(rs.getInt("slotId"));
                FFS.setGymId(rs.getInt("gymId"));
                FFS.setSeatsAvailable(rs.getInt("seatsAvailable"));
                FFS.setSlotStartTime(rs.getObject("slotTime", java.sql.Time.class));

                ffarray.add(FFS);
            };


            con.close();
        } catch(SQLException | ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        return ffarray;
    };


}