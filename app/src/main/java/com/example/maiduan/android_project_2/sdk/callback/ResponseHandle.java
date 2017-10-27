//package com.example.maiduan.android_project_2.sdk.callback;
//
//import android.util.Log;
//
//import com.example.maiduan.android_project_2.sdk.commons.Constants;
//import com.example.maiduan.android_project_2.sdk.utils.JsonHelper;
//
///**
// * Created by Lê Công Long Vũ on 12/4/2016
// */
//
//public abstract class ResponseHandle<T extends RESP_Basic> {
//    private Class<T> clazz;
//
//    protected ResponseHandle(Class<T> clazz) {
//        this.clazz = clazz;
//    }
//
//    public void onSuccess(String result) {
//        Log.e("ResponseHandle", "result: " + result);
//
//        try {
//
//            if (TextUtils.isEmpty(result)) {
//                onSuccess((T) null);
//            } else {
//                T t = JsonHelper.getObjectNoException(result, clazz);
//
//                if (t.getError() != null) {
//                    onError(t.getError());
//                } else {
//                    onSuccess(t);
//                }
//            }
//
//        } catch (Exception e) {
//            Log.e("ResponseHandle", "error: " + e);
//            onError(new Error(Constants.ERROR_UNKOW, "ERROR_PARSER_RESPONSE", "ERROR"));
//        }
//    }
//
//    public void onError(Exception error) {
//        onError(new Error(Constants.ERROR_UNKOW, "ERROR_REQUEST_RESPONSE", error.getMessage()));
//    }
//
//    protected abstract void onSuccess(T obj);
//
//    protected abstract void onError(Error error);
//}
