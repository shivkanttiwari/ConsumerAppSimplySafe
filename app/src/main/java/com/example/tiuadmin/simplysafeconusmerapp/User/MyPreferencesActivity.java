package com.example.tiuadmin.simplysafeconusmerapp.User;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import com.example.tiuadmin.simplysafeconusmerapp.R;

public class MyPreferencesActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.setting);
    }



}
