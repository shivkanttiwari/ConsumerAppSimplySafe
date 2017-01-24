package com.example.tiuadmin.simplysafeconusmerapp.utilsApps.rewards;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.tiuadmin.simplysafeconusmerapp.R;

public class RewardHomeActivity extends AppCompatActivity {

    Button btn_redeem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reward_home);

        btn_redeem = (Button) findViewById(R.id.button1);
        btn_redeem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RewardHomeActivity.this, RewardsActivity.class));
            }
        });
    }
}
