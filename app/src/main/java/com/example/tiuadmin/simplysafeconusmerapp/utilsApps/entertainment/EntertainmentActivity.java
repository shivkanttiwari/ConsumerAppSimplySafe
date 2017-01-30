package com.example.tiuadmin.simplysafeconusmerapp.utilsApps.entertainment;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.example.tiuadmin.simplysafeconusmerapp.APPUtility.GridAdapter;
import com.example.tiuadmin.simplysafeconusmerapp.APPUtility.GridSpacingItemDecoration;
import com.example.tiuadmin.simplysafeconusmerapp.APPUtility.UtilityActivity;
import com.example.tiuadmin.simplysafeconusmerapp.Models.MainMenuDashboard;
import com.example.tiuadmin.simplysafeconusmerapp.R;
import com.example.tiuadmin.simplysafeconusmerapp.utilsApps.utils.UtilsConstants;

import java.util.ArrayList;
import java.util.List;

public class EntertainmentActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private GridAdapter adapter;
    private List<MainMenuDashboard> albumList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entertainment);
        getSupportActionBar().setTitle("Entertainment");

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView1);

        albumList = new ArrayList<>();
        adapter = new GridAdapter(EntertainmentActivity.this, albumList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(EntertainmentActivity.this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(5), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new UtilityActivity.RecyclerTouchListener(getApplicationContext(), recyclerView, new UtilityActivity.ClickListener() {
            @Override
            public void onClick(View view, int position) {

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
                    case 2: {
                        WebViewListsActivity.loadList = UtilsConstants.MOVIES;
                        startActivity(new Intent(EntertainmentActivity.this, WebViewListsActivity.class));
                    }
                    break;
                }
            }

            @Override
            public void onLongClick(View view, int position) {
            }
        }));

        prepareGrid();

    }
//******PREPARE GRID*******

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    private void prepareGrid() {
        String[] strList = {"Cricket", "Channels", "Movies"};
        int[] covers = new int[]{
                R.drawable.icc_cricket,
                R.drawable.icc_channels,
                R.drawable.icc_movies
        };

        for (int i = 0; i < strList.length; i++) {
            albumList.add(new MainMenuDashboard("" + i, strList[i], covers[i]));
        }

        adapter.notifyDataSetChanged();
    }

    //******PREPARE GRID*******

    //******CLICK EVENT*******
    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private UtilityActivity.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final UtilityActivity.ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }
    //******CLICK EVENT*******

}
