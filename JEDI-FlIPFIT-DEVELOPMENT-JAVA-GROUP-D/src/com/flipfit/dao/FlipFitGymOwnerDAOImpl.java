package com.flipfit.dao;

import com.flipfit.bean.FlipFitGymCentre;
import com.flipfit.bean.FlipFitGymOwner;
import com.flipfit.bean.*;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
public class FlipFitGymOwnerDAOImpl implements FlipFitGymOwnerDAOInterface {

    // In-memory data stores
    private final List<FlipFitGymCentre> gymCentres = new ArrayList<>();
    private final List<FlipFitUser> users = new ArrayList<>(); // Stores all users (including gym owners as users)


    public void addCentre(FlipFitGymCentre flipFitGymCentre) {
        System.out.println("Adding centre");
    }

    public List<FlipFitCustomer> getCustomerListByGymId(int gymId) {
        System.out.println("Here is the customer list");
        return null;
    }

    public List<FlipFitGymCentre> viewOwnCentres(int ownerId) {
        List<FlipFitGymCentre> ownerCentres = new ArrayList<>();
        for (FlipFitGymCentre centre : gymCentres) {
            if (centre.getOwnerID() == ownerId) {
                ownerCentres.add(centre);
            }
        }
        System.out.println("Viewing centres for owner ID: " + ownerId + ". Found " + ownerCentres.size() + " centres.");
        return ownerCentres;
    }

    public FlipFitGymOwner editDetails(FlipFitGymOwner flipFitGymOwner) {
        System.out.println("Editing centre");
        return flipFitGymOwner;
    }
}