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

import org.json.JSONObject;


public class RewardHomeActivity extends AppCompatActivity {

    Button btn_redeem;
    TextView txtview_RewardsPoint;
    TextView txttotalRewards;
    String status;
    String message;
    String rewardPoints;

    private String url="http://simplypos.co.in/api/v1/customer/info";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reward_home);

        btn_redeem = (Button) findViewById(R.id.button1);
        txtview_RewardsPoint=(TextView)findViewById(R.id.txttotalRewards) ;
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

                try {




                    if (res != null && res.length() > 0) {
                        JSONObject json = new JSONObject(res);
                        if (json != null) {

                            status = json.getString("status");
                             message = json.getString("message");
                            if (status.equalsIgnoreCase("true")) {
                                JSONObject jsonuserdata = json.getJSONObject("data");

                                 rewardPoints=jsonuserdata.getString("balanceRewardPoints");

                                txtview_RewardsPoint.setText(rewardPoints);

                            }


                            // new GeneralFunction().hideProgressDialog();
                        }
                    } else {
                        message="Unable to get user information";
                        // new GeneralFunction().hideProgressDialog();
                    }

                } catch (Exception e) {
                    message="Unable to get user information";
                    //new GeneralFunction().hideProgressDialog();
                    e.printStackTrace();
                }

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
