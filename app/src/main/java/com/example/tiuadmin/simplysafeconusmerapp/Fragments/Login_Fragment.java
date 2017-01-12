package com.example.tiuadmin.simplysafeconusmerapp.Fragments;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tiuadmin.simplysafeconusmerapp.Activity.DrawerActivity;
import com.example.tiuadmin.simplysafeconusmerapp.R;
import com.example.tiuadmin.simplysafeconusmerapp.Utility.Const;
import com.example.tiuadmin.simplysafeconusmerapp.Utility.GeneralFunction;
import com.example.tiuadmin.simplysafeconusmerapp.Utility.Utils;
import com.example.tiuadmin.simplysafeconusmerapp.Webservices.WebService;

import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login_Fragment extends Fragment implements OnClickListener {
	private static View view;

	private static EditText mobileNumber, password;
	private static Button loginButton;
	private static TextView forgotPassword, signUp;
	//private static CheckBox show_hide_password;
	private static LinearLayout loginLayout;
	private static Animation shakeAnimation;
	private static FragmentManager fragmentManager;

	public Login_Fragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.login_layout, container, false);
		initViews();
		setListeners();
		return view;
	}

	// Initiate Views
	private void initViews() {
		fragmentManager = getActivity().getSupportFragmentManager();

		mobileNumber = (EditText) view.findViewById(R.id.login_username_editText);
		password = (EditText) view.findViewById(R.id.login_pass_editText);
		loginButton = (Button) view.findViewById(R.id.login_button1);
		forgotPassword = (TextView) view.findViewById(R.id.forget_password_link);
		signUp = (TextView) view.findViewById(R.id.SignUpLink);
		//show_hide_password = (CheckBox) view
		//		.findViewById(R.id.show_hide_password);
		loginLayout = (LinearLayout) view.findViewById(R.id.login_layout);

		//mobileNumber.setText("9971119874");


		// Load ShakeAnimation
		shakeAnimation = AnimationUtils.loadAnimation(getActivity(),
				R.anim.shake);

		// Setting text selector over textviews
		XmlResourceParser xrp = getResources().getXml(R.drawable.text_selector);
		try {
			ColorStateList csl = ColorStateList.createFromXml(getResources(),
					xrp);

			forgotPassword.setTextColor(csl);
		//	show_hide_password.setTextColor(csl);
			signUp.setTextColor(csl);
		} catch (Exception e) {
		}



		Selection.setSelection(mobileNumber.getText(), mobileNumber.getText().length());
		mobileNumber.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
									  int count) {
				// TODO Auto-generated method stub
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
										  int after) {
				// TODO Auto-generated method stub
			}

			@Override
			public void afterTextChanged(Editable s) {
				if (!s.toString().startsWith("+91")) {
					mobileNumber.setText("+91");
					Selection.setSelection(mobileNumber.getText(), mobileNumber
							.getText().length());

				}

			}

		});
	}

	// Set Listeners
	private void setListeners() {
		loginButton.setOnClickListener(this);
		forgotPassword.setOnClickListener(this);
		signUp.setOnClickListener(this);
		mobileNumber.setText("9096572182");
		password.setText("1234567");

//		// Set check listener over checkbox for showing and hiding password
//		show_hide_password
//				.setOnCheckedChangeListener(new OnCheckedChangeListener() {
//
//					@Override
//					public void onCheckedChanged(CompoundButton button,
//							boolean isChecked) {
//
//						// If it is checkec then show password else hide
//						// password
//						if (isChecked) {
//
//							show_hide_password.setText(R.string.hide_pwd);// change
//																			// checkbox
//																			// text
//
//							password.setInputType(InputType.TYPE_CLASS_TEXT);
//							password.setTransformationMethod(HideReturnsTransformationMethod
//									.getInstance());// show password
//						} else {
//							show_hide_password.setText(R.string.show_pwd);// change
//																			// checkbox
//																			// text
//
//							password.setInputType(InputType.TYPE_CLASS_TEXT
//									| InputType.TYPE_TEXT_VARIATION_PASSWORD);
//							password.setTransformationMethod(PasswordTransformationMethod
//									.getInstance());// hide password
//
//						}
//
//					}
//				});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.login_button1:
			checkValidation();
			//getActivity().startActivity(new Intent(getActivity(), DrawerActivity.class));

			//getActivity().finish();
			break;

		case R.id.forget_password_link:

			// Replace forgot password fragment with animation
			fragmentManager
					.beginTransaction()
					.setCustomAnimations(R.anim.right_enter, R.anim.left_out)
					.replace(R.id.frameContainer,
							new ForgotPassword_Fragment(),
							Utils.ForgotPassword_Fragment).commit();
			break;
		case R.id.SignUpLink:

			// Replace signup frgament with animation
			fragmentManager
					.beginTransaction()
					.setCustomAnimations(R.anim.right_enter, R.anim.left_out)
					.replace(R.id.frameContainer, new SignUp_Fragment(),
							Utils.SignUp_Fragment).commit();
			break;
		}

	}

	// Check Validation before login
	private void checkValidation() {

		// Get email id and password
		String getMobilenumber = mobileNumber.getText().toString();
		String getPassword = password.getText().toString();
		getMobilenumber=getMobilenumber.substring(3,getMobilenumber.length());
		makeLoginRequest(getMobilenumber,getPassword);
		// Check patter for email id
		Pattern p = Pattern.compile(Utils.regEx);

		Matcher m = p.matcher(getMobilenumber);

		// Check for both field is empty or not
		if (getMobilenumber.equals("") || getMobilenumber.length() == 0
				|| getPassword.equals("") || getPassword.length() == 0) {
			loginLayout.startAnimation(shakeAnimation);
			new GeneralFunction().Show_Toast(getActivity(), view,
					"Enter both credentials.");

		}
		// Check if email id is valid or not
		else if (getMobilenumber.length()<10)
			new GeneralFunction().Show_Toast(getActivity(), view,
					"Please provide valid mobile number.");
		// Else do login and do your stuff
		else
		{



		}


	}

	/**
	 * Making json object request
	 */
	private void makeLoginRequest(String phone,String password) {
		new GeneralFunction().showProgressDialog(getActivity());
		phone="9096572182";
		String res = null;
		String responseCode = null;
		String returnResponse = null;
		try {

			String url = "http://52.66.101.233/Customer-Backend/public/api/token";
			JSONObject jsonrequest = new JSONObject();
			jsonrequest.put("phone", phone);
			jsonrequest.put("sspin", password);



			WebService web = new WebService();
			res = web.postWithHeader(url, jsonrequest.toString());
			Log.d(res, res);


			if (res != null && res.length()>0) {
				JSONObject json = new JSONObject(res);
				if (json != null) {

					String logintoken = json.getString("access_token");

					Const.LOGIN_TOKEN=logintoken;
					Const.TOKEN_WITH_BEARER+=Const.LOGIN_TOKEN;
					Log.d("token",Const.TOKEN_WITH_BEARER);
					getActivity().startActivity(new Intent(getActivity(), DrawerActivity.class));
					Toast.makeText(getActivity(), "Login Successful", Toast.LENGTH_SHORT)
							.show();
					getActivity().finish();
					new GeneralFunction().hideProgressDialog();
				}
			}
			else {
				Toast.makeText(getActivity(), "Please provide valid mobile number  and password.", Toast.LENGTH_SHORT)
						.show();
				new GeneralFunction().hideProgressDialog();
			}

		} catch (Exception e) {
			new GeneralFunction().hideProgressDialog();
			e.printStackTrace();
		}
	}
}
