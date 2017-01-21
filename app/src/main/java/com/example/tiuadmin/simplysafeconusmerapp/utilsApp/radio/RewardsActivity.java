package com.example.tiuadmin.simplysafeconusmerapp.utilsApp.radio;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.tiuadmin.simplysafeconusmerapp.R;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class RewardsActivity extends AppCompatActivity {
    CirclePageIndicator indicator;
    private AnimatedExpandableListView listView;
    private ExampleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rewars);

        getSupportActionBar().setTitle("Rewards");
        listView = (AnimatedExpandableListView) findViewById(R.id.listView1);
        // In order to show animations, we need to use a custom click handler
        // for our ExpandableListView.
        listView.setOnGroupClickListener(new AnimatedExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                // We call collapseGroupWithAnimation(int) and
                // expandGroupWithAnimation(int) to animate group
                // expansion/collapse.
                if (listView.isGroupExpanded(groupPosition)) {
                    listView.collapseGroupWithAnimation(groupPosition);
                } else {
                    listView.expandGroupWithAnimation(groupPosition);
                }
                return true;
            }

        });
        prepareListData();


        mPager = (ViewPager) findViewById(R.id.pager);
        indicator = (CirclePageIndicator) findViewById(R.id.indicator);
        updateSliderImages();

    }


    //*******ADD SLIDER*****
    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private static final Integer[] IMAGES = {R.drawable.img_rewards, R.drawable.img_rewards, R.drawable.img_rewards, R.drawable.img_rewards};
    private ArrayList<Integer> ImagesArray = new ArrayList<>();
    //private static ArrayList<SetGetFoodList> setget_new_slider = new ArrayList<>();

    private void updateSliderImages() {

        for (int i = 0; i < IMAGES.length; i++)
            ImagesArray.add(IMAGES[i]);

        // ArrayList<SetGetFoodList> setget_new = new ArrayList<>();
//        try {
//            if (setget_new_slider.size() == 0) {
//                setget_new_slider = new CommonUtilsFunctions().getCurrentTabData(CommonUtilsVariables.SETGET_MENU_DATA, 0, prefManager);
//            }
//        } catch (Exception e) {
//        }

        mPager.setAdapter(new SlidingImage_Adapter(RewardsActivity.this, ImagesArray));
        //mPager.setAdapter(new SlidingImage_Adapter(getActivity(), setget_new_slider));
        indicator.setViewPager(mPager);

        final float density = getResources().getDisplayMetrics().density;
        indicator.setRadius(5 * density);
        //NUM_PAGES = IMAGES.length;
        NUM_PAGES = ImagesArray.size();

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 3000);

        // Pager listener over indicator
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;
            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int pos) {
            }
        });

    }
    //*******ADD SLIDER*****


    private void prepareListData() {

        List<GroupItem> items = new ArrayList<>();

//        for (int i = 0; i < setget.size(); i++) {
//            GroupItem itemg1 = new GroupItem();
//            itemg1.title = setget.get(i).getFloor_name();
//
//
//            String str = "";
//            ArrayList<SetGetShopTag> str_arrr = new ArrayList<>();
//            str_arrr = setget.get(i).getShop_name_tag();
//            for (int i1 = 0; i1 < str_arrr.size(); i1++) {
//                // str = str + str_arrr.get(i1) + "\n";
//                ChildItem childg1 = new ChildItem();
//                childg1.title = str_arrr.get(i1).getSname();
//                childg1.title2 = str_arrr.get(i1).getTag();
//                itemg1.items.add(childg1);
//            }
//            items.add(itemg1);
//        }
        // Populate our list with groups and it's children

        GroupItem itemg1 = new GroupItem();
        GroupItem itemg2 = new GroupItem();
        GroupItem itemg3 = new GroupItem();
        GroupItem itemg4 = new GroupItem();

        itemg1.title = "Redeem Now";
        itemg2.title = "My Points";
        itemg3.title = "Earn eDGE Points";
        itemg4.title = "Help Centre";
//
//
        ChildItem childg1 = new ChildItem();
        ChildItem childg2 = new ChildItem();
        ChildItem childg3 = new ChildItem();
        ChildItem childg4 = new ChildItem();


        childg1.title = "Reward Store\n\nRecharges & Bookings\n\nPartner Deals";
        itemg1.items.add(childg1);

        childg2.title = "My Profile\n" +
                "\n" +
                "My eDGE Transactions\n" +
                "\n" +
                "Transfer My Points";
        itemg2.items.add(childg2);

        childg3.title = "Earn Faster With\n" +
                "\n" +
                "How Can I Earn?";
        itemg3.items.add(childg3);

        childg4.title = "FAQ\n" +
                "\n" +
                "Contact Us\n" +
                "\n" +
                "Terms & Conditions\n\nAbout eDGE";
        itemg4.items.add(childg4);


        items.add(itemg1);
        items.add(itemg2);
        items.add(itemg3);
        items.add(itemg4);

        adapter = new ExampleAdapter(RewardsActivity.this);
        adapter.setData(items);
        listView.setAdapter(adapter);
    }

    private static class GroupItem {
        String title;
        List<ChildItem> items = new ArrayList<>();
    }

    private static class ChildItem {
        String title, title2;
        // String hint;
    }

    private static class ChildHolder {
        TextView title, title2;
        // TextView hint;
    }

    private static class GroupHolder {
        TextView title;
    }

    /**
     * Adapter for our list of {@link GroupItem}s.
     */
    private class ExampleAdapter extends AnimatedExpandableListView.AnimatedExpandableListAdapter {
        private LayoutInflater inflater;

        private List<GroupItem> items;

        public ExampleAdapter(Context context) {
            inflater = LayoutInflater.from(context);
        }

        public void setData(List<GroupItem> items) {
            this.items = items;
        }

        @Override
        public ChildItem getChild(int groupPosition, int childPosition) {
            return items.get(groupPosition).items.get(childPosition);
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public View getRealChildView(int groupPosition, int childPosition,
                                     boolean isLastChild, View convertView, ViewGroup parent) {
            ChildHolder holder;
            ChildItem item = getChild(groupPosition, childPosition);
            if (convertView == null) {
                holder = new ChildHolder();
                convertView = inflater.inflate(R.layout.list_item, parent,
                        false);
                holder.title = (TextView) convertView
                        .findViewById(R.id.textTitle);
                holder.title2 = (TextView) convertView
                        .findViewById(R.id.textTitle2);
                convertView.setTag(holder);
            } else {
                holder = (ChildHolder) convertView.getTag();
            }

            holder.title.setText(item.title);
            holder.title2.setText(item.title2);

            return convertView;
        }

        @Override
        public int getRealChildrenCount(int groupPosition) {
            return items.get(groupPosition).items.size();
        }

        @Override
        public GroupItem getGroup(int groupPosition) {
            return items.get(groupPosition);
        }

        @Override
        public int getGroupCount() {
            return items.size();
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded,
                                 View convertView, ViewGroup parent) {
            GroupHolder holder;
            GroupItem item = getGroup(groupPosition);
            if (convertView == null) {
                holder = new GroupHolder();
                convertView = inflater.inflate(R.layout.group_item, parent,
                        false);
                holder.title = (TextView) convertView
                        .findViewById(R.id.textTitle);
                convertView.setTag(holder);
            } else {
                holder = (GroupHolder) convertView.getTag();
            }

            holder.title.setText(item.title);

            return convertView;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public boolean isChildSelectable(int arg0, int arg1) {
            return true;
        }

    }
    //*****************exp list end***************

}
