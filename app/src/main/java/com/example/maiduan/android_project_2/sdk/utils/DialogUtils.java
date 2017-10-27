//package com.example.maiduan.android_project_2.sdk.utils;
//
//import android.content.Context;
//import android.support.design.widget.BottomSheetDialog;
//import android.view.View;
//import android.widget.Button;
//import android.widget.LinearLayout;
//
//import com.xtelsolution.sdk.callback.HealthRecordOptionListener;
//import com.xtelsolution.xmec.R;
//
///**
// * Author: Lê Công Long Vũ
// * Date: 10/23/2017
// * Email: leconglongvu@gmail.com
// */
//public class DialogUtils {
//
//    public static void showHealthRecordOption(Context context, final HealthRecordOptionListener listener) {
//        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context, R.style.Theme_Transparent);
//       bottomSheetDialog.setContentView(R.layout.dialog_hr_option);
//        //noinspection ConstantConditions
//        bottomSheetDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
////       bottomSheetDialog.setCancelable(true);
////       bottomSheetDialog.setCanceledOnTouchOutside(true);
//
//        Button btnEndOfTreatment = (Button) bottomSheetDialog.findViewById(R.id.btn_end_of_treatment);
//        Button btnEdit = (Button) bottomSheetDialog.findViewById(R.id.btn_edit);
//        Button btnDelete = (Button) bottomSheetDialog.findViewById(R.id.btn_delete);
//
//        assert btnEndOfTreatment != null;
//        btnEndOfTreatment.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                bottomSheetDialog.dismiss();
//                listener.onEndOfTreatment();
//            }
//        });
//
//        assert btnEdit != null;
//        btnEdit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                bottomSheetDialog.dismiss();
//                listener.onEdit();
//            }
//        });
//
//        assert btnDelete != null;
//        btnDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                bottomSheetDialog.dismiss();
//                listener.onDelete();
//            }
//        });
//
//        bottomSheetDialog.show();
//    }
//}