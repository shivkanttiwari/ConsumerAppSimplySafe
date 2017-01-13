package com.example.tiuadmin.simplysafeconusmerapp.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.tiuadmin.simplysafeconusmerapp.CustomAdapter.scrollviewanimationcustomadapter;
import com.example.tiuadmin.simplysafeconusmerapp.R;

public class ScrollviewAnimate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrollview_animate);

        scrollviewanimationcustomadapter adapter=new scrollviewanimationcustomadapter(ScrollviewAnimate.this);

        ListView ls=(ListView)findViewById(R.id.list);


        ls.setAdapter(adapter);

    }
}
