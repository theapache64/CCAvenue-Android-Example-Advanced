package com.cybaze.ccavenuetest.ccavenue;

import android.content.Context;
import android.content.Intent;

import com.cybaze.ccavenuetest.ccavenue.base.activities.WebViewActivity;
import com.cybaze.ccavenuetest.ccavenue.utility.AvenuesParams;

/**
 * Created by theapache64 on 21/7/17.
 */

public class CCAvenue {

    //Payment gateway details
    private static final String MERCHANT_ID = "138917";
    private static final String ACCESS_CODE = "AVEY71EF55BX39YEXB";
    private static final String CURRENCY = "INR";
    private static final String REDIRECT_URL = "http://surabhicinemas.com/CC/ccavResponseHandler.php";
    private static final String CANCEL_URL = "http://surabhicinemas.com/CC/ccavResponseHandler.php";
    private static final String RSA_KEY_URL = "http://surabhicinemas.com/CC/GetRSA.php";


    public static void startPayment(final Context context, final String orderId, String amount) {

        Intent wbIntent = new Intent(context, WebViewActivity.class);

        wbIntent.putExtra(AvenuesParams.ACCESS_CODE, ACCESS_CODE);
        wbIntent.putExtra(AvenuesParams.MERCHANT_ID, MERCHANT_ID);
        wbIntent.putExtra(AvenuesParams.ORDER_ID, orderId);
        wbIntent.putExtra(AvenuesParams.CURRENCY, CURRENCY);
        wbIntent.putExtra(AvenuesParams.AMOUNT, amount);

        wbIntent.putExtra(AvenuesParams.REDIRECT_URL, REDIRECT_URL);
        wbIntent.putExtra(AvenuesParams.CANCEL_URL, CANCEL_URL);
        wbIntent.putExtra(AvenuesParams.RSA_KEY_URL, RSA_KEY_URL);

        context.startActivity(wbIntent);
    }
}
