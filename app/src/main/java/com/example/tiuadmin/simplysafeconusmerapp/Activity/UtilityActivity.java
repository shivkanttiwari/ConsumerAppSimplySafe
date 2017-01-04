package com.example.tiuadmin.simplysafeconusmerapp.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.tiuadmin.simplysafeconusmerapp.CustomAdapter.UtilityNameListAdapter;
import com.example.tiuadmin.simplysafeconusmerapp.R;

import java.util.ArrayList;

public class UtilityActivity extends AppCompatActivity {

    ListView lvUtiltiyName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utility);
        lvUtiltiyName=(ListView)findViewById(R.id.listuitlityname);

        ArrayList<String>utiltyName=new ArrayList<>();

        utiltyName.add("Calculator");
        utiltyName.add("Wheather");
        utiltyName.add("Games");
        utiltyName.add("Entertainment");
        utiltyName.add("Knowledge");


        UtilityNameListAdapter adapter=new UtilityNameListAdapter(this,utiltyName);

        lvUtiltiyName.setAdapter(adapter);

    }
}
