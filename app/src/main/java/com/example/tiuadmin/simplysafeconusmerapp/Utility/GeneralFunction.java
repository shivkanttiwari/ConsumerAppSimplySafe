package com.example.tiuadmin.simplysafeconusmerapp.Utility;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tiuadmin.simplysafeconusmerapp.R;
import com.example.tiuadmin.simplysafeconusmerapp.Webservices.WebService;

import org.json.JSONObject;

/**
 * Created by tiuadmin on 14/12/16.
 */

public class GeneralFunction {
    public static ProgressDialog pDialog;


PrefManager prefManager;
    // Custom Toast Method
    public void Show_Toast(Context context, View view, String error) {

        // Layout Inflater for inflating custom view
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // inflate the layout over view
        View layout = inflater.inflate(R.layout.custom_toast,
                (ViewGroup) view.findViewById(R.id.toast_root));

        // Get TextView id and set error
        TextView text = (TextView) layout.findViewById(R.id.toast_error);
        text.setText(error);

        Toast toast = new Toast(context);// Get Toast Context
        toast.setGravity(Gravity.TOP | Gravity.FILL_HORIZONTAL, 0, 0);// Set
        // Toast
        // gravity
        // and
        // Fill
        // Horizoontal

        toast.setDuration(Toast.LENGTH_SHORT);// Set Duration
        toast.setView(layout); // Set Custom View over toast

        toast.show();// Finally show toast
    }

    public AlertDialog AskOption(Activity context) {
        AlertDialog myQuittingDialogBox = new AlertDialog.Builder(context)
                //set message, title, and icon
                .setTitle("Delete")
                .setMessage("Do you want to Delete this merchant?")
                .setIcon(R.drawable.cross_delete)

                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        //your deleting code
                        dialog.dismiss();
                    }

                })


                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();

                    }
                })
                .create();
        return myQuittingDialogBox;

    }

    public void showProgressDialog(Activity context) {
        pDialog = new ProgressDialog(context);
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);
        if (!pDialog.isShowing())
            pDialog.show();
    }

    public void hideProgressDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    /**
     * Making json object request
     */
    public void sendRegistrationToServer(Context context,String token) {
        // new GeneralFunction().showProgressDialog(getActivity());
        prefManager=new PrefManager(context);

        String res = null;
        String responseCode = null;
        String returnResponse = null;
        try {

            String url ="http://52.66.101.233/Customer-Backend/public/api/v1/customer/device/register";
            JSONObject jsonrequest = new JSONObject();
            jsonrequest.put("device_token", token);
            jsonrequest.put("device_type_id", "");


            WebService web = new WebService();
            res = web.postWithHeader(url, jsonrequest.toString(),prefManager.getToken());
            Log.d(res, res);

            if (res != null && res.length() > 0) {
                JSONObject json = new JSONObject(res);
                if (json != null) {

                   String status = json.getString("status");
                    String message = json.getString("message");
                    if (status.equalsIgnoreCase("true")) {
                        JSONObject userdata = json.getJSONObject("data");

                        //Const.SIGNUP_TOKEN=userdata.getString("otp");


                        //Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();


                    } else {
                        // Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                    }


                    // new GeneralFunction().hideProgressDialog();
                }
            } else {
                //Toast.makeText(getApplicationContext(), "Unable to update device token.", Toast.LENGTH_SHORT).show();
                // new GeneralFunction().hideProgressDialog();
            }

        } catch (Exception e) {
            // Toast.makeText(getApplicationContext(), "Unable to update device token", Toast.LENGTH_SHORT).show();
            //new GeneralFunction().hideProgressDialog();
            e.printStackTrace();
        }
    }


}


