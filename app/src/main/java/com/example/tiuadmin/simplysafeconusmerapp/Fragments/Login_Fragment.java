package com.example.tiuadmin.simplysafeconusmerapp.Fragments;

import android.content.res.ColorStateList;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
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

import com.example.tiuadmin.simplysafeconusmerapp.R;
import com.example.tiuadmin.simplysafeconusmerapp.Utility.GeneralFunction;
import com.example.tiuadmin.simplysafeconusmerapp.Utility.Utils;

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


		mobileNumber.setText("+91");
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
		String getEmailId = mobileNumber.getText().toString();
		String getPassword = password.getText().toString();

		// Check patter for email id
		Pattern p = Pattern.compile(Utils.regEx);

		Matcher m = p.matcher(getEmailId);

		// Check for both field is empty or not
		if (getEmailId.equals("") || getEmailId.length() == 0
				|| getPassword.equals("") || getPassword.length() == 0) {
			loginLayout.startAnimation(shakeAnimation);
			new GeneralFunction().Show_Toast(getActivity(), view,
					"Enter both credentials.");

		}
		// Check if email id is valid or not
		else if (getEmailId.length()<=12)
			new GeneralFunction().Show_Toast(getActivity(), view,
					"Please provide valid mobile number.");
		// Else do login and do your stuff
		else
			Toast.makeText(getActivity(), "Do Login.", Toast.LENGTH_SHORT)
					.show();

	}
}
