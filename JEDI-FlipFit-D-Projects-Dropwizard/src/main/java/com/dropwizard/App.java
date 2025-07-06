package com.dropwizard;

/**
 * Hello world!
 *
 */
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flipfit.business.*;
import com.flipfit.dao.*;
import com.flipfit.rest.*;

import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class App extends Application<Configuration> {
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    @Override
    public void initialize(Bootstrap<Configuration> b) {
    }

    @Override
    public void run(Configuration c, Environment e) throws Exception {
        LOGGER.info("Registering REST resources");
        FlipFitAdminDAOImpl flipFitAdminDAO = new FlipFitAdminDAOImpl();
        FlipFitAdminBusinessServices flipFitAdmin = new FlipFitAdminBusinessServices(flipFitAdminDAO);
        FlipFitUserBusinessServices flipFitUser = new FlipFitUserBusinessServices();
        FlipFitCustomerDAOImpl flipFitCustomerDAO = new FlipFitCustomerDAOImpl();
        FlipFitCustomerBusinessServices flipFitCustomer = new FlipFitCustomerBusinessServices(flipFitCustomerDAO);
        FlipFitGymCentreDAOImpl flipFitCentreDAO = new FlipFitGymCentreDAOImpl();
        FlipFitGymCentreBusinessServices flipFitCentre = new FlipFitGymCentreBusinessServices(flipFitCentreDAO);

        FlipFitBookingDAOImpl flipFitBookingDAO = new FlipFitBookingDAOImpl();
        FlipFitBookingBusinessServices bookingBusiness = new FlipFitBookingBusinessServices(flipFitBookingDAO);

        FlipFitGymOwnerDAOImpl flipFitGymOwnerDAO = new FlipFitGymOwnerDAOImpl();
        FlipFitGymOwnerBusinessServices gymOwnerBusiness = new FlipFitGymOwnerBusinessServices(flipFitGymOwnerDAO);

        FlipFitSlotDAOImpl flipFitSlotDAO = new FlipFitSlotDAOImpl();
        FlipFitSlotsBusinessServices slotsBusiness = new FlipFitSlotsBusinessServices(flipFitSlotDAO);

        e.jersey().register(new FlipFitAdminController(flipFitAdmin));
        e.jersey().register(new FlipFitCustomerController(bookingBusiness, flipFitCustomer, flipFitCentre));
        e.jersey().register(new FlipFitGymOwnerController(gymOwnerBusiness, slotsBusiness));
        e.jersey().register(new FlipFitMainApplicationController(flipFitUser, gymOwnerBusiness, flipFitCustomer));
        System.out.println("HERE");
    }

    public static void main(String[] args) throws Exception {
        new App().run(args);
    }
}