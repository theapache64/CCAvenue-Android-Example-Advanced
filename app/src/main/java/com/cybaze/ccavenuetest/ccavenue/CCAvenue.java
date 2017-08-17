package com.cybaze.ccavenuetest.ccavenue;

import android.content.Context;
import android.content.Intent;

import com.cybaze.ccavenuetest.ccavenue.base.activities.WebViewActivity;
import com.cybaze.ccavenuetest.ccavenue.utility.AvenuesParams;
import com.cybaze.ccavenuetest.utils.SecretConstants;

/**
 * Created by theapache64 on 21/7/17.
 */

public class CCAvenue {


    public static void startPayment(final Context context, final String orderId, String amount) {

        Intent wbIntent = new Intent(context, WebViewActivity.class);

        wbIntent.putExtra(AvenuesParams.MERCHANT_ID, SecretConstants.MERCHANT_ID); // eg: 131217
        wbIntent.putExtra(AvenuesParams.ACCESS_CODE, SecretConstants.ACCESS_CODE); //eg: AVEC71EE55BX39YDXB
        wbIntent.putExtra(AvenuesParams.ORDER_ID, orderId); //eg: 123123
        wbIntent.putExtra(AvenuesParams.CURRENCY, SecretConstants.CURRENCY); //eg: INR
        wbIntent.putExtra(AvenuesParams.AMOUNT, amount); //eg: 200

        wbIntent.putExtra(AvenuesParams.REDIRECT_URL, SecretConstants.REDIRECT_URL); //eg: https://yourdomain.com/CCAvenue/ccavResponseHandler.php
        wbIntent.putExtra(AvenuesParams.CANCEL_URL, SecretConstants.CANCEL_URL);//eg: https://yourdomain.com/CCAvenue/ccavResponseHandler.php
        wbIntent.putExtra(AvenuesParams.RSA_KEY_URL, SecretConstants.RSA_KEY_URL);//eg: https://yourdomain.com/CCAvenue/GetRSA.php

        context.startActivity(wbIntent);
    }


}
