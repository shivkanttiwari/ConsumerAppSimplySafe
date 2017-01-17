package com.example.tiuadmin.simplysafeconusmerapp.Fragments;


import android.app.ProgressDialog;
import android.content.res.ColorStateList;
import android.content.res.XmlResourceParser;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tiuadmin.simplysafeconusmerapp.Models.Merchant;
import com.example.tiuadmin.simplysafeconusmerapp.R;
import com.example.tiuadmin.simplysafeconusmerapp.Utility.Const;
import com.example.tiuadmin.simplysafeconusmerapp.Webservices.WebService;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class VerifyOTPForgetPassword extends Fragment implements View.OnClickListener {
    private static View view;

    private static EditText phonenumber,ed_otp;
    private static TextView submit, back;
    String getphonenumber ;//= phonenumber.getText().toString().trim();
    String getotp ;//= otp.getText().toString().trim();
    String status;// = json.getString("status");

    public VerifyOTPForgetPassword() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_verify_otpforget_password, container, false);

        initViews();
        setListeners();


        return  view;
    }
    // Initialize the views
    private void initViews() {
        phonenumber = (EditText) view.findViewById(R.id.registration_otp_phonenumber);
        ed_otp = (EditText) view.findViewById(R.id.registration_otp);
        submit = (TextView) view.findViewById(R.id.otpverficaitonsubmitBtn);
        back = (TextView) view.findViewById(R.id.otpbackToLoginBtn);

        phonenumber.setText(Const.USER_MOBILENUMBER);
       // ed_otp.setText(Const.ForgetPassword_TOKEN);
        // Setting text selector over textviews
        XmlResourceParser xrp = getResources().getXml(R.drawable.text_selector);
        try {
            ColorStateList csl = ColorStateList.createFromXml(getResources(),
                    xrp);

            back.setTextColor(csl);
            submit.setTextColor(csl);

        } catch (Exception e) {
        }

    }

    public void setOTP(String otp)
    {
        otp=otp.substring(0,6);
        ed_otp.setText(otp);
    }

    // Set Listeners over buttons
    private void setListeners() {
        back.setOnClickListener(this);
        submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.otpbackToLoginBtn:

                // Replace Login Fragment on Back Presses
                new MainActivity().replaceLoginFragment();

                break;

            case R.id.otpverficaitonsubmitBtn:

                // Call Submit button task
                submitButtonTask();
                break;

        }

    }

    private void submitButtonTask() {
         getphonenumber = phonenumber.getText().toString().trim();
         getotp = ed_otp.getText().toString().trim();

        /*// Pattern for email id validation
        Pattern p = Pattern.compile(Utils.regEx);

        // Match the pattern
        Matcher m = p.matcher(getotp);

        // First check if email id is not null else show error toast
        if (getotp.equals("") || getotp.length() == 0)

            new GeneralFunction().Show_Toast(getActivity(), view,
                    "Please enter 4 digit OTP.");

            // Check if email id is valid or not
        else if (!m.find())
            new GeneralFunction().Show_Toast(getActivity(), view,
                    "Invalid OTP.");

            // Else submit email id and fetch passwod or do your stuff
        else  if (getphonenumber.equals("") || getphonenumber.length() == 0)

            new GeneralFunction().Show_Toast(getActivity(), view,
                    "Please enter valid mobile number");

            // Check if email id is valid or not
        else if (!m.find())
            new GeneralFunction().Show_Toast(getActivity(), view,
                    "Invalid Mobile number");*/
        // else
        {
            new AsyncTaskChangePasswordForgetPassword().execute();
        }

    }

    /**
     * Making json object request
     */
    private void makeForgetPasswordRequest(String phone,String otp) {

        String res = null;
        String responseCode = null;
        String returnResponse = null;
        try {

            String url = "http://52.66.101.233/Customer-Backend/public/api/user/passwordotp/verify";
            JSONObject jsonrequest = new JSONObject();
            jsonrequest.put("otp", otp);
            jsonrequest.put("phone", phone);



            WebService web = new WebService();
            res = web.postWithHeader(url, jsonrequest.toString());
            Log.d(res, res);


            if (res != null) {
                JSONObject json = new JSONObject(res);
                if (json != null) {

                     status = json.getString("status");
                    String message = json.getString("message");

                    Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();



                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //******************webservice********
    private ProgressDialog progressDialog2 = null;
    String username;
    ArrayList<Merchant> setget = new ArrayList<>();
    String fname, lname, strGender;

    // To use the AsyncTask, it must be subclassed
    private class AsyncTaskChangePasswordForgetPassword extends AsyncTask<Void, Integer, Void> {
        // Before running code in separate thread
        @Override
        protected void onPreExecute() {
            // Create a new progress dialog
            progressDialog2 = new ProgressDialog(getActivity());
            progressDialog2.getWindow().setBackgroundDrawableResource(R.color.colorPrimaryDark);
            progressDialog2.getWindow().setGravity(Gravity.CENTER);
            // Set the progress dialog to display a horizontal progress bar
            progressDialog2.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            // Set the dialog title to 'Loading...'
            // Set the dialog message to 'Loading application View, please
            // wait...'
            progressDialog2.setMessage("Pleaes Wait...");
            // This dialog can't be canceled by pressing the back key
            progressDialog2.setCancelable(false);
            // This dialog isn't indeterminate
            progressDialog2.setIndeterminate(false);
            // Display the progress dialog
            progressDialog2.show();
        }

        // The code to be executed in a background thread.
        @Override
        protected Void doInBackground(Void... params) {
            try {
                makeForgetPasswordRequest(getphonenumber, getotp);
            } catch (Exception e) {
                progressDialog2.dismiss();
                e.printStackTrace();
            }
            return null;
        }

        // after executing the code in the thread
        @Override
        protected void onPostExecute(Void result) {
            progressDialog2.dismiss();
            if (status.equalsIgnoreCase("true"))
            {

                new MainActivity().replaceChangePasswordFragment();
            }
        }
    }
}
