package com.flipfit.dao;

import com.flipfit.bean.FlipFitCustomer;
import com.flipfit.bean.FlipFitGymCentre;
import com.flipfit.bean.FlipFitGymOwner;

import java.util.ArrayList;
import java.util.List;

public interface FlipFitGymOwnerDAOInterface {
    public void addCentre(FlipFitGymCentre flipFitGymCentre);

    public List<FlipFitCustomer> getCustomerListByGymId(int gymId);

    public List<FlipFitGymCentre> viewOwnCentres(int ownerId);

    public FlipFitGymOwner editDetails(FlipFitGymOwner flipFitGymOwner);
}
