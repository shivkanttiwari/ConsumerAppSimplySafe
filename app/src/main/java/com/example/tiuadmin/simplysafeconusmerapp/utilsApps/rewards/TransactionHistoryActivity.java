package com.example.tiuadmin.simplysafeconusmerapp.utilsApps.rewards;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.tiuadmin.simplysafeconusmerapp.R;

import java.util.ArrayList;
import java.util.List;

public class TransactionHistoryActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_history);
        initializeToolbar();

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        setupTab();
    }

    void initializeToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setTitle("Transaction History");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case android.R.id.home:
                finish(); // close this activity and return to preview activity (if there is any)
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    // *************** Setup Tab *************** //

    void setupTab() {
        tabLayout.setSelectedTabIndicatorColor(Color.WHITE);
        tabLayout.setTabTextColors(Color.LTGRAY, Color.WHITE);

        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });

        setupViewPager(viewPager);
        //viewPager.setCurrentItem(currentTabPosition);
    }

    private void setupViewPager(ViewPager viewPager) {
        String[] arrTabs = {"Earns", "Redemption"};

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new TransactionHistoryEarnsFragment(), arrTabs[0]);
        adapter.addFragment(new TransactionHistoryRedemptionFragment(), arrTabs[1]);

        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

//        @Override
//        public Fragment getItem(int position) {
//            //return mFragmentList.get(position);
//            return ApprovalsTab1Fragment.newInstance(position);
//        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
    //***************Setup Tab************

}
