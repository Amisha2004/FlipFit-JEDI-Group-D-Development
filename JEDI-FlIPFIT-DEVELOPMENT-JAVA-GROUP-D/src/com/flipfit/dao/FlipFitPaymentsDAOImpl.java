package com.flipfit.dao;
import com.flipfit.bean.*;

public class FlipFitPaymentsDAOImpl implements FlipFitPaymentsDAOInterface {
    public void setPaymentInfo(FlipFitPayments flipFitPayments){
        System.out.println("Setting payment info");
    }
    public void deletePaymentInfo(FlipFitPayments FFP) {
        System.out.println("Deleting payment info");
    }
}
