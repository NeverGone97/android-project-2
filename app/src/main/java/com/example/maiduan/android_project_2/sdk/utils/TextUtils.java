package com.example.maiduan.android_project_2.sdk.utils;


import com.example.maiduan.android_project_2.sdk.commons.Constants;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.Normalizer;
import java.text.ParseException;
import java.util.regex.Pattern;

/**
 * Created by Vulcl on 5/22/2017
 */

public class TextUtils {

    public static boolean isEmpty(String text) {
        return text == null || text.length() == 0;
    }

    /**
     * Bỏ dấu tiếng việt
     *
     * @param s tiếng Việt có dấu
     * @return tiếng Việt không dấu
     */
    public static String unicodeToKoDau(String s) {
        if (android.text.TextUtils.isEmpty(s)) {
            return "";
        }

        String nfdNormalizedString = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");

        return pattern.matcher(nfdNormalizedString).replaceAll("").replaceAll("\u0111", "d").replaceAll("\u0110", "D");
    }

    public static String unicodeToKoDauLowerCase(String text) {
        if (android.text.TextUtils.isEmpty(text)) {
            return "";
        }

        String nfdNormalizedString = Normalizer.normalize(text.toLowerCase(), Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");

        return pattern.matcher(nfdNormalizedString).replaceAll("").replaceAll("\u0111", "d").replaceAll("\u0110", "D");
    }

    public static String fomatMoney(float value) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator('.');
        symbols.setGroupingSeparator('.');
        DecimalFormat fomat = new DecimalFormat("###,###,###,###", symbols);
        return fomat.format(value);
    }

    public static String fomatMoney(String value) {
        String clearString = value.replaceAll("[\\,,\\.]", "");
        float valueNumber = parserValueMonneyFomat(clearString);
        return fomatMoney(valueNumber);
    }

    public static float parserValueMonneyFomat(String value) {
        DecimalFormat format = new DecimalFormat("###,###,###,###");
        try {
            Number number = format.parse(value);
            return number.floatValue();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static String fomatFloatValue(float value) {
        DecimalFormat format = null;
        if (value == (int) value)
            format = new DecimalFormat("#");
        else
            format = new DecimalFormat("#.#");
        return format.format(value);
    }

    public static String getTypeFile(String filePatch) {
        String[] spilt = filePatch.split("\\.");
        String sss = spilt[spilt.length - 1];
        return "." + spilt[spilt.length - 1];
    }

    /**
     * Lấy giá trị của gender
     * 1 - Nữ
     * 2 - Nam
     * 3 - Free
     * @param gender id giới tính
     * @return Giá trị
     */
    public static String getGender(int gender) {
        switch (gender) {
            case 1:
                return "Nữ";
            case 2:
                return "Nam";
            case 3:
                return "Free";
            default:
                return null;
        }
    }

    public static String getErrorCode(int code) {
        switch (code) {
            case Constants.ERROR_NO_INTERNET:
                return "Không có kết nối mạng";
            case -1:
                return "Có lỗi xảy ra. Vui lòng thử lại";
            case 100:
                return "Dịch vụ không tồn tại";
            case 101:
                return "Dịch vụ chưa kích hoạt";
            case 102:
                return "Email đã tồn tại";
            case 103:
                return "Username đã tồn tại";
            case 105:
                return "Tài khoản này đã kích hoạt";
            case 108:
                return "Số điện thoại chưa đăng kí";
            case 110:
                return "Dịch vụ không hỗi trợ loại thiết bị này";
            case 111:
                return "Tên đăng nhập hoặc mật khẩu không đúng";
            case 112:
                return "Tài khoản chưa được kích hoạt";
            case 118:
                return "Số điện thoại không đúng định dạng";
            default:
                return null;
        }
    }

//    public static static void test(float v) {
//        String formatted = NumberFormat.getCurrencyInstance(new Locale("vi", "VN")).format(v);
//    }
//
////    public static void textFixerUpper(EditText et, String value) {
////        et.removeTextChangedListener(et);
////    }
//
//    public static static void main(String[] agr) {
////        float value = 44434234.444f;
////        if (value == (int) value) {
////            System.out.print("Integer");
////        } else
////            System.out.print("Integer not");
//        test(242342343.5f);
//
//    }
}