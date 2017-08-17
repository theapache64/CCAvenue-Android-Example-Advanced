package com.cybaze.ccavenuetest.ccavenue.base.callbacks;

public interface Callback {

    void onPaymentDeclined();

    void onPaymentSuccess();

    void onPaymentAborted();

    void onPaymentAbortedByUser();

    void onPaymentUnknown();
}