package com.cybaze.ccavenuetest;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.cybaze.ccavenuetest.ccavenue.base.activities.BaseCCAvenueActivity;
import com.cybaze.ccavenuetest.utils.PermissionUtils;

import java.util.Locale;
import java.util.Random;

public class MainActivity extends BaseCCAvenueActivity implements PermissionUtils.Callback {

    //Product details
    private static final String AMOUNT = "1";//Rs.1

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new PermissionUtils(this, this, this).begin();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == PermissionUtils.RQ_CODE_ASK_PERMISSION) {

            boolean isAllPermissionGranted = true;
            for (final int grantResult : grantResults) {
                if (grantResult != PackageManager.PERMISSION_GRANTED) {
                    isAllPermissionGranted = false;
                    break;
                }
            }

            if (isAllPermissionGranted) {
                onAllPermissionGranted();
            } else {
                onPermissionDenial();
            }
        }
    }

    @Override
    public void onAllPermissionGranted() {
        ((TextView) findViewById(R.id.tvTotalPrice)).setText(String.format(Locale.getDefault(), "Total amount: Rs. %s", AMOUNT));
        findViewById(R.id.bPay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPayment(new Random().nextInt(10000) + "", AMOUNT);
            }
        });
    }


    @Override
    public void onPermissionDenial() {
        Toast.makeText(this, "Insufficient permission", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onPaymentDeclined() {

    }

    @Override
    public void onPaymentSuccess() {

    }

    @Override
    public void onPaymentAborted() {

    }

    @Override
    public void onPaymentAbortedByUser() {

    }

    @Override
    public void onPaymentUnknown() {

    }
}
