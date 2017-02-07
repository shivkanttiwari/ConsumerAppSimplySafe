package com.example.tiuadmin.simplysafeconusmerapp.PushNotification;


import android.util.Log;

import com.example.tiuadmin.simplysafeconusmerapp.Utility.Const;
import com.example.tiuadmin.simplysafeconusmerapp.Utility.GeneralFunction;
import com.example.tiuadmin.simplysafeconusmerapp.Utility.PrefManager;
import com.example.tiuadmin.simplysafeconusmerapp.Webservices.WebService;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import org.json.JSONObject;

/**
 * Created by Belal on 5/27/2016.
 */


public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    private static final String TAG = "MyFirebaseIIDService";
    public static final String REGISTRATION_SUCCESS = "RegistrationSuccess";

    private String status;
    PrefManager prefManager;

    @Override
    public void onTokenRefresh() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);

        Const.DEVICE_TOKEN=refreshedToken;
        prefManager = new PrefManager(getApplicationContext());

        prefManager.setDeviceToken(refreshedToken);

        if(refreshedToken.length()>0 && prefManager.getToken().length()>0)
        {
            new GeneralFunction().sendRegistrationToServer(getApplicationContext());
        }


    }




    /**
     * Making json object request
     */
    private void sendRegistrationToServer(String refreshedToken) {
        // new GeneralFunction().showProgressDialog(getActivity());


        String res = null;
        String responseCode = null;
        String returnResponse = null;
        try {

            String url = "http://52.66.101.233/Customer-Backend/public/api/v1/customer/device/register";
            JSONObject jsonrequest = new JSONObject();
            jsonrequest.put("device_token", "afkhlsdfkjhldsfjlhkaslhjk");
            jsonrequest.put("device_type_id", "");


            WebService web = new WebService();
            res = web.postWithHeader(url, jsonrequest.toString(),prefManager.getToken());
            Log.d(res, res);

            if (res != null && res.length() > 0) {
                JSONObject json = new JSONObject(res);
                if (json != null) {

                    status = json.getString("status");
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