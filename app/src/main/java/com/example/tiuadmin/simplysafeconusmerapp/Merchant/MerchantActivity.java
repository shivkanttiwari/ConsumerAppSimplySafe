package com.example.tiuadmin.simplysafeconusmerapp.Merchant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.tiuadmin.simplysafeconusmerapp.CustomAdapter.MerchantViewAdapter;
import com.example.tiuadmin.simplysafeconusmerapp.R;

import java.util.ArrayList;
import java.util.List;

public class MerchantActivity extends AppCompatActivity {
    private List<String> list = new ArrayList<String>();
    MerchantViewAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant);

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.list);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        arrayList();

        mAdapter = new MerchantViewAdapter(list,this);
        mRecyclerView.setAdapter(mAdapter);


    }

    public void arrayList(){
        for (int i = 0; i< 20; i++){
            list.add("This is row of number "+ i);
        }
    }
}
