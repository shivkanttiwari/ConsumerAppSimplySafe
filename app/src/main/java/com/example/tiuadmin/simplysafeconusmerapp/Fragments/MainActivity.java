package com.example.tiuadmin.simplysafeconusmerapp.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.tiuadmin.simplysafeconusmerapp.R;
import com.example.tiuadmin.simplysafeconusmerapp.Utility.Utils;


public class MainActivity extends AppCompatActivity {
	private static FragmentManager fragmentManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		fragmentManager = getSupportFragmentManager();

		// If savedinstnacestate is null then replace login fragment
		if (savedInstanceState == null) {
			fragmentManager
					.beginTransaction()
					.replace(R.id.frameContainer, new Login_Fragment(),
							Utils.Login_Fragment).commit();
		}

		// On close icon click finish activity
		findViewById(R.id.close_activity).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						finish();

					}
				});

	}

	// Replace Login Fragment with animation
	protected void replaceLoginFragment() {
		fragmentManager
				.beginTransaction()
				.setCustomAnimations(R.anim.left_enter, R.anim.right_out)
				.replace(R.id.frameContainer, new Login_Fragment(),
						Utils.Login_Fragment).commit();
	}

	@Override
	public void onBackPressed() {

		// Find the tag of signup and forgot password fragment
		Fragment SignUp_Fragment = fragmentManager
				.findFragmentByTag(Utils.SignUp_Fragment);
		Fragment ForgotPassword_Fragment = fragmentManager
				.findFragmentByTag(Utils.ForgotPassword_Fragment);

		// Check if both are null or not
		// If both are not null then replace login fragment else do backpressed
		// task

		if (SignUp_Fragment != null)
			replaceLoginFragment();
		else if (ForgotPassword_Fragment != null)
			replaceLoginFragment();
		else
			super.onBackPressed();
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
}
