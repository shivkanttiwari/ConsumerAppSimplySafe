package com.example.tiuadmin.simplysafeconusmerapp.User;

import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.tiuadmin.simplysafeconusmerapp.R;

public class MyPreferencesActivity extends PreferenceActivity  {
    private AppCompatDelegate mDelegate;
    CheckBoxPreference prefCatOne;
    CheckBoxPreference prefCatTwo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getDelegate().installViewFactory();
        getDelegate().onCreate(savedInstanceState);

        super.onCreate(savedInstanceState);
      //  getActionBar().setDisplayShowHomeEnabled(true);
       // getActionBar().setDisplayHomeAsUpEnabled(true);
        addPreferencesFromResource(R.xml.setting);
         prefCatOne= (CheckBoxPreference)findPreference("prefallowpushnotificaiton");
         prefCatTwo= (CheckBoxPreference)findPreference("prefallowpushnotificaitonpopup");


        prefCatOne.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {

            public boolean onPreferenceClick(Preference preference) {

                if(prefCatOne.isChecked())
                {
                    prefCatTwo.setSelectable(false);
                    prefCatTwo.setChecked(false);
                }
                else
                {
                    prefCatTwo.setSelectable(true);
                }

                Toast.makeText(getBaseContext(), "Some text", Toast.LENGTH_SHORT).show();
                return true;
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
               // NavUtils.navigateUpFromSameTask(this);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        getDelegate().onPostCreate(savedInstanceState);
    }

    public ActionBar getSupportActionBar() {
        return getDelegate().getSupportActionBar();
    }

    public void setSupportActionBar(@Nullable Toolbar toolbar) {
        getDelegate().setSupportActionBar(toolbar);
    }

    @Override
    public MenuInflater getMenuInflater() {
        return getDelegate().getMenuInflater();
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        getDelegate().setContentView(layoutResID);
    }

    @Override
    public void setContentView(View view) {
        getDelegate().setContentView(view);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        getDelegate().setContentView(view, params);
    }

    @Override
    public void addContentView(View view, ViewGroup.LayoutParams params) {
        getDelegate().addContentView(view, params);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        getDelegate().onPostResume();
    }

    @Override
    protected void onTitleChanged(CharSequence title, int color) {
        super.onTitleChanged(title, color);
        getDelegate().setTitle(title);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        getDelegate().onConfigurationChanged(newConfig);
    }

    @Override
    protected void onStop() {
        super.onStop();
        getDelegate().onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getDelegate().onDestroy();
    }

    public void invalidateOptionsMenu() {
        getDelegate().invalidateOptionsMenu();
    }

    private AppCompatDelegate getDelegate() {
        if (mDelegate == null) {
            mDelegate = AppCompatDelegate.create(this, null);
        }
        return mDelegate;
    }


}
