package com.example.maiduan.android_project_2.sdk.utils;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

/**
 * Created by Lê Công Long Vũ on 12/26/2016
 */

public class PermissionHelper {

    public static boolean isAllowPermission(String permission, Activity activity) {

        return ActivityCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_GRANTED;
    }

    public static boolean isAllowPermission(String[] permission, Activity activity) {

        for (int i = (permission.length - 1); i >= 0; i--) {
            if (ActivityCompat.checkSelfPermission(activity, permission[i]) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }

        return true;
    }

    public static boolean checkPermission(String[] permission, Activity activity, int REQUEST_CODE) {
        boolean isAllow = true;

        for (int i = (permission.length - 1); i >= 0; i--) {
            if (ActivityCompat.checkSelfPermission(activity, permission[i]) != PackageManager.PERMISSION_GRANTED) {
                isAllow = false;
                break;
            }
        }

        if (!isAllow) {
            boolean isShould = true;
            for (int i = (permission.length - 1); i >= 0; i--) {
                if (!ActivityCompat.shouldShowRequestPermissionRationale(activity, permission[i])) {
                    isShould = false;
                    break;
                }
            }
            // Should we show an explanation?
            if (isShould) {
                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                ActivityCompat.requestPermissions(activity, permission, REQUEST_CODE);
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(activity, permission, REQUEST_CODE);
                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
            return false;
        }

        return true;
    }

    public static boolean checkPermission(String permission, Activity activity, int REQUEST_CODE) {
        if (ActivityCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                ActivityCompat.requestPermissions(activity, new String[]{permission}, REQUEST_CODE);
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(activity, new String[]{permission}, REQUEST_CODE);
                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
            return false;
        }
        return true;
    }

    public static boolean checkResult(int[] grantResults) {

        for (int i = grantResults.length - 1; i >= 0; i--) {
            if (grantResults[i] == PackageManager.PERMISSION_DENIED)
                return false;
        }

        return true;
    }
}