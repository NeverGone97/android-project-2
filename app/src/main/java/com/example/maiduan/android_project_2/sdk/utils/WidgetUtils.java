//package com.example.maiduan.android_project_2.sdk.utils;
//
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.graphics.drawable.BitmapDrawable;
//import android.graphics.drawable.Drawable;
//import android.text.TextUtils;
//import android.util.Log;
//import android.view.View;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.squareup.picasso.Callback;
//import com.squareup.picasso.Picasso;
//import com.xtelsolution.MyApplication;
//
//import java.io.File;
//
///**
// * Created by Vulcl on 1/17/2017
// */
//
//public class WidgetUtils {
//    /**
//     * Image
//     */
//    public static void setImageURL(ImageView view, final String url, int resource) {
//        if (TextUtils.isEmpty(url)) {
//            view.setImageResource(resource);
//            return;
//        }
//
//        Picasso.with(MyApplication.context)
//                .load(url)
//                .noPlaceholder()
//                .error(resource)
//                .into(view, new Callback() {
//                    @Override
//                    public void onSuccess() {
//                        Picasso.with(MyApplication.context).invalidate(url);
//                    }
//
//                    @Override
//                    public void onError() {
//                        Log.e("WidgetUtils", "load error " + url);
//                    }
//                });
//    }
//
//    public static void setImageURL(ImageView view, String url, int resource, Callback callback) {
//        if (TextUtils.isEmpty(url)) {
//            view.setImageResource(resource);
//            return;
//        }
//
//        Picasso.with(MyApplication.context)
//                .load(url)
//                .noPlaceholder()
//                .error(resource)
//                .into(view, callback);
//    }
//
//    public static void setSmallImageURL(ImageView view, final String url, int resource) {
//        if (TextUtils.isEmpty(url)) {
//            view.setImageResource(resource);
//            return;
//        }
//
//        Picasso.with(MyApplication.context)
//                .load(url)
//                .noPlaceholder()
//                .error(resource)
//                .fit()
//                .centerCrop()
//                .into(view, new Callback() {
//                    @Override
//                    public void onSuccess() {
//                        Picasso.with(MyApplication.context).invalidate(url);
//                    }
//
//                    @Override
//                    public void onError() {
//                        Picasso.with(MyApplication.context).invalidate(url);
//                    }
//                });
//    }
//
//    public static void setSmallImageURL(ImageView view, final String url, int resource, Callback callback) {
//        if (TextUtils.isEmpty(url)) {
//            view.setImageResource(resource);
//            return;
//        }
//
//        Picasso.with(MyApplication.context)
//                .load(url)
//                .noPlaceholder()
//                .error(resource)
//                .fit()
//                .centerCrop()
//                .into(view, callback);
//    }
//
//    public static void setImageFile(ImageView view, final File file, int resource) {
//        if (file == null) {
//            view.setImageResource(resource);
//            return;
//        }
//
//        Picasso.with(MyApplication.context)
//                .load(file)
//                .noPlaceholder()
//                .error(resource)
//                .into(view, new Callback() {
//                    @Override
//                    public void onSuccess() {
//                        Picasso.with(MyApplication.context).invalidate(file);
//                    }
//
//                    @Override
//                    public void onError() {
//                    }
//                });
//    }
//
//    public static void setImageFileAndDelete(ImageView view, final File file, int resource) {
//        if (file == null) {
//            view.setImageResource(resource);
//            return;
//        }
//
//        Picasso.with(MyApplication.context)
//                .load(file)
//                .noPlaceholder()
//                .error(resource)
//                .into(view, new Callback() {
//                    @Override
//                    public void onSuccess() {
//                        Picasso.with(MyApplication.context).invalidate(file);
//                        deleteFile(file);
//                    }
//
//                    @Override
//                    public void onError() {
//                        deleteFile(file);
//                    }
//                });
//    }
//
//    public static void setSmallImageFile(ImageView view, final File file, int resource) {
//        if (file == null) {
//            view.setImageResource(resource);
//            return;
//        }
//
//        Picasso.with(MyApplication.context)
//                .load(file)
//                .noPlaceholder()
//                .error(resource)
//                .fit()
//                .centerCrop()
//                .into(view, new Callback() {
//                    @Override
//                    public void onSuccess() {
//                        Picasso.with(MyApplication.context).invalidate(file);
//                    }
//
//                    @Override
//                    public void onError() {
//                        Picasso.with(MyApplication.context).invalidate(file);
//                    }
//                });
//    }
//
//    public static void setSmallImageFileAndDelete(ImageView view, final File file, int resource) {
//        if (file == null) {
//            view.setImageResource(resource);
//            return;
//        }
//
//        Picasso.with(MyApplication.context)
//                .load(file)
//                .noPlaceholder()
//                .error(resource)
//                .fit()
//                .centerCrop()
//                .into(view, new Callback() {
//                    @Override
//                    public void onSuccess() {
//                        Picasso.with(MyApplication.context).invalidate(file);
//                        deleteFile(file);
//                    }
//
//                    @Override
//                    public void onError() {
//                        Picasso.with(MyApplication.context).invalidate(file);
//                        deleteFile(file);
//                    }
//                });
//    }
//
//    public static void setImageFileBlur(ImageView view, File file, int resource) {
//        Bitmap bitmap;
//
//        if (file != null && file.exists()) {
//            bitmap = BitmapFactory.decodeFile(file.getPath());
//        } else {
//            bitmap = BitmapFactory.decodeResource(MyApplication.context.getResources(), resource);
//        }
//
//        view.setImageBitmap(BlurUtils.blur(MyApplication.context, bitmap));
//    }
//
//    public static void setImageFileUrl(ImageView view, ImageView view2, int resource) {
//        Bitmap bitmap;
//
//        try {
//            bitmap = ((BitmapDrawable) view2.getDrawable()).getBitmap();
//        } catch (Exception e) {
//            bitmap = BitmapFactory.decodeResource(MyApplication.context.getResources(), resource);
//        }
//
//        view.setImageBitmap(BlurUtils.blur(MyApplication.context, bitmap));
//    }
//    /**
//     * End Image
//     * */
//
//
//    /**
//     * Edittext
//     */
//    public static void setText(EditText editText, int content) {
//        editText.setText(String.valueOf(content));
//    }
//
//    public static void setText(EditText editText, String title, int content) {
//        editText.setText((title + content));
//    }
//
//    public static void setText(EditText editText, String content) {
//        editText.setText(content);
//    }
//
//    public static void setText(EditText editText, String title, String content) {
//        editText.setText((title + content));
//    }
//
//
//    public static void setTextWithResult(EditText editText, String content, String result) {
//        if (TextUtils.isEmpty(content))
//            editText.setHint(result);
//        else
//            editText.setText(content);
//    }
//
//    public static void setTextWithResult(EditText editText, String title, String content, String result) {
//        if (TextUtils.isEmpty(content))
//            editText.setHint(result);
//        else
//            editText.setText((title + content));
//    }
//
//
//    public static void setDate(EditText editText, long milliseconds) {
//        editText.setText(TimeUtils.convertLongToDate(milliseconds));
//    }
//
//    public static void setDate(EditText editText, String title, long milliseconds) {
//        editText.setText((title + TimeUtils.convertLongToDate(milliseconds)));
//    }
//
//    public static void setDate(EditText editText, int day, int month, int year) {
//        String mDay = (day < 10) ? ("0" + day) : String.valueOf(day);
//        String mMonth = (month < 10) ? ("0" + month) : String.valueOf(month);
//
//        editText.setText(mDay + "/" + mMonth + "/" + year);
//    }
//
//    public static void setDateWithResult(EditText editText, Long milliseconds, String result) {
//        if (milliseconds == null)
//            editText.setHint(result);
//        else
//            editText.setText(TimeUtils.convertLongToDate(milliseconds));
//    }
//
//    public static void setDateWithResult(EditText editText, String title, Long milliseconds) {
//        editText.setText((title + TimeUtils.convertLongToDate(milliseconds)));
//    }
//
//    public static void setDateWithResult(EditText editText, String title, int day, int month, int year) {
//        String mDay = (day < 10) ? ("0" + day) : String.valueOf(day);
//        String mMonth = (month < 10) ? ("0" + month) : String.valueOf(month);
//
//        editText.setText((title + mDay + "/" + mMonth + "/" + year));
//    }
//
//    public static void setDateWithResult(EditText editText, String title, Long milliseconds, String result) {
//        if (milliseconds == null)
//            editText.setHint(result);
//        else
//            editText.setText((title + TimeUtils.convertLongToDate(milliseconds)));
//    }
//
//
//    public static void setTime(EditText editText, int mHour, int mMinute) {
//        String hour = (mHour < 10) ? ("0" + mHour) : String.valueOf(mHour);
//        String minute = (mMinute < 10) ? ("0" + mMinute) : String.valueOf(mMinute);
//
//        editText.setText(hour + ":" + minute);
//    }
//
//    public static void setTime(EditText editText, Long milisecond) {
//        editText.setText(TimeUtils.convertLongToTime(milisecond));
//    }
//
//    public static void setTimeWithResult(EditText editText, Long milisecond, String result) {
//        if (milisecond == null)
//            editText.setHint(result);
//        else
//            editText.setText(TimeUtils.convertLongToTime(milisecond));
//    }
//
//    public static void setTimeWithResult(EditText editText, String title, int mHour, int mMinute) {
//        String hour = (mHour < 10) ? ("0" + mHour) : String.valueOf(mHour);
//        String minute = (mMinute < 10) ? ("0" + mMinute) : String.valueOf(mMinute);
//
//        editText.setText(title + hour + ":" + minute);
//    }
//
//    public static void setTimeWithResult(EditText editText, String title, Long milisecond) {
//        editText.setText((title + TimeUtils.convertLongToTime(milisecond)));
//    }
//
//    public static void setEditTextDrawable(EditText editText, int position, int resource) {
//        switch (position) {
//            case 0:
//                editText.setCompoundDrawablesWithIntrinsicBounds(resource, 0, 0, 0);
//                break;
//            case 1:
//                editText.setCompoundDrawablesWithIntrinsicBounds(0, resource, 0, 0);
//                break;
//            case 2:
//                editText.setCompoundDrawablesWithIntrinsicBounds(0, 0, resource, 0);
//                break;
//            case 3:
//                editText.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, resource);
//                break;
//            default:
//                break;
//        }
//    }
//
//    public static void setEditTextDrawable(EditText editText, int position, Drawable resource) {
//        switch (position) {
//            case 0:
//                editText.setCompoundDrawablesWithIntrinsicBounds(resource, null, null, null);
//                break;
//            case 1:
//                editText.setCompoundDrawablesWithIntrinsicBounds(null, resource, null, null);
//                break;
//            case 2:
//                editText.setCompoundDrawablesWithIntrinsicBounds(null, null, resource, null);
//                break;
//            case 3:
//                editText.setCompoundDrawablesWithIntrinsicBounds(null, null, null, resource);
//                break;
//            default:
//                break;
//        }
//    }
//    /**
//     * End Edittext
//     * */
//
//
//    /**
//     * TextView
//     */
//    public static void setText(TextView view, int content) {
//        view.setText(String.valueOf(content));
//    }
//
//    public static void setText(TextView view, String content) {
//        view.setText(content);
//    }
//
//    public static void setText(TextView view, String title, String content) {
//        view.setText((title + content));
//    }
//
//    public static void setTextWithResult(TextView view, String content, String result) {
//        if (TextUtils.isEmpty(content))
//            view.setText(result);
//        else
//            view.setText(content);
//    }
//
//    public static void setTextWithResult(TextView view, String title, String content, String result) {
//        if (TextUtils.isEmpty(content))
//            view.setText((title + result));
//        else
//            view.setText((title + content));
//    }
//
//    public static void setDate(TextView view, String title, Long milliseconds) {
//        view.setText((title + TimeUtils.convertLongToDate(milliseconds)));
//    }
//
//    public static void setDate(TextView view, String title, Long milliseconds, String result) {
//        if (milliseconds == null)
//            view.setText(result);
//        else
//            view.setText((title + TimeUtils.convertLongToDate(milliseconds)));
//    }
//
//    public static void setTime(TextView view, Long milliseconds) {
//        view.setText(TimeUtils.convertLongToTime(milliseconds));
//    }
//
//    public static void setTime(TextView view, Long milliseconds, String result) {
//        if (milliseconds == null)
//            view.setText(result);
//        else
//            view.setText(TimeUtils.convertLongToTime(milliseconds));
//    }
//
//    public static void setDateTime(TextView view, Long milliseconds) {
//        view.setText(TimeUtils.convertLongToDateTime(milliseconds));
//    }
//
//    public static void setDateTime(TextView view, Long milliseconds, String result) {
//        if (milliseconds == null)
//            view.setText(result);
//        else
//            view.setText(TimeUtils.convertLongToDateTime(milliseconds));
//    }
//
//    public static void setTextViewDrawable(TextView view, int position, int resource) {
//        switch (position) {
//            case 0:
//                view.setCompoundDrawablesWithIntrinsicBounds(resource, 0, 0, 0);
//                break;
//            case 1:
//                view.setCompoundDrawablesWithIntrinsicBounds(0, resource, 0, 0);
//                break;
//            case 2:
//                view.setCompoundDrawablesWithIntrinsicBounds(0, 0, resource, 0);
//                break;
//            case 3:
//                view.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, resource);
//                break;
//            default:
//                break;
//        }
//    }
//
//    public static void setTextViewDrawable(TextView view, int position, Drawable resource) {
//        switch (position) {
//            case 0:
//                view.setCompoundDrawablesWithIntrinsicBounds(resource, null, null, null);
//                break;
//            case 1:
//                view.setCompoundDrawablesWithIntrinsicBounds(null, resource, null, null);
//                break;
//            case 2:
//                view.setCompoundDrawablesWithIntrinsicBounds(null, null, resource, null);
//                break;
//            case 3:
//                view.setCompoundDrawablesWithIntrinsicBounds(null, null, null, resource);
//                break;
//            default:
//                break;
//        }
//    }
//
//    /**
//     * End TextView
//     */
//
//
//    public static void setBackgroundResource(View view, int resource) {
//        view.setBackgroundResource(resource);
//    }
//
//    public static void setBackgroundColor(View view, int resource) {
//        view.setBackgroundColor(resource);
//    }
//
//    public static void setBackgroundDrawable(View view, int resource) {
//        //noinspection deprecation
//        view.setBackgroundDrawable(MyApplication.context.getResources().getDrawable(resource));
//    }
//
//
//    private static boolean deleteFile(File file) {
//        try {
//            return file.delete();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            return false;
//        }
//    }
//}