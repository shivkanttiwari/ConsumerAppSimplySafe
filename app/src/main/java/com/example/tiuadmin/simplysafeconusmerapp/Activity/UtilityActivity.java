package com.example.tiuadmin.simplysafeconusmerapp.Activity;

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

import cz.martykan.forecastie.activities.SplashActivity;

public class UtilityActivity extends AppCompatActivity {

    ListView lvUtiltiyName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utility);
        lvUtiltiyName = (ListView) findViewById(R.id.listuitlityname);

        ArrayList<String> utiltyName = new ArrayList<>();

        utiltyName.add("Radio");
        utiltyName.add("Calculator");
        utiltyName.add("Weather");
        utiltyName.add("Games");
        utiltyName.add("Entertainment");
        utiltyName.add("Knowledge");

        UtilityNameListAdapter adapter = new UtilityNameListAdapter(this, utiltyName);
        lvUtiltiyName.setAdapter(adapter);

        lvUtiltiyName.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(UtilityActivity.this, RadioWebViewActivity.class));
//                        Intent i = new Intent();
//                        i.setClassName("com.android.calculator2", "com.android.calculator2.Calculator");
//                        startActivity(i);

                        break;
                    case 1:
                        Intent i = new Intent();
                        i.setClassName("com.android.calculator2", "com.android.calculator2.Calculator");
                        startActivity(i);

                        break;
                    case 2:
                        startActivity(new Intent(UtilityActivity.this, SplashActivity.class));

                        break;
                    case 3:


                        break;
                    case 4:


                        break;
                    case 5:


                        break;
                }
            }
        });

    }
}
