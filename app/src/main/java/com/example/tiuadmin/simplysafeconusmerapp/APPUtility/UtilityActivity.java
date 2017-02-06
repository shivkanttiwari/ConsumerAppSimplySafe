package com.example.tiuadmin.simplysafeconusmerapp.APPUtility;

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

import com.example.tiuadmin.simplysafeconusmerapp.Models.MainMenuDashboard;
import com.example.tiuadmin.simplysafeconusmerapp.R;
import com.example.tiuadmin.simplysafeconusmerapp.utilsApps.entertainment.EntertainmentActivity;
import com.example.tiuadmin.simplysafeconusmerapp.utilsApps.entertainment.WebViewListsActivity;
import com.example.tiuadmin.simplysafeconusmerapp.utilsApps.utils.UtilsConstants;
import com.simplemobiletools.draw.activities.MainActivity;

import java.util.ArrayList;
import java.util.List;

import cz.martykan.forecastie.activities.SplashActivity;

public class UtilityActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private GridAdapter adapter;
    private List<MainMenuDashboard> albumList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utility);
        getSupportActionBar().setTitle("Utilities");

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        albumList = new ArrayList<>();
        adapter = new GridAdapter(UtilityActivity.this, albumList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(UtilityActivity.this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(UtilsConstants.GRID_PADDING), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {

                switch (position) {
                    case 0: {
                        Intent i = new Intent();
                        i.setClassName("com.android.calculator2",
                                "com.android.calculator2.Calculator");
                        startActivity(i);
                    }
                    break;
                    case 1: {
                        startActivity(new Intent(UtilityActivity.this, SplashActivity.class));
                    }
                    break;
                    case 2: {

                    }
                    break;
                    case 3: {
                        startActivity(new Intent(UtilityActivity.this, EntertainmentActivity.class));
                    }
                    break;
                    case 4: {
                        WebViewListsActivity.loadList = UtilsConstants.RECHARGE;
                        startActivity(new Intent(UtilityActivity.this, WebViewListsActivity.class));
                    }
                    break;

                    case 5: {

                    }
                    break;
                    case 6: {
                        startActivity(new Intent(UtilityActivity.this, MainActivity.class));
                    }
                    break;
                    case 7: {
                        //startActivity(new Intent(UtilityActivity.this, main.class));
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
        String[] strList = {"Calculator", "Weather", "Games", "Entertainment", "Recharge", "Knowledge","Simply Draw","Camera"};
        int[] covers = new int[]{
                R.drawable.calculator,
                R.drawable.icc_weather,
                R.drawable.gamepad,
                R.drawable.entertainment,
                R.drawable.knowledge,
                R.drawable.knowledge,
                R.drawable.knowledge,
                R.drawable.knowledge
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
        private ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener) {
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
