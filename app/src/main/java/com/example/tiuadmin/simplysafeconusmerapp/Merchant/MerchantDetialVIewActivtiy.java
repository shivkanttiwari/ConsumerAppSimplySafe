package com.example.tiuadmin.simplysafeconusmerapp.Merchant;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tiuadmin.simplysafeconusmerapp.Fragments.MerchantOffersFragment;
import com.example.tiuadmin.simplysafeconusmerapp.Fragments.MerchantProfileFragment;
import com.example.tiuadmin.simplysafeconusmerapp.Fragments.MerchantTransactionMessage;
import com.example.tiuadmin.simplysafeconusmerapp.Models.Merchant;
import com.example.tiuadmin.simplysafeconusmerapp.Models.MerchantDetailMessageModel;
import com.example.tiuadmin.simplysafeconusmerapp.Models.MerchantMessageModel;
import com.example.tiuadmin.simplysafeconusmerapp.Models.MerchantTransactionModel;
import com.example.tiuadmin.simplysafeconusmerapp.R;
import com.example.tiuadmin.simplysafeconusmerapp.Utility.Const;
import com.example.tiuadmin.simplysafeconusmerapp.Utility.PrefManager;
import com.example.tiuadmin.simplysafeconusmerapp.Webservices.WebService;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MerchantDetialVIewActivtiy extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private String merchant_id;
    PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_tabs);
        prefManager=new PrefManager(getApplicationContext());
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);


        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);



        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                merchant_id= null;
            } else {
                merchant_id= extras.getString("merchant_id");
            }
        } else {
            merchant_id= (String) savedInstanceState.getSerializable("merchant_id");
        }

        new AsyncTaskAddMerchant().execute();
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new MerchantProfileFragment(), "Merchant Profile");
        adapter.addFragment(new MerchantOffersFragment(), "Promotional");
        adapter.addFragment(new MerchantTransactionMessage(), "Transaction");

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {
            case android.R.id.home:




                finish();

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    //******************webservice********
    private ProgressDialog progressDialog2 = null;
    String username;
    ArrayList<Merchant> setget = new ArrayList<>();


    // To use the AsyncTask, it must be subclassed
    private class AsyncTaskAddMerchant extends AsyncTask<Void, Integer, Void> {
        // Before running code in separate thread
        @Override
        protected void onPreExecute() {
            // Create a new progress dialog
            progressDialog2 = new ProgressDialog(MerchantDetialVIewActivtiy.this);
            progressDialog2.getWindow().setBackgroundDrawableResource(R.color.colorPrimaryDark);
            progressDialog2.getWindow().setGravity(Gravity.CENTER);
            // Set the progress dialog to display a horizontal progress bar
            progressDialog2.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            // Set the dialog title to 'Loading...'
            // Set the dialog message to 'Loading application View, please
            // wait...'
            progressDialog2.setMessage("Pleaes Wait...");
            // This dialog can't be canceled by pressing the back key
            progressDialog2.setCancelable(false);
            // This dialog isn't indeterminate
            progressDialog2.setIndeterminate(false);
            // Display the progress dialog
            progressDialog2.show();
        }

        // The code to be executed in a background thread.
        @Override
        protected Void doInBackground(Void... params) {
            try {


                makeGetMerchantDetailRequest();
            } catch (Exception e) {
                progressDialog2.dismiss();


                e.printStackTrace();
            }
            return null;
        }

        // after executing the code in the thread
        @Override
        protected void onPostExecute(Void result) {
            progressDialog2.dismiss();

            setupViewPager(viewPager);
           // mAdapter.notifyDataSetChanged();
            // sendRequest();

            // MerchantViewAdapter layoutManager = new MerchantViewAdapter(getActivity());
            // mRecyclerView.setLayoutManager(layoutManager);
           // mRecyclerView.smoothScrollToPosition(0);
        }
    }

    /**
     * Making json object request
     */
    private void makeGetMerchantDetailRequest() {
        // llProgress.setVisibility(View.VISIBLE);
        String res = null;
        String responseCode = null;
        String returnResponse = null;
        try {

            String url = "http://52.66.101.233/Customer-Backend/public/api/v1/customer/merchant/"+Const.MerchantID_Selected_For_Detial;
          /*  JSONObject jsonrequest = new JSONObject();
            jsonrequest.put("phone", phone);
            jsonrequest.put("sspin", password);
*/
            //Const.MERCHANT_DATA.add(0,new Merchant("1","shivknat","9096572182","www.goole.com","3","pending"));

            WebService web = new WebService();
            res = web.getWithHeader(url,prefManager.getToken());
            Log.d(res, res);


            if (res != null && res.length()>0) {
                JSONObject json = new JSONObject(res);
                if (json != null) {



                    String merhantStatus=json.getString("status");
                    JSONObject jsonuserdata=new JSONObject();

                    jsonuserdata=json.getJSONObject("data");

                    String id=jsonuserdata.getString("id");
                    String customer_id=jsonuserdata.getString("customer_id");

                    String merchant_id=jsonuserdata.getString("merchant_id");
                    String merchant_name=jsonuserdata.getString("merchant_name");
                    String merchant_email=jsonuserdata.getString("merchant_email");
                    String merchant_phone=jsonuserdata.getString("merchant_phone");
                    String merchant_address=jsonuserdata.getString("merchant_address");
                    String merchant_type=jsonuserdata.getString("merchant_type");
                    String merchant_pos_name=jsonuserdata.getString("merchant_pos_name");
                    String merchant_message=jsonuserdata.getString("merchant_message");
                    String merchant_pos_url=jsonuserdata.getString("merchant_pos_url");
                    String merchant_pos_create_at=jsonuserdata.getString("merchant_pos_create_at");
                    String merchant_pos_demo_expiry_at=jsonuserdata.getString("merchant_pos_demo_expiry_at");
                    String merchant_payment_status=jsonuserdata.getString("merchant_payment_status");
                    String merchant_status=jsonuserdata.getString("merchant_status");
                    String merchant_password=jsonuserdata.getString("merchant_password");
                    String created_at=jsonuserdata.getString("created_at");
                    String updated_at=jsonuserdata.getString("updated_at");




                    Merchant MerchantProfile=new Merchant(id,customer_id,merchant_id,merchant_name,merchant_phone,merchant_pos_url,merchant_type,merhantStatus
                            ,merchant_email,merchant_address,merchant_pos_name,merchant_message,merchant_pos_create_at,merchant_pos_demo_expiry_at,merchant_payment_status);

                    JSONArray merchantPromotionalMessage = jsonuserdata.getJSONArray("messages");

                    ArrayList<MerchantMessageModel> merhantMessageModelArray=new ArrayList<>();

                    ArrayList<MerchantTransactionModel>merchantTransactionModelArray=new ArrayList<>();
                    for (int i=0; i<merchantPromotionalMessage.length(); i++) {
                        JSONObject messageObject = merchantPromotionalMessage.getJSONObject(i);
                        String messageID = messageObject.getString("id");
                        String merchant_login_id = messageObject.getString("merchant_id");
                        String heading = messageObject.getString("heading");
                        String message = messageObject.getString("message");
                        String imageUrl = messageObject.getString("imageUrl");
                        String type = messageObject.getString("type");


                        MerchantMessageModel merchantMessageModel;
                        MerchantTransactionModel merhantTransactionModel;
                        if(type.equalsIgnoreCase("promotional"))
                        {
                            merchantMessageModel=new MerchantMessageModel(messageID,merchant_login_id,heading,message,url);
                            merhantMessageModelArray.add(merchantMessageModel);
                        }
                        else {
                            merhantTransactionModel=new MerchantTransactionModel(messageID,merchant_login_id,heading,message,url);
                            merchantTransactionModelArray.add(merhantTransactionModel);
                        }

                    }

                    if(Const.merchantDetailMessageModels.size()>0)
                        Const.merchantDetailMessageModels.clear();
                    Const.merchantDetailMessageModels.add(new MerchantDetailMessageModel(MerchantProfile,merhantMessageModelArray,merchantTransactionModelArray));






                    //   Collections.reverse(Const.MERCHANT_DATA);



                   /* String logintoken = json.getString("access_token");

                    Const.LOGIN_TOKEN=logintoken;
                    Const.TOKEN_WITH_BEARER+=Const.LOGIN_TOKEN;
                    Log.d("token",Const.TOKEN_WITH_BEARER);
                    startActivity(new Intent(DrawerActivity.this, DrawerActivity.class));

                   finish();*/

                }
            }
            else {
                Toast.makeText(getApplicationContext(), "Unable to get user information.", Toast.LENGTH_SHORT)
                        .show();

            }

        } catch (Exception e) {

            Toast.makeText(getApplicationContext(), "Please provide valid mobile number.", Toast.LENGTH_SHORT)
                    .show();
            e.printStackTrace();
        }
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View view = getCurrentFocus();
        if (view != null && (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) && view instanceof EditText && !view.getClass().getName().startsWith("android.webkit.")) {
            int scrcoords[] = new int[2];
            view.getLocationOnScreen(scrcoords);
            float x = ev.getRawX() + view.getLeft() - scrcoords[0];
            float y = ev.getRawY() + view.getTop() - scrcoords[1];
            if (x < view.getLeft() || x > view.getRight() || y < view.getTop() || y > view.getBottom())
                ((InputMethodManager)this.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow((this.getWindow().getDecorView().getApplicationWindowToken()), 0);
        }
        return super.dispatchTouchEvent(ev);
    }
}
