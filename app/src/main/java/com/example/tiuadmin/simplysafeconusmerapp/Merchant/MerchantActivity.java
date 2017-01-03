package com.example.tiuadmin.simplysafeconusmerapp.Merchant;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.example.tiuadmin.simplysafeconusmerapp.CustomAdapter.MerchantViewAdapter;
import com.example.tiuadmin.simplysafeconusmerapp.R;

import java.util.ArrayList;
import java.util.List;

public class MerchantActivity extends AppCompatActivity {
    private List<String> list = new ArrayList<String>();
    MerchantViewAdapter mAdapter;



    private Toolbar toolbar;

    private RecyclerView mRecyclerView;
    private StaggeredGridLayoutManager mStaggeredLayoutManager;

    private boolean isListView;
    private Menu menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant);

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.list);

       // mStaggeredLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setHasFixedSize(false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
       arrayList();
//
        //mAdapter = new MerchantViewAdapter(list,this);
       // mRecyclerView.setAdapter(mAdapter);



        toolbar = (Toolbar) findViewById(R.layout.toolbar);
        setUpActionBar();

        mStaggeredLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);

        mRecyclerView = (RecyclerView) findViewById(R.id.list);
        mRecyclerView.setLayoutManager(mStaggeredLayoutManager);

        mRecyclerView.setHasFixedSize(true); //Data size is fixed - improves performance
        mAdapter = new MerchantViewAdapter(getApplicationContext());
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(onItemClickListener);

        isListView = true;




    }
    MerchantViewAdapter.OnItemClickListener onItemClickListener = new MerchantViewAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View v, int position) {

            Toast.makeText(getApplicationContext(),"merchant selected",Toast.LENGTH_SHORT).show();
        }
    };

    public void arrayList(){
        for (int i = 0; i< 20; i++){
            list.add("This is row of number "+ i);
        }
    }

    private void setUpActionBar() {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getActionBar().setDisplayHomeAsUpEnabled(false);
            getActionBar().setDisplayShowTitleEnabled(true);
            getActionBar().setElevation(7);
        }
    }
}
