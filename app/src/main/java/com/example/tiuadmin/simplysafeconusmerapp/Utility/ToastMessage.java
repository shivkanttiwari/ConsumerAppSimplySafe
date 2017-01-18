package com.example.tiuadmin.simplysafeconusmerapp.Utility;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ToastMessage {

    public static void toastMsgShot(Context context, String msg) {

        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

    }

    public static void toastMsgLong(Context context, String msg) {

        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();

    }

    public static void toastMsgLongPassMin6(Context context) {

        Toast.makeText(context, "Password should be minimum of 6 digits.", Toast.LENGTH_LONG).show();

    }

    public static void toastMsgNoInternet(Context context) {

        Toast.makeText(context, "No Internet Connection", Toast.LENGTH_LONG)
                .show();

    }

    public static void toastMsgFailed(Context context) {

        Toast.makeText(context, "Failed! try again", Toast.LENGTH_LONG).show();

    }

    public static void snackBarMsgIndefinite(View mLayout, String msg) {

        final Snackbar snackbar = Snackbar.make(mLayout, msg, Snackbar.LENGTH_INDEFINITE);

        View snackbarView = snackbar.getView();
        TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setMaxLines(5);

        snackbar.setAction("OK", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        snackbar.show();

    }

    public static void snackBarMsgIndefinite(Activity activity, String msg) {

        View mLayout = activity.findViewById(android.R.id.content);

        final Snackbar snackbar = Snackbar.make(mLayout, msg, Snackbar.LENGTH_INDEFINITE);

        View snackbarView = snackbar.getView();
        TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setMaxLines(5);

        snackbar.setAction("OK", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        snackbar.show();

    }

    public static void snackBarMsg(Activity activity, String msg) {

        View mLayout = activity.findViewById(android.R.id.content);

        final Snackbar snackbar = Snackbar.make(mLayout, msg, Snackbar.LENGTH_LONG);

        View snackbarView = snackbar.getView();
        TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setMaxLines(5);

        snackbar.show();

    }

    public static void snackBarMsgIndefiniteNeedLogin(Activity activity) {

        View mLayout = activity.findViewById(android.R.id.content);

        final Snackbar snackbar = Snackbar.make(mLayout, "You need to login to view this.", Snackbar.LENGTH_INDEFINITE);

        View snackbarView = snackbar.getView();
        TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setMaxLines(5);

        snackbar.setAction("OK", new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        snackbar.show();

    }

    public static void toastMsgLongEnterCityName(Context context) {
        Toast.makeText(context, "Please provide city name", Toast.LENGTH_LONG).show();


    }
    public static void toastMsgLongEnterValidEmail(Context context) {
        Toast.makeText(context, "Enter Valid Email or leave Empty", Toast.LENGTH_LONG).show();


    }

}
