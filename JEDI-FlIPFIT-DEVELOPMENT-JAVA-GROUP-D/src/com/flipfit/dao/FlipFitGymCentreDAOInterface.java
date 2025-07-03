package com.flipfit.dao;

import com.flipfit.bean.FlipFitGymCentre;
import com.flipfit.bean.FlipFitSlots;
import com.flipfit.constants.DBConstants;
import com.flipfit.exceptions.UpdationFailedException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public interface FlipFitGymCentreDAOInterface {
    public FlipFitGymCentre updateGymCentre(FlipFitGymCentre FFGC);
    public void deleteGymCentre(FlipFitGymCentre FFGC);
    public boolean isGymCentreAvailable(int centreID);
    public ArrayList<FlipFitGymCentre> viewCentres(String city);
    public ArrayList<FlipFitSlots> viewAvailableSlots(FlipFitGymCentre FFGC);
}
