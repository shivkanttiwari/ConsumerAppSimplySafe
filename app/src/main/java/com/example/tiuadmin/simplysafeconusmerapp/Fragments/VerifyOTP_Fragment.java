package com.example.tiuadmin.simplysafeconusmerapp.Fragments;


import android.content.res.ColorStateList;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tiuadmin.simplysafeconusmerapp.R;
import com.example.tiuadmin.simplysafeconusmerapp.Utility.GeneralFunction;
import com.example.tiuadmin.simplysafeconusmerapp.Utility.Utils;
import com.example.tiuadmin.simplysafeconusmerapp.Webservices.WebService;

import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 */
public class VerifyOTP_Fragment extends Fragment implements View.OnClickListener {
    private static View view;

    private static EditText emailId;
    private static TextView submit, back;

    public VerifyOTP_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.registerotpverficaiton, container, false);

        initViews();
        setListeners();


        return  view;
    }
    // Initialize the views
    private void initViews() {
        emailId = (EditText) view.findViewById(R.id.registration_otp);
        submit = (TextView) view.findViewById(R.id.otpverficaitonsubmitBtn);
        back = (TextView) view.findViewById(R.id.otpbackToLoginBtn);

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
        String getEmailId = emailId.getText().toString();

        // Pattern for email id validation
        Pattern p = Pattern.compile(Utils.regEx);

        // Match the pattern
        Matcher m = p.matcher(getEmailId);

        // First check if email id is not null else show error toast
        if (getEmailId.equals("") || getEmailId.length() == 0)

            new GeneralFunction().Show_Toast(getActivity(), view,
                    "Please enter 4 digit OTP.");

            // Check if email id is valid or not
        else if (!m.find())
            new GeneralFunction().Show_Toast(getActivity(), view,
                    "Invalid OTP.");

            // Else submit email id and fetch passwod or do your stuff
        else
            Toast.makeText(getActivity(), "Get Forgot Password.",
                    Toast.LENGTH_SHORT).show();
    }

    /**
     * Making json object request
     */
    private void makeForgetPasswordRequest(String phone,String password) {
        new GeneralFunction().showProgressDialog(getActivity());
        String res = null;
        String responseCode = null;
        String returnResponse = null;
        try {

            String url = "http://52.66.101.233/Customer-Backend/public/api/user/otp/verify";
            JSONObject jsonrequest = new JSONObject();
            jsonrequest.put("otp", phone);
            jsonrequest.put("phone", password);



            WebService web = new WebService();
            res = web.postWithHeader(url, jsonrequest.toString());
            Log.d(res, res);


            if (res != null) {
                JSONObject json = new JSONObject(res);
                if (json != null) {

                    String status = json.getString("status");
                    String message = json.getString("message");
                    if (status.equalsIgnoreCase("true"))
                        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();

                    new MainActivity().replaceRgistrationOTPVerificaitonFragment();

                }
            }
            new GeneralFunction().hideProgressDialog();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
