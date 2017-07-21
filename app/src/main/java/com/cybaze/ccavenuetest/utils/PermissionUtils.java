
package com.cybaze.ccavenuetest.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;

import org.jetbrains.annotations.NotNull;

/**
 * Created by theapache64 on 5/1/17.
 */

public class PermissionUtils {

    public static final int RQ_CODE_ASK_PERMISSION = 1;

    private static final String[] PERMISSIONS_NEEDED = new String[]{
            Manifest.permission.SEND_SMS,
            Manifest.permission.RECEIVE_SMS,
            Manifest.permission.READ_SMS
    };

    private final Context context;
    private final Callback callback;
    private final Activity activity;

    public PermissionUtils(@NotNull Context context, @NotNull Callback callback, @Nullable Activity activity) {
        this.context = context;
        this.callback = callback;
        this.activity = activity;
    }

    public void begin() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            boolean isAllPermissionAccepted = true;
            for (final String perm : PERMISSIONS_NEEDED) {
                if (ActivityCompat.checkSelfPermission(context, perm) != PackageManager.PERMISSION_GRANTED) {
                    isAllPermissionAccepted = false;
                    break;
                }
            }

            if (!isAllPermissionAccepted) {
                if (activity != null) {
                    activity.requestPermissions(PERMISSIONS_NEEDED, RQ_CODE_ASK_PERMISSION);
                } else {
                    callback.onPermissionDenial();
                }
            } else {
                callback.onAllPermissionGranted();
            }

        } else {
            callback.onAllPermissionGranted();
        }

    }

    public interface Callback {
        void onAllPermissionGranted();

        void onPermissionDenial();
    }
}
