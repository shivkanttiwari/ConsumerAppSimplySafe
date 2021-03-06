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
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tiuadmin.simplysafeconusmerapp.Models.Merchant;
import com.example.tiuadmin.simplysafeconusmerapp.R;
import com.example.tiuadmin.simplysafeconusmerapp.Utility.Const;
import com.example.tiuadmin.simplysafeconusmerapp.Utility.GeneralFunction;
import com.example.tiuadmin.simplysafeconusmerapp.Utility.PrefManager;
import com.example.tiuadmin.simplysafeconusmerapp.Utility.Utils;
import com.example.tiuadmin.simplysafeconusmerapp.Webservices.WebService;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUp_Fragment extends Fragment implements OnClickListener {
	private static View view;
	private static EditText fullName, emailId, mobileNumber, location,
			password, confirmPassword;
	private static TextView login;
	private static Button signUpButton;
	private static CheckBox terms_conditions;
	private String TAG = SignUp_Fragment.class.getSimpleName();
PrefManager prefManager;
	private TextView msgResponse;
	private ProgressDialog pDialog;
	private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
public  static String phoneNumberCommonforUserRegistraiotn="";
	// Get all edittext texts
	String getFullName;// = fullName.getText().toString();
	String getEmailId ;//= emailId.getText().toString();
	String getMobileNumber;// = mobileNumber.getText().toString();
	String getLocation ;//= location.getText().toString();
	String getPassword ;//= password.getText().toString();
	String getConfirmPassword ;//= confirmPassword.getText().toString();
	String message;
	public SignUp_Fragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.signup_layout, container, false);
		//StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

		//StrictMode.setThreadPolicy(policy);

		initViews();
		setListeners();


		return view;
	}

	// Initialize all views
	private void initViews() {

		prefManager=new PrefManager(getActivity());
		pDialog = new ProgressDialog(getActivity());
		pDialog.setMessage("Loading...");
		pDialog.setCancelable(false);
		fullName = (EditText) view.findViewById(R.id.fullName);
		emailId = (EditText) view.findViewById(R.id.userEmailId);
		mobileNumber = (EditText) view.findViewById(R.id.mobileNumber);
		location = (EditText) view.findViewById(R.id.location);
		password = (EditText) view.findViewById(R.id.password);
		confirmPassword = (EditText) view.findViewById(R.id.confirmPassword);
		signUpButton = (Button) view.findViewById(R.id.signUpBtn);
		login = (TextView) view.findViewById(R.id.already_user);
		terms_conditions = (CheckBox) view.findViewById(R.id.terms_conditions);

		// Setting text selector over textviews
		XmlResourceParser xrp = getResources().getXml(R.drawable.text_selector);
		try {
			ColorStateList csl = ColorStateList.createFromXml(getResources(),
					xrp);

			login.setTextColor(csl);
			terms_conditions.setTextColor(csl);
		} catch (Exception e) {
		}
	}

	// Set Listeners
	private void setListeners() {
		signUpButton.setOnClickListener(this);
		login.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.signUpBtn:

				// Call checkValidation method
				checkValidation();
				break;

			case R.id.already_user:

				// Replace login fragment
				new MainActivity().replaceLoginFragment();
				break;
		}

	}

	// Check Validation Method
	private void checkValidation() {


		// Get all edittext texts
		 getFullName = fullName.getText().toString();
		 getEmailId = emailId.getText().toString();
		 getMobileNumber = mobileNumber.getText().toString();
		 getLocation = location.getText().toString();
		 getPassword = password.getText().toString();
		 getConfirmPassword = confirmPassword.getText().toString();

		// Pattern match for email id
		Pattern p = Pattern.compile(Utils.regEx);
		Matcher m = p.matcher(getEmailId);

		// Check if all strings are null or not
		if (getFullName.equals("") || getFullName.length() == 0

				|| getMobileNumber.equals("") || getMobileNumber.length() == 0

				|| getPassword.equals("") || getPassword.length() == 0
				|| getConfirmPassword.equals("")
				|| getConfirmPassword.length() == 0)

			new GeneralFunction().Show_Toast(getActivity(), view,
					"All fields are required.");

			// Check if email id valid or not
		else if(getEmailId.length()>0 &&  (!m.find()))
		{

			new GeneralFunction().Show_Toast(getActivity(), view,
					"Your Email Id is Invalid.");

		}

			// Check if both password should be equal
		else if (!getConfirmPassword.equals(getPassword))
			new GeneralFunction().Show_Toast(getActivity(), view,
					"Both password doesn't match.");

			// Make sure user should check Terms and Conditions checkbox
		else if (!terms_conditions.isChecked())
			new GeneralFunction().Show_Toast(getActivity(), view,
					"Please select Terms and Conditions.");

			// Else do signup or do your stuff
		else
			//Toast.makeText(getActivity(), "Do SignUp.", Toast.LENGTH_SHORT)
					//.show();
			new AsyncTaskSignUP().execute();

	}

	private void showProgressDialog() {
		if (!pDialog.isShowing())
			pDialog.show();
	}

	private void hideProgressDialog() {
		if (pDialog.isShowing())
			pDialog.hide();
	}

	/**
	 * Making json object request
	 */
	private void makeSignUpRequest(String name,String email,String phone,String password) {
		Const.SIGNUP_TOKEN="";
		String res = null;
		String responseCode = null;
		String returnResponse = null;
		try {

			String url = "http://52.66.101.233/Customer-Backend/public/api/user/signup";
			JSONObject signUpJsonRequestObject = new JSONObject();
			signUpJsonRequestObject.put("name", name);
			signUpJsonRequestObject.put("email", email);
			signUpJsonRequestObject.put("phone", phone);
			signUpJsonRequestObject.put("sspin", password);

			phoneNumberCommonforUserRegistraiotn=phone;

			WebService web = new WebService();
			res = web.postWithHeader(url, signUpJsonRequestObject.toString(),prefManager.getToken());
			Log.d(res, res);


			if (res != null) {
				JSONObject json = new JSONObject(res);
				if (json != null) {

					String status=json.getString("status");
					 message=json.getString("message");
					if(status.equalsIgnoreCase("true"))
					{
						JSONObject userdata=json.getJSONObject("data");

						Const.SIGNUP_TOKEN=userdata.getString("otp");

					}
					Toast.makeText(getActivity(),message,Toast.LENGTH_LONG).show();






				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		/*JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
				Const.URL_JSON_OBJECT, null,
				new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						Log.d(TAG, response.toString());
						msgResponse.setText(response.toString());
						hideProgressDialog();
					}
				}, new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				VolleyLog.d(TAG, "Error: " + error.getMessage());
				hideProgressDialog();
			}
		}) {



			*//**
		 * Passing some request headers
		 * *//*
			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
				HashMap<String, String> headers = new HashMap<String, String>();
				headers.put("Content-Type", "application/json");
				headers.put("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjFmNjRlY2M5ZmMyMDA1ZjA3NGE0OGQ4NmEyZWYyOGJmNWIwOTk0OWViZGRkMWU5NjE3MGU4MjU0N2Y0OGI2Y2NhMzhjOGU5ODlmNDljMGIyIn0.eyJhdWQiOiIxIiwianRpIjoiMWY2NGVjYzlmYzIwMDVmMDc0YTQ4ZDg2YTJlZjI4YmY1YjA5OTQ5ZWJkZGQxZTk2MTcwZTgyNTQ3ZjQ4YjZjY2EzOGM4ZTk4OWY0OWMwYjIiLCJpYXQiOjE0ODI4MzU3MzQsIm5iZiI6MTQ4MjgzNTczNCwiZXhwIjoxNzk4MzY4NTM0LCJzdWIiOiIxIiwic2NvcGVzIjpbXX0.NOLkQNT5P515HdnRbGxwnvPMpfReYZGDbBgqhq5RxAi7I1qyvXOvn-BX08MEUkwZjIz0KKev9Er54jX007uUhfYWOfVH6Ehwhro_F4WrjYVVLmKBGlh--PlyYQnFKbos2vWjzz9DWclZqtxz906gd5f_P10wDN1O6r0VfgMAMGjkJZwrnIy97zmXb9VSzfLDrFeSjUi4gF3vtQVXUWICjqlWgGZ0TgLiEYEh5ivsFNrfLri1YDhsX09vP8GuztdiML5JCN3MEyuIL_dWIsQnkYrnIV3LSULCoTftqBAyWbVYY9KGw28bf1fWx04fG-GQJ-HcZT4ZZtBYW4-3jt-Nb57nxbk76k4utbN0AcFjwJwdc5g4zQHGG7686WI0to2o1oc1aRxjzqOGK6fDfkQT1caABpKBb63kM1bLd__-7CAIIIuXSdKY59kPanOo3dZE5N2p1EMN6swToE8QlALEU6NwXk3otfI88RC6vnQlNOszqBzzdU_uF92hNa3hDo11jCh0Nfr4bjefIgIyl8rrwR5mDBNq78OO447xOH8XnlkwSWLLVkVqDr46fAwKRsph-N1mA6M8wkn-rVR6mTXJTUv6oeVVF7VP4hJ03IC71R5WG6fZNAS2UUspRai21mktopOeUNQvkSsWO51jhD5hrfETbuxRZRtxyCWFFwK68WE");
				return headers;
			}

			@Override
			protected Map<String, String> getParams() {
				Map<String, String> params = new HashMap<String, String>();
				params.put("name", "shivkant");
				params.put("email", "shivkant.tiwari123@gmail.com");
				params.put("phone", "9096572182");
				params.put("sspin", "123456");

				return params;
			}

		};

		// Adding request to request queue
		AppController.getInstance().addToRequestQueue(jsonObjReq);

		// Cancelling request
		// ApplicationController.getInstance().getRequestQueue().cancelAll(tag_json_obj);
	}*/
	}

	//******************webservice********
	private ProgressDialog progressDialog2 = null;
	String username;
	ArrayList<Merchant> setget = new ArrayList<>();
	String fname, lname, strGender;

	// To use the AsyncTask, it must be subclassed
	private class AsyncTaskSignUP extends AsyncTask<Void, Integer, Void> {
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
				makeSignUpRequest(getFullName,getEmailId,getMobileNumber,getConfirmPassword);
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
			if(Const.SIGNUP_TOKEN.length()>0)
			{
				Const.OTP_VERIFICATION_MOUDLE_ID_FOR_SMS=2;
				new MainActivity().replaceRgistrationOTPVerificaitonFragment();
			}

			else
			{
				Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();
			}

		}
	}
}
