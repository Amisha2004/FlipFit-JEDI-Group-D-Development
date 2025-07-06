package com.flipfit.dao;

import com.flipfit.bean.FlipFitCustomer;
import com.flipfit.bean.FlipFitGymCentre;
import com.flipfit.bean.FlipFitGymOwner;
import com.flipfit.bean.FlipFitUser;

import java.util.List;

public interface FlipFitGymOwnerDAOInterface {
    public FlipFitGymCentre addCentre(FlipFitGymCentre flipFitGymCentre);

    public List<FlipFitCustomer> getCustomerListByGymId(int gymId);

    public List<FlipFitGymCentre> viewOwnCentres(int userId);

    public FlipFitGymOwner editDetails(FlipFitGymOwner owner);

    public FlipFitGymOwner addGymOwner(FlipFitGymOwner owner, FlipFitUser user);

    public boolean deleteGymOwner(int gymId);
}
