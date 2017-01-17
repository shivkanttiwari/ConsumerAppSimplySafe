package com.example.tiuadmin.simplysafeconusmerapp.utilsApp.radio.entertainment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.tiuadmin.simplysafeconusmerapp.CustomAdapter.UtilityNameListAdapter;
import com.example.tiuadmin.simplysafeconusmerapp.R;
import com.example.tiuadmin.simplysafeconusmerapp.utilsApp.radio.UtilsConstants;

public class EntertainmentActivity extends AppCompatActivity {
    ListView lv;
    String[] strList = {"Cricket", "Channels"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entertainment);
        //getSupportActionBar().setTitle("Entertainment");

        lv = (ListView) findViewById(R.id.listView1);

        UtilityNameListAdapter adapter = new UtilityNameListAdapter(this, strList);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position) {
                    case 0: {
                        WebViewListsActivity.loadList = UtilsConstants.CRICKET;
                        startActivity(new Intent(EntertainmentActivity.this, WebViewListsActivity.class));
                    }
                    break;
                    case 1: {
                        WebViewListsActivity.loadList = UtilsConstants.CHANNELS;
                        startActivity(new Intent(EntertainmentActivity.this, WebViewListsActivity.class));
                    }
                    break;
                }
            }
        });


    }
}
