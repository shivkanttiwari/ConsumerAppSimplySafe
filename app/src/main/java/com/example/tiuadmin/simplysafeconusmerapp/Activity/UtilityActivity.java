package com.example.tiuadmin.simplysafeconusmerapp.Activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.tiuadmin.simplysafeconusmerapp.CustomAdapter.UtilityNameListAdapter;
import com.example.tiuadmin.simplysafeconusmerapp.R;
import com.example.tiuadmin.simplysafeconusmerapp.Utility.ToastMessage;
import com.example.tiuadmin.simplysafeconusmerapp.utilsApp.radio.RadioWebViewActivity;
import com.example.tiuadmin.simplysafeconusmerapp.utilsApp.radio.entertainment.EntertainmentActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UtilityActivity extends AppCompatActivity {

    ListView lv;
    String[] strList = {"Radio", "Calculator", "Weather", "Games", "Entertainment", "Knowledge"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utility);
        lv = (ListView) findViewById(R.id.listView1);

        UtilityNameListAdapter adapter = new UtilityNameListAdapter(this, strList);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position) {
                    case 0: {
                        RadioWebViewActivity.loadUrl = "http://gaana.com/radiomirchi";
                        startActivity(new Intent(UtilityActivity.this, RadioWebViewActivity.class));
                    }
                    break;

                    case 1: {
                        openCalculator();
                    }
                    break;

                    case 2:
                          //startActivity(new Intent(UtilityActivity.this, SplashActivity.class));
                        break;
                    case 3:

                        break;
                    case 4: {
                        startActivity(new Intent(UtilityActivity.this, EntertainmentActivity.class));
                    }
                    break;
                    case 5:

                        break;
                }
            }
        });

    }


    void openCalculator() {

//        Intent i = new Intent();
//        i.setClassName("com.android.calculator2", "com.android.calculator2.Calculator");
//        startActivity(i);


//        Intent intent = new Intent();
//        intent.setAction(Intent.ACTION_MAIN);
//        intent.addCategory(Intent.CATEGORY_APP_CALCULATOR);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(intent);


        ArrayList<HashMap<String, Object>> items = new ArrayList<>();
        final PackageManager pm = getPackageManager();
        List<PackageInfo> packs = pm.getInstalledPackages(0);
        for (PackageInfo pi : packs) {
            if (pi.packageName.toString().toLowerCase().contains("calcul")) {
                HashMap<String, Object> map = new HashMap<String, Object>();
                map.put("appName", pi.applicationInfo.loadLabel(pm));
                map.put("packageName", pi.packageName);
                items.add(map);
            }
        }
        if (items.size() >= 1) {
            String packageName = (String) items.get(0).get("packageName");
            Intent i = pm.getLaunchIntentForPackage(packageName);
            if (i != null)
                startActivity(i);
        } else {
            ToastMessage.toastMsgShot(getApplicationContext(), "No app available");
        }
    }
}
