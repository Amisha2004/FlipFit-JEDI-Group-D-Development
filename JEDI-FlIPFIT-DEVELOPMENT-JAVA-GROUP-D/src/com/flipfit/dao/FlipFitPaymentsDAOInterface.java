package com.flipfit.dao;

import com.flipfit.bean.FlipFitPayments;

public interface FlipFitPaymentsDAOInterface {
    public void setPaymentInfo(FlipFitPayments flipFitPayments);
    public void deletePaymentInfo(FlipFitPayments FFP);
}
