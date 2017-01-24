package com.example.tiuadmin.simplysafeconusmerapp.utilsApps.entertainment;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.tiuadmin.simplysafeconusmerapp.CustomAdapter.UtilityDasbhoardAdapter;
import com.example.tiuadmin.simplysafeconusmerapp.CustomAdapter.UtilityNameListAdapter;
import com.example.tiuadmin.simplysafeconusmerapp.Models.MainMenuDashboard;
import com.example.tiuadmin.simplysafeconusmerapp.R;
import com.example.tiuadmin.simplysafeconusmerapp.utilsApps.UtilsConstants;

import java.util.List;

public class EntertainmentActivity extends AppCompatActivity {
    ListView lv;
    String[] strList = {"Cricket", "Channels", "Radio", "Movies", "Recharge"};

    private RecyclerView recyclerView;
    private UtilityDasbhoardAdapter adapter;
    private List<MainMenuDashboard> albumList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entertainment);
        getSupportActionBar().setTitle("Entertainment");

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
                    case 2: {
                        WebViewListsActivity.loadList = UtilsConstants.RADIO;
                        startActivity(new Intent(EntertainmentActivity.this, WebViewListsActivity.class));
                    }
                    break;
                    case 3: {
                        WebViewListsActivity.loadList = UtilsConstants.MOVIES;
                        startActivity(new Intent(EntertainmentActivity.this, WebViewListsActivity.class));
                    }
                    break;
                    case 4: {
                        WebViewListsActivity.loadList = UtilsConstants.RECHARGE;
                        startActivity(new Intent(EntertainmentActivity.this, WebViewListsActivity.class));
                    }
                    break;
                }
            }
        });


//        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
//
//        albumList = new ArrayList<>();
//        adapter = new UtilityDasbhoardAdapter(EntertainmentActivity.this, albumList);
//
//        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(EntertainmentActivity.this, 2);
//        recyclerView.setLayoutManager(mLayoutManager);
//        recyclerView.addItemDecoration(new EntertainmentActivity.GridSpacingItemDecoration(2, dpToPx(10), true));
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.setAdapter(adapter);

        //prepareAlbums();

    }

    /**
     * Adding few albums for testing
     */
    private void prepareAlbums() {
        int[] covers = new int[]{
                R.drawable.knowledge,
                R.drawable.knowledge,
                R.drawable.knowledge,
                R.drawable.knowledge,
                R.drawable.knowledge,
        };

        MainMenuDashboard a = new MainMenuDashboard("0", strList[0], covers[0]);
        albumList.add(a);

        a = new MainMenuDashboard("1", strList[1], covers[1]);
        albumList.add(a);

//        a = new MainMenuDashboard("2",strList[2],covers[2]);
//        albumList.add(a);
//
//        a = new MainMenuDashboard("3",strList[3],covers[3]);
//        albumList.add(a);
//
//        a = new MainMenuDashboard("4",strList[4],covers[4]);
//        albumList.add(a);

//        a = new Merchant("OLA", 1, covers[5]);
//        albumList.add(a);
//
//        a = new Merchant("Loud", 11, covers[6]);
//        albumList.add(a);
//
//        a = new Merchant("Legend", 14, covers[7]);
//        albumList.add(a);
//
//        a = new Merchant("Hello", 11, covers[8]);
//        albumList.add(a);
//
//        a = new Merchant("Greatest Hits", 17, covers[9]);
//        albumList.add(a);

        adapter.notifyDataSetChanged();
    }

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}
