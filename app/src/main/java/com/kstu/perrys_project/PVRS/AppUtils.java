package com.kstu.perrys_project.PVRS;


import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

/**
 * Created by femicodes on 1/23/2018.
 */

public class AppUtils {
    private static ProgressDialog progressDialog;

    public static void showProgressDialog(Context context) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Searching..."); // Setting Message
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner
        progressDialog.show(); // Display Progress Dialog
        progressDialog.setCancelable(false);
    }

    public static void dismissProgressDialog(Context context) {
        progressDialog.dismiss();
    }

    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }


}
