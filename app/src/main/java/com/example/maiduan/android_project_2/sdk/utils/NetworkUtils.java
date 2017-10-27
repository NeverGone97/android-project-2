//package com.example.maiduan.android_project_2.sdk.utils;
//
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.location.LocationManager;
//import android.net.ConnectivityManager;
//import android.net.NetworkInfo;
//import android.provider.Settings;
//import android.support.v7.app.AlertDialog;
//import android.telephony.TelephonyManager;
//
//import com.example.maiduan.android_project_2.R;
//
//
///**
// * Created by Vũ Hà Vi on 11/4/2016
// */
//public class NetworkUtils {
//
//    /**
//     * Get the network info
//     */
//    private static NetworkInfo getNetworkInfo(Context context){
//        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//        return cm.getActiveNetworkInfo();
//    }
//
//    /**
//     * Check if there is any connectivity
//     */
//    public static boolean isConnected(Context context){
//        NetworkInfo info = getNetworkInfo(context);
//        return (info != null && info.isConnectedOrConnecting());
//    }
//
//    /**
//     * Check if there is any connectivity to a Wifi network
//     */
//    public  static boolean isConnectedWifi(Context context){
//        NetworkInfo info = getNetworkInfo(context);
//        return (info != null && info.isConnected() && info.getType() == ConnectivityManager.TYPE_WIFI);
//    }
//
//    /**
//     * Check if there is any connectivity to a mobile network
//     */
//    public  static boolean isConnectedMobile(Context context){
//        NetworkInfo info = getNetworkInfo(context);
//        return (info != null && info.isConnected() && info.getType() == ConnectivityManager.TYPE_MOBILE);
//    }
//
//    /**
//     * Check if there is fast connectivity
//     */
//    public  static boolean isConnectedFast(Context context){
//        NetworkInfo info = getNetworkInfo(context);
//        return (info != null && info.isConnected() && isConnectionFast(info.getType(),info.getSubtype()));
//    }
//
//    /**
//     * Check if the connection is fast
//     */
//    public  static boolean isConnectionFast(int type, int subType){
//        if(type==ConnectivityManager.TYPE_WIFI){
//            return true;
//        }else if(type==ConnectivityManager.TYPE_MOBILE){
//            switch(subType){
//                case TelephonyManager.NETWORK_TYPE_1xRTT:
//                    return false; // ~ 50-100 kbps
//                case TelephonyManager.NETWORK_TYPE_CDMA:
//                    return false; // ~ 14-64 kbps
//                case TelephonyManager.NETWORK_TYPE_EDGE:
//                    return false; // ~ 50-100 kbps
//                case TelephonyManager.NETWORK_TYPE_EVDO_0:
//                    return true; // ~ 400-1000 kbps
//                case TelephonyManager.NETWORK_TYPE_EVDO_A:
//                    return true; // ~ 600-1400 kbps
//                case TelephonyManager.NETWORK_TYPE_GPRS:
//                    return false; // ~ 100 kbps
//                case TelephonyManager.NETWORK_TYPE_HSDPA:
//                    return true; // ~ 2-14 Mbps
//                case TelephonyManager.NETWORK_TYPE_HSPA:
//                    return true; // ~ 700-1700 kbps
//                case TelephonyManager.NETWORK_TYPE_HSUPA:
//                    return true; // ~ 1-23 Mbps
//                case TelephonyManager.NETWORK_TYPE_UMTS:
//                    return true; // ~ 400-7000 kbps
//			/*
//			 * Above API level 7, make sure to set android:targetSdkVersion
//			 * to appropriate level to use these
//			 */
//                case TelephonyManager.NETWORK_TYPE_EHRPD: // API level 11
//                    return true; // ~ 1-2 Mbps
//                case TelephonyManager.NETWORK_TYPE_EVDO_B: // API level 9
//                    return true; // ~ 5 Mbps
//                case TelephonyManager.NETWORK_TYPE_HSPAP: // API level 13
//                    return true; // ~ 10-20 Mbps
//                case TelephonyManager.NETWORK_TYPE_IDEN: // API level 8
//                    return false; // ~25 kbps
//                case TelephonyManager.NETWORK_TYPE_LTE: // API level 11
//                    return true; // ~ 10+ Mbps
//                // Unknown
//                case TelephonyManager.NETWORK_TYPE_UNKNOWN:
//                default:
//                    return false;
//            }
//        }else{
//            return false;
//        }
//    }
//
//    public  static boolean checkGPS(final Context context, String message) {
//        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
//        boolean gps_enabled = false;
//        boolean network_enabled = false;
//
//        try {
//            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//
//        try {
//            network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//
//        if (!gps_enabled && !network_enabled) {
//            // notify user
//            AlertDialog.Builder dialog = new AlertDialog.Builder(context, R.style.AppCompatAlertDialogStyle);
//            dialog.setMessage(message);
//            dialog.setPositiveButton(context.getString(R.string.setting), new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
//                    // TODO Auto-generated method stub
//                    Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//                    context.startActivity(myIntent);
//                    //get gps
//                }
//            });
//            dialog.setNegativeButton(context.getString(R.string.cancel), new DialogInterface.OnClickListener() {
//
//                @Override
//                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
//                    // TODO Auto-generated method stub
//
//                }
//            });
//            dialog.show();
//            return false;
//        }
//
//        return true;
//    }
//}