package com.example.maiduan.android_project_2.sdk.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Author: Lê Công Long Vũ
 * Date: 10/6/2017
 * Email: leconglongvu@gmail.com
 */
public class SizeUtils {

    /**
     * Lấy kích thước của màn hình
     * @param activity ngữ cảnh
     * @return kết quả
     */
    public static Point getDisplaySize(Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        final Point size = new Point();
        display.getSize(size);
        return size;
    }

    /**
     * Lấy vị trí x, y, width, height của TextView
     * @param textView view muốn lấy
     * @return kết quả
     */
    public static Rect getTextViewBounds(TextView textView) {
        Rect rect = new Rect();
        textView.getPaint().getTextBounds(textView.getText().toString(), 0, textView.getText().length(), rect);
        return rect;
    }

    /**
     * Lấy vị trí x, y, width, height của ImageView
     * @param imageView view muốn lấy
     * @return kết quả
     */
    public static Rect getImageViewBounds(ImageView imageView) {
        return imageView.getDrawable().getBounds();
    }


    /**
     * Chuyển dip sang pixels
     * @param context ngữ cảnh
     * @param dp value muốn chuyển
     * @return kết quả pixels
     */
    public static float dpToPx(Context context, int dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    /**
     * Chuyển pixels sang dip
     * @param context ngữ cảnh
     * @param px value muốn chuyển
     * @return kết quả dip
     */
    public static float pxToDp(Context context, int px) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

//    public static float dpToPx(Context context, float dpValue) {
//        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
//        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, displayMetrics);
//    }

    /**
     * Chuyển sp sang pixels
     * @param context ngữ cảnh
     * @param spValue value muốn chuyển
     * @return kết quả pixels
     */
    public static float spToPx(Context context, float spValue) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spValue, displayMetrics);
    }
}