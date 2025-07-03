package com.flipfit.dao;

import com.flipfit.bean.FlipFitCustomer;
import com.flipfit.bean.FlipFitGymCentre;
import com.flipfit.bean.FlipFitGymOwner;
import com.flipfit.bean.FlipFitUser;

import java.util.ArrayList;
import java.util.List;

public interface FlipFitAdminDAOInterface {
    public List<FlipFitGymOwner> getPendingGymOwnerList();
    public List<FlipFitGymOwner> getApprovedGymOwnerList();
    public List<FlipFitUser> getUserList();
    public boolean validateOwner(int ownerId);
    public boolean deleteOwner(int ownerId);
    public List<FlipFitGymCentre> getGymCentreUsingOwnerId(int ownerId);
}

