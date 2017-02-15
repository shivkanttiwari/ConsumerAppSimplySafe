package com.example.tiuadmin.simplysafeconusmerapp.utilsApps.rewards;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tiuadmin.simplysafeconusmerapp.Callbacks.Callback;
import com.example.tiuadmin.simplysafeconusmerapp.Callbacks.MyAsynckTask;
import com.example.tiuadmin.simplysafeconusmerapp.R;

public class RewardHomeActivity extends AppCompatActivity {

    Button btn_redeem;
    TextView txttotalRewards;

    private String url="http://52.66.101.233/Customer-Backend/public/api/v1/customer/info";

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


        MyAsynckTask obj=new MyAsynckTask(new Callback() {
            @Override
            public void onResult(String result) {

                String res=result;

            }
        },url, RewardHomeActivity.this);
        obj.execute();
    }

    public void init()
    {
        txttotalRewards=(TextView)findViewById(R.id.txttotalRewards);

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
