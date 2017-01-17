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
import com.example.tiuadmin.simplysafeconusmerapp.utilsApp.radio.UtilsConstants;

public class WebViewListsActivity extends AppCompatActivity {

    ListView lv;

    public static int loadList;
    String[] loadedListLinks;

    //********PREPARE LIST*******
    String[] strCricket = {"ICC", "ESPN", "Cricbuzz"};
    String[] strCricketLinks = {"http://www.icc-cricket.com/", "http://m.espncricinfo.com/", "http://www.m.cricbuzz.com/"};

    String[] strChannels = {"Hot Star", "sonyLIV", "EROS NOW"};
    String[] strChannelsLinks = {"http://www.hotstar.com/", "http://www.sonyliv.com/", "http://erosnow.com/welcome"};
    //********PREPARE LIST*******


    void setAdapter(String[] str) {
        UtilityNameListAdapter adapter = new UtilityNameListAdapter(this, str);
        lv.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_lists);

        lv = (ListView) findViewById(R.id.listView1);

        switch (loadList) {
            case UtilsConstants.CRICKET: {
                setAdapter(strCricket);
                loadedListLinks = strCricketLinks;
            }
            break;
            case UtilsConstants.CHANNELS: {
                setAdapter(strChannels);
                loadedListLinks = strChannelsLinks;
            }
            break;
        }

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position) {
                    case 0: {
                        RadioWebViewActivity.loadUrl = loadedListLinks[position];
                        startActivity(new Intent(WebViewListsActivity.this, RadioWebViewActivity.class));
                    }
                    break;
                    case 1: {
                        RadioWebViewActivity.loadUrl = loadedListLinks[position];
                        startActivity(new Intent(WebViewListsActivity.this, RadioWebViewActivity.class));
                    }
                    break;
                    case 2: {
                        RadioWebViewActivity.loadUrl = loadedListLinks[position];
                        startActivity(new Intent(WebViewListsActivity.this, RadioWebViewActivity.class));
                    }
                    break;
                    case 3: {

                    }
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
