package com.cybaze.ccavenuetest.ccavenue.base.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.cybaze.ccavenuetest.ccavenue.base.callbacks.Callback;
import com.cybaze.ccavenuetest.ccavenue.utility.AvenuesParams;
import com.cybaze.ccavenuetest.ccavenue.utility.SecretConstants;

/**
 * Created by theapache64 on 17/8/17.
 */

public abstract class BaseCCAvenueActivity extends AppCompatActivity implements Callback {

    private static final String X = BaseCCAvenueActivity.class.getSimpleName();

    public void startPayment(final String orderId, String amount) {

        Intent wbIntent = new Intent(this, WebViewActivity.class);

        wbIntent.putExtra(AvenuesParams.MERCHANT_ID, SecretConstants.MERCHANT_ID); // eg: 131217
        wbIntent.putExtra(AvenuesParams.ACCESS_CODE, SecretConstants.ACCESS_CODE); //eg: AVEC71EE55BX39YDXB
        wbIntent.putExtra(AvenuesParams.ORDER_ID, orderId); //eg: 123123
        wbIntent.putExtra(AvenuesParams.CURRENCY, SecretConstants.CURRENCY); //eg: INR
        wbIntent.putExtra(AvenuesParams.AMOUNT, amount); //eg: 200

        wbIntent.putExtra(AvenuesParams.REDIRECT_URL, SecretConstants.REDIRECT_URL); //eg: https://yourdomain.com/CCAvenue/ccavResponseHandler.php
        wbIntent.putExtra(AvenuesParams.CANCEL_URL, SecretConstants.CANCEL_URL);//eg: https://yourdomain.com/CCAvenue/ccavResponseHandler.php
        wbIntent.putExtra(AvenuesParams.RSA_KEY_URL, SecretConstants.RSA_KEY_URL);//eg: https://yourdomain.com/CCAvenue/GetRSA.php

        startActivityForResult(wbIntent, WebViewActivity.RQ_CODE);
    }

    /**
     * @param requestCode
     * @param resultCode
     * @param data
     */

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == WebViewActivity.RQ_CODE) {

            switch (resultCode) {
                case WebViewActivity.RESULT_TRANSACTION_SUCCESS:
                    Log.i(X, "Transaction success");
                    onPaymentSuccess();
                    break;
                case WebViewActivity.RESULT_TRANSACTION_DECLINED:
                    Log.e(X, "Transaction declined");
                    onPaymentDeclined();
                    break;
                case WebViewActivity.RESULT_TRANSACTION_ABORTED:
                    Log.e(X, "Transaction aborted");
                    onPaymentAborted();
                    break;
                case WebViewActivity.RESULT_TRANSACTION_UNKNOWN:
                    Log.w(X, "Transaction unknown");
                    onPaymentUnknown();
                    break;
                default:
                    Log.w(X, "Transaction aborted by user");

                    onPaymentAbortedByUser();
            }

        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
