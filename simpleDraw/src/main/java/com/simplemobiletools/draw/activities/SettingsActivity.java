package com.simplemobiletools.draw.activities;

import android.os.Bundle;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.RelativeLayout;

import com.simplemobiletools.draw.Config;
import com.simplemobiletools.draw.R;


public class SettingsActivity extends SimpleActivity {
    private static Config mConfig;
    SwitchCompat mDarkThemeSwitch, mBrushSizeSwitch;
    RelativeLayout rl_settings_dark_theme_holder, rl_settings_brush_size_holder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        mDarkThemeSwitch = (SwitchCompat) findViewById(R.id.settings_dark_theme);
        mBrushSizeSwitch = (SwitchCompat) findViewById(R.id.settings_brush_size);
        rl_settings_dark_theme_holder = (RelativeLayout) findViewById(R.id.settings_dark_theme_holder);
        rl_settings_brush_size_holder = (RelativeLayout) findViewById(R.id.settings_brush_size_holder);

        mConfig = Config.newInstance(getApplicationContext());
        //ButterKnife.bind(this);

        setupSwitches();


        rl_settings_dark_theme_holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleDarkTheme();
            }
        });
        rl_settings_brush_size_holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleBrushSize();
            }
        });
    }

    private void setupSwitches() {
        mDarkThemeSwitch.setChecked(mConfig.getIsDarkTheme());
        mBrushSizeSwitch.setChecked(mConfig.getIsStrokeWidthBarEnabled());
    }

    public void handleDarkTheme() {
        mDarkThemeSwitch.setChecked(!mDarkThemeSwitch.isChecked());
        mConfig.setIsDarkTheme(mDarkThemeSwitch.isChecked());
        restartActivity();
    }

    public void handleBrushSize() {
        mBrushSizeSwitch.setChecked(!mBrushSizeSwitch.isChecked());
        mConfig.setIsStrokeWidthBarEnabled(mBrushSizeSwitch.isChecked());
    }

    private void restartActivity() {
        TaskStackBuilder.create(getApplicationContext()).addNextIntentWithParentStack(getIntent()).startActivities();
    }
}
