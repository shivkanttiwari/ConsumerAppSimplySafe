package com.example.tiuadmin.simplysafeconusmerapp.User;

import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.XmlResourceParser;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tiuadmin.simplysafeconusmerapp.Models.Merchant;
import com.example.tiuadmin.simplysafeconusmerapp.R;
import com.example.tiuadmin.simplysafeconusmerapp.Utility.PrefManager;
import com.example.tiuadmin.simplysafeconusmerapp.Webservices.WebService;

import org.json.JSONObject;

import java.util.ArrayList;

public class ChangePasswordActivity extends AppCompatActivity implements View.OnClickListener {
    private static EditText OldPassword, NewPassword,ConfimPassword;
    private static TextView submit;
    private static TextView forgotPassword, signUp;
    //private static CheckBox show_hide_password;
    private static LinearLayout loginLayout;
    private static Animation shakeAnimation;
    private static FragmentManager fragmentManager;

    String GetOldPassword ;//= mobileNumber.getText().toString().trim();
    String GetNewPassword ;//= otp.getText().toString().trim();
    String GetConfirmPassword;// = newpassword.getText().toString().trim();
    String status ;//= json.getString("status");;
    String message;
    PrefManager prefManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_change_password);

        initViews();
        setListeners();
    }
    // Initialize the views
    private void initViews() {

        prefManager=new PrefManager(getApplicationContext());
        OldPassword = (EditText) findViewById(R.id.changepassowrd_oldpassword);
        NewPassword = (EditText) findViewById(R.id.changepassowrd_newpassword);
        ConfimPassword = (EditText) findViewById(R.id.changepassword_confirmpassword);
        submit = (TextView) findViewById(R.id.SubmitChangePaasword);
        //back = (TextView) findViewById(R.id.otpbackToLoginBtn);

        //otp.setText(Const.ForgetPassword_TOKEN);
        // Setting text selector over textviews
        XmlResourceParser xrp = getResources().getXml(R.drawable.text_selector);
        try {
            ColorStateList csl = ColorStateList.createFromXml(getResources(),
                    xrp);

           // back.setTextColor(csl);
            submit.setTextColor(csl);

        } catch (Exception e) {
        }

    }

    // Set Listeners over buttons
    private void setListeners() {
       // back.setOnClickListener(this);
        submit.setOnClickListener(this);
    }

  /*  @Override
    public void onClick(View v) {
        switch (v.getId()) {


            case R.id.SubmitChangePaasword:

                // Call Submit button task
                submitButtonTask();
                break;

        }

    }*/

    private void submitButtonTask() {
        GetOldPassword = OldPassword.getText().toString().trim();
        GetNewPassword = NewPassword.getText().toString().trim();
        GetConfirmPassword = ConfimPassword.getText().toString().trim();






        // First check if email id is not null else show error toast
        if (GetOldPassword.equals("") || GetOldPassword.length() == 0)

          Toast.makeText(ChangePasswordActivity.this,
                    "Please enter valid old password,it can not be blank.",Toast.LENGTH_LONG).show();



            // Else submit email id and fetch passwod or do your stuff
        else  if (GetNewPassword.equals("") || GetNewPassword.length() == 0)

            Toast.makeText(ChangePasswordActivity.this,
                    "Please enter valid new password,it can not be blank.",Toast.LENGTH_LONG).show();

        else  if (GetConfirmPassword.equals("") || GetConfirmPassword.length() == 0)

            Toast.makeText(ChangePasswordActivity.this,
                    "Please enter valid confimr password,it can not be blank.",Toast.LENGTH_LONG).show();



        else
        {
            new AsyncTaskChangePassword().execute();

        }

    }

    /**
     * Making json object request
     */
    private void MakeChagnePasswordRequest(String oldpasword,String newpassowrd,String confirmpassword) {

        String res = null;
        String responseCode = null;
        String returnResponse = null;
        try {

            String url = "http://52.66.101.233/Customer-Backend/public/api/v1/user/password";
            JSONObject jsonrequest = new JSONObject();
            jsonrequest.put("oldPassword", oldpasword);
            jsonrequest.put("newPassword", newpassowrd);
            jsonrequest.put("confirmPassword", confirmpassword);



            WebService web = new WebService();
            res = web.postWithHeader(url, jsonrequest.toString(),prefManager.getToken());
            Log.d(res, res);


            if (res != null) {
                JSONObject json = new JSONObject(res);
                if (json != null) {

                    status = json.getString("status");
                     message = json.getString("message");



                    Toast.makeText(this, message, Toast.LENGTH_LONG).show();



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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {


            case R.id.SubmitChangePaasword:

                // Call Submit button task
                submitButtonTask();
                break;

        }
    }

    // To use the AsyncTask, it must be subclassed
    private class AsyncTaskChangePassword extends AsyncTask<Void, Integer, Void> {
        // Before running code in separate thread
        @Override
        protected void onPreExecute() {
            // Create a new progress dialog
            progressDialog2 = new ProgressDialog(ChangePasswordActivity.this);
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
                MakeChagnePasswordRequest(GetOldPassword, GetNewPassword,GetConfirmPassword);
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

               Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
                finish();
            }
            else {
                Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();

            }
        }
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View view = getCurrentFocus();
        if (view != null && (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) && view instanceof EditText && !view.getClass().getName().startsWith("android.webkit.")) {
            int scrcoords[] = new int[2];
            view.getLocationOnScreen(scrcoords);
            float x = ev.getRawX() + view.getLeft() - scrcoords[0];
            float y = ev.getRawY() + view.getTop() - scrcoords[1];
            if (x < view.getLeft() || x > view.getRight() || y < view.getTop() || y > view.getBottom())
                ((InputMethodManager)this.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow((this.getWindow().getDecorView().getApplicationWindowToken()), 0);
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                Log.i("DATA", "Hit Actionbar Back Button");


                finish();

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
