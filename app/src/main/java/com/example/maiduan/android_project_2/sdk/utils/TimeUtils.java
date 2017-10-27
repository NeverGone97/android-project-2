//package com.example.maiduan.android_project_2.sdk.utils;
//
//import android.app.DatePickerDialog;
//import android.app.TimePickerDialog;
//import android.content.Context;
//import android.widget.DatePicker;
//import android.widget.TimePicker;
//
//import com.example.maiduan.android_project_2.R;
//import com.example.maiduan.android_project_2.sdk.callback.DatePickerListener;
//import com.example.maiduan.android_project_2.sdk.callback.TimePickerListener;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.Locale;
//import java.util.Random;
//
///**
// * Created by Vulcl on 3/29/2017
// */
//
//public class TimeUtils {
//
//    public static int randInt(int min, int max) {
//        // Usually this can be a field rather than a method variable
//        Random rand = new Random();
//
//        // nextInt is normally exclusive of the top value,
//        // so add 1 to make it inclusive
//
//        return rand.nextInt((max - min) + 1) + min;
//    }
//
//    public static String getCreteTime() {
//        SimpleDateFormat sdf = new SimpleDateFormat("HH_mm_ss_d_MM_yyyy", Locale.getDefault());
//        Date mDate = new Date();
//
//        return sdf.format(mDate);
//    }
//
//    public static long convertDateToLong(String date) {
//        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
//
//        try {
//            Date mDate = sdf.parse(date);
//            return mDate.getTime();
//        } catch (ParseException e) {
//            return 0;
//        }
//    }
//
//    public static long convertTimeToLong(String time) {
//        String[] mTime = time.split(":");
//
//        int hour = Integer.parseInt(mTime[0]);
//        int minute = Integer.parseInt(mTime[1]);
//
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(2017, 1, 1, hour, minute);
//
//        return 0;
//    }
//
//    public static long convertTimeToLong(int hour, int minute) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(2017, 1, 1, hour, minute);
//
//        return (calendar.getTimeInMillis());
//    }
//
//    public static String convertLongToDate(Long milisecond) {
//        if (milisecond == null)
//            return null;
//
//        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
//
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeInMillis(milisecond);
//
//        try {
//            return sdf.format(new Date(calendar.getTimeInMillis()));
//        } catch (Exception e) {
//            return null;
//        }
//    }
//
//    public static String convertLongToTime(Long milliseconds) {
//        if (milliseconds == null)
//            return null;
//
//        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());
//
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeInMillis(milliseconds);
//
//        try {
//            return sdf.format(new Date(calendar.getTimeInMillis()));
//        } catch (Exception e) {
//            return null;
//        }
//    }
//
//    public static String convertLongToDateTime(Long milliseconds) {
//        if (milliseconds == null)
//            return null;
//
//        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.getDefault());
//
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeInMillis(milliseconds);
//
//        try {
//            return sdf.format(new Date(calendar.getTimeInMillis()));
//        } catch (Exception e) {
//            return null;
//        }
//    }
//
//    public static String getToday() {
//        Calendar calendar = Calendar.getInstance();
//        return (calendar.get(Calendar.DAY_OF_MONTH) + "/" + (calendar.get(Calendar.MONTH) + 1) + "/" + calendar.get(Calendar.YEAR));
//    }
//
//    public static String getYesterday() {
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeInMillis((System.currentTimeMillis() - 86400000));
//        return (calendar.get(Calendar.DAY_OF_MONTH) + "/" + (calendar.get(Calendar.MONTH) + 1) + "/" + calendar.get(Calendar.YEAR));
//    }
//
//    public static void timePicker(Context context, Long time, final TimePickerListener timePickerListener) {
//        final Calendar calendar = Calendar.getInstance();
//
//        if (time != null && time > 0)
//            calendar.setTimeInMillis(time);
//
//        TimePickerDialog dialog = new TimePickerDialog(context, R.style.PickerTheme, new TimePickerDialog.OnTimeSetListener() {
//            @Override
//            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//                String time = hourOfDay > 9 ? String.valueOf(hourOfDay) : "0" + hourOfDay;
//                time += ":" + (minute > 9 ? String.valueOf(minute) : "0" + minute);
//
//                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
//                calendar.set(Calendar.MINUTE, minute);
//                calendar.set(Calendar.SECOND, 0);
//                calendar.set(Calendar.MILLISECOND, 0);
//
//                if (view.isShown())
//                    timePickerListener.onTimeSet(time, calendar.getTimeInMillis());
//            }
//        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
//
////        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, context.getString(R.string.layout_cancel), new DialogInterface.OnClickListener() {
////            @Override
////            public void onClick(DialogInterface dialog, int which) {
////                dialog.dismiss();
////            }
////        });
////        dialog.setButton(DialogInterface.BUTTON_POSITIVE, context.getString(R.string.layout_choose), dialog);
//
//        dialog.show();
//    }
//
//    public static void datePicker(Context context, String Date, final DatePickerListener datePickerListener) {
//        final Calendar calendar = Calendar.getInstance();
//
//        long date = convertDateToLong(Date) > 0 ? convertDateToLong(Date) : System.currentTimeMillis();
//        calendar.setTimeInMillis(date);
//
//        final DatePickerDialog dialog = new DatePickerDialog(context, R.style.PickerTheme, new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                String time = dayOfMonth > 9 ? String.valueOf(dayOfMonth) : "0" + dayOfMonth;
//                time += "-" + (month > 8 ? String.valueOf((month + 1)) : "0" + (month + 1));
//                time += "-" + year;
//
//                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
//                calendar.set(Calendar.MONTH, month);
//                calendar.set(Calendar.YEAR, year);
//
//                if (view.isShown())
//                    datePickerListener.onTimeSet(time, calendar.getTimeInMillis());
//            }
//        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
//
////        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, context.getString(R.string.layout_cancel), new DialogInterface.OnClickListener() {
////            @Override
////            public void onClick(DialogInterface dialog, int which) {
////                dialog.dismiss();
////            }
////        });
////        dialog.setButton(DialogInterface.BUTTON_POSITIVE, context.getString(R.string.layout_choose), dialog);
//
//        dialog.show();
//    }
//}