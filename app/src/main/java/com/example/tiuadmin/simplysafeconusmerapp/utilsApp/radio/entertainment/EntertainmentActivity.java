package com.example.tiuadmin.simplysafeconusmerapp.utilsApp.radio.entertainment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.tiuadmin.simplysafeconusmerapp.CustomAdapter.UtilityNameListAdapter;
import com.example.tiuadmin.simplysafeconusmerapp.R;
import com.example.tiuadmin.simplysafeconusmerapp.utilsApp.radio.RadioWebViewActivity;

import java.util.ArrayList;

public class EntertainmentActivity extends AppCompatActivity {
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entertainment);
        //getSupportActionBar().setTitle("Entertainment");

        lv = (ListView) findViewById(R.id.listView1);

        ArrayList<String> utiltyName = new ArrayList<>();

        utiltyName.add("Hot Star");
        utiltyName.add("Cricbuzz");
//        utiltyName.add("Weather");
//        utiltyName.add("Games");
//        utiltyName.add("Entertainment");
//        utiltyName.add("Knowledge");

        UtilityNameListAdapter adapter = new UtilityNameListAdapter(this, utiltyName);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position) {
                    case 0: {
                        RadioWebViewActivity.loadUrl = "http://www.hotstar.com/";
                        startActivity(new Intent(EntertainmentActivity.this, RadioWebViewActivity.class));
                    }
                    break;
                    case 1: {
                        RadioWebViewActivity.loadUrl = "http://www.m.cricbuzz.com//";
                        startActivity(new Intent(EntertainmentActivity.this, RadioWebViewActivity.class));
                    }


                    break;
                    case 2:
                        //  startActivity(new Intent(UtilityActivity.this, SplashActivity.class));

                        break;
                    case 3:


                        break;
                    case 4: {
                        //startActivity(new Intent(UtilityActivity.this, EntertainmentActivity.class));
                    }
                    break;
                    case 5:
                        break;
                }
            }
        });


    }
}
