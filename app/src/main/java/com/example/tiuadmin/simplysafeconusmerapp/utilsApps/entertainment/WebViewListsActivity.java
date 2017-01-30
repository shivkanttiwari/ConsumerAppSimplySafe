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

public class WebViewListsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private GridAdapter adapter;
    private List<MainMenuDashboard> albumList;

    public static int loadList;
    String[] loadedListLinks;

    //********PREPARE LIST*******
    int[] iconsCricket = new int[]{R.drawable.icc_cricket, R.drawable.icc_cricket, R.drawable.icc_cricket};
    String[] strCricket = {"ICC", "ESPN", "Cricbuzz"};
    String[] strCricketLinks = {"http://www.icc-cricket.com/", "http://m.espncricinfo.com/", "http://www.m.cricbuzz.com/"};

    int[] iconsChannels = new int[]{R.drawable.icc_channels, R.drawable.icc_channels, R.drawable.icc_channels};
    String[] strChannels = {"Hot Star", "sonyLIV", "EROS NOW"};
    String[] strChannelsLinks = {"http://www.hotstar.com/", "http://www.sonyliv.com/", "http://erosnow.com/welcome"};

    //*****************
    int[] iconsMusicRadio = new int[]{R.drawable.icc_music, R.drawable.icc_music,
            R.drawable.icc_radio, R.drawable.icc_radio,
            R.drawable.icc_radio, R.drawable.icc_radio, R.drawable.icc_radio};
    String[] strMusicRadio = {"Gaana", "Saavan", "Internet Radio", "TuneIn", "Streema", "TuneIn City", "Radio4fm"};
    String[] strMusicRadioLinks = {"http://gaana.com/", "http://www.saavn.com/",
            "https://www.internet-radio.com/stations/bollywood/",
            "http://tunein.com/radio/Bollywood-g2762/", "http://streema.com/radios/genre/Bollywood/",
            "http://tunein.com/radio/City-1016-FM-s14329/", "http://www.radio4fm.com/player/"};

    int[] iconsMusic = new int[]{R.drawable.icc_music, R.drawable.icc_music};
    String[] strMusic = {"Gaana", "Saavan"};
    String[] strMusicLinks = {"http://gaana.com/",
            "http://www.saavn.com/"};

    int[] iconsRadios = new int[]{R.drawable.icc_radio, R.drawable.icc_radio,
            R.drawable.icc_radio, R.drawable.icc_radio, R.drawable.icc_radio};
    String[] strRadios = {"Internet Radio", "TuneIn", "Streema", "TuneIn City", "Radio4fm"};
    String[] strRadiosLinks = {"https://www.internet-radio.com/stations/bollywood/",
            "http://tunein.com/radio/Bollywood-g2762/", "http://streema.com/radios/genre/Bollywood/",
            "http://tunein.com/radio/City-1016-FM-s14329/", "http://www.radio4fm.com/player/"};
    //*****************

    int[] iconsMovies = new int[]{R.drawable.icc_movies, R.drawable.icc_movies, R.drawable.icc_movies};
    String[] strMovies = {"Snag films", "Eros Now", "BoxTv"};
    String[] strMoviesLinks = {"http://www.snagfilms.com/categories/", "http://erosnow.com/",
            "http://www.boxtv.com/movies/"};

    int[] iconsRecharges = new int[]{R.drawable.knowledge, R.drawable.knowledge,
            R.drawable.knowledge, R.drawable.knowledge};
    String[] strRecharges = {"Freecharge", "Paytm", "Mobikwik", "RechargeItNow"};
    String[] strRechargesLinks = {"https://www.freecharge.in/mobile/", "https://paytm.com/recharge",
            "https://m.mobikwik.com/", "http://m.rechargeitnow.com/"};

    //********PREPARE LIST*******


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_lists);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView1);

        albumList = new ArrayList<>();
        adapter = new GridAdapter(WebViewListsActivity.this, albumList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(WebViewListsActivity.this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(UtilsConstants.GRID_PADDING), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new UtilityActivity.RecyclerTouchListener(getApplicationContext(), recyclerView, new UtilityActivity.ClickListener() {
            @Override
            public void onClick(View view, int position) {

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
                        RadioWebViewActivity.loadUrl = loadedListLinks[position];
                        startActivity(new Intent(WebViewListsActivity.this, RadioWebViewActivity.class));
                    }
                    break;
                    case 4: {
                        RadioWebViewActivity.loadUrl = loadedListLinks[position];
                        startActivity(new Intent(WebViewListsActivity.this, RadioWebViewActivity.class));
                    }
                    break;
                    case 5: {
                        RadioWebViewActivity.loadUrl = loadedListLinks[position];
                        startActivity(new Intent(WebViewListsActivity.this, RadioWebViewActivity.class));
                    }
                    break;
                    case 6: {
                        RadioWebViewActivity.loadUrl = loadedListLinks[position];
                        startActivity(new Intent(WebViewListsActivity.this, RadioWebViewActivity.class));
                    }
                    break;
                    case 7: {
                        RadioWebViewActivity.loadUrl = loadedListLinks[position];
                        startActivity(new Intent(WebViewListsActivity.this, RadioWebViewActivity.class));
                    }
                    break;
                }
            }

            @Override
            public void onLongClick(View view, int position) {
            }
        }));


        switch (loadList) {
            case UtilsConstants.CRICKET: {
                prepareGrid(strCricket, iconsCricket);
                loadedListLinks = strCricketLinks;
            }
            break;
            case UtilsConstants.CHANNELS: {
                prepareGrid(strChannels, iconsChannels);
                loadedListLinks = strChannelsLinks;
            }
            break;
            case UtilsConstants.RADIO: {
                prepareGrid(strRadios, iconsRadios);
                loadedListLinks = strRadiosLinks;
            }
            break;
            case UtilsConstants.MOVIES: {
                prepareGrid(strMovies, iconsMovies);
                loadedListLinks = strMoviesLinks;
            }
            break;
            case UtilsConstants.RECHARGE: {
                prepareGrid(strRecharges, iconsRecharges);
                loadedListLinks = strRechargesLinks;
            }
            break;
            case UtilsConstants.MUSIC: {
                prepareGrid(strMusic, iconsMusic);
                loadedListLinks = strMusicLinks;
            }
            break;
            case UtilsConstants.MUSIC_RADIO: {
                prepareGrid(strMusicRadio, iconsMusicRadio);
                loadedListLinks = strMusicRadioLinks;
            }
            break;
        }
    }

    //******PREPARE GRID*******

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    private void prepareGrid(String[] strList, int[] icons) {

        for (int i = 0; i < strList.length; i++) {
            albumList.add(new MainMenuDashboard("" + i, strList[i], icons[i]));
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
