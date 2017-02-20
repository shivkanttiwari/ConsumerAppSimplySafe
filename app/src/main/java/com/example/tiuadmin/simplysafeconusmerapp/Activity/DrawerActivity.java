package com.example.tiuadmin.simplysafeconusmerapp.Activity;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tiuadmin.simplysafeconusmerapp.Fragments.DashboardFragment;
import com.example.tiuadmin.simplysafeconusmerapp.Fragments.MainActivity;
import com.example.tiuadmin.simplysafeconusmerapp.Models.Merchant;
import com.example.tiuadmin.simplysafeconusmerapp.R;
import com.example.tiuadmin.simplysafeconusmerapp.User.ChangePasswordActivity;
import com.example.tiuadmin.simplysafeconusmerapp.User.UserProfileActivity;
import com.example.tiuadmin.simplysafeconusmerapp.Utility.Const;
import com.example.tiuadmin.simplysafeconusmerapp.Utility.PrefManager;
import com.example.tiuadmin.simplysafeconusmerapp.Webservices.WebService;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.tiuadmin.simplysafeconusmerapp.R.id.drawer;

public class DrawerActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    NavigationView navigationView;

    TextView navigationDrawerTextview;
    ImageView navigationDrawerImageview;
    PrefManager prefManager;
    TextView tv_email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_main);
        prefManager=new PrefManager(this);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initNavigationDrawer();
        makeUserDetailRequest();
       // new AsyncTaskWS().execute();
      //  initCollapsingToolbar();



//        try {
//            Glide.with(this).load(R.drawable.cover).into((ImageView) findViewById(R.id.backdrop));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }



    public void initNavigationDrawer() {

         navigationView = (NavigationView)findViewById(R.id.navigation_view);



        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                int id = menuItem.getItemId();
                Fragment fragment=null;
                FragmentManager fragmentManager = getSupportFragmentManager();
                switch (id){
                    case R.id.home:
                        Toast.makeText(getApplicationContext(),"Home",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawers();
                        break;

                    case R.id.ChangePassword:
                       // Toast.makeText(getApplicationContext(),"Trash",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(DrawerActivity.this, ChangePasswordActivity.class));
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.contactus:
                        Toast.makeText(getApplicationContext(),"Settings",Toast.LENGTH_SHORT).show();
                        fragment=new DashboardFragment();
                        getFragmentManager().beginTransaction()
                                .replace(R.id.fragment_place, fragment)
                                .commit();
                        break;
                    case R.id.Rewards:
                        Toast.makeText(getApplicationContext(),"Trash",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.logout:

                        prefManager.setToken("");
                        startActivity(new Intent(DrawerActivity.this, MainActivity.class));
                        finish();

                }
                return true;
            }
        });
        View header = navigationView.getHeaderView(0);
         tv_email = (TextView)header.findViewById(R.id.tv_email);
        tv_email.setText(Const.USER_EMAIL);



        navigationDrawerImageview = (ImageView) header.findViewById(R.id.navigationdrawerimageuser);

        navigationDrawerImageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                drawerLayout.closeDrawers();
                startActivity(new Intent(DrawerActivity.this, UserProfileActivity.class));

            }
        });
        drawerLayout = (DrawerLayout)findViewById(drawer);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close){

            @Override
            public void onDrawerClosed(View v){
                super.onDrawerClosed(v);
            }

            @Override
            public void onDrawerOpened(View v) {
                super.onDrawerOpened(v);
            }
        };
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }


    //******************************CartView Methods***********

    /**
     * Initializing collapsing toolbar
     * Will show and hide the toolbar title on scroll
     */
//    private void initCollapsingToolbar() {
//        final CollapsingToolbarLayout collapsingToolbar =
//                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
//        collapsingToolbar.setTitle(" ");
//        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
//        appBarLayout.setExpanded(true);
//
//        // hiding & showing the title when toolbar expanded & collapsed
//        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
//            boolean isShow = false;
//            int scrollRange = -1;
//
//            @Override
//            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
//                if (scrollRange == -1) {
//                    scrollRange = appBarLayout.getTotalScrollRange();
//                }
//                if (scrollRange + verticalOffset == 0) {
//                    collapsingToolbar.setTitle(getString(R.string.app_name));
//                    isShow = true;
//                } else if (isShow) {
//                    collapsingToolbar.setTitle(" ");
//                    isShow = false;
//                }
//            }
//        });
//    }

    /**
     * Making json object request
     */
    private void makeUserDetailRequest() {
        //new GeneralFunction().showProgressDialog(this);
        String res = null;
        String responseCode = null;
        String returnResponse = null;
        try {

            String url = "http://52.66.101.233/Customer-Backend/public/api/user";
          /*  JSONObject jsonrequest = new JSONObject();
            jsonrequest.put("phone", phone);
            jsonrequest.put("sspin", password);
*/



            WebService web = new WebService();
            res = web.getWithHeader(url,prefManager.getToken());
            Log.d(res, res);


            if (res != null && res.length()>0) {
                JSONObject json = new JSONObject(res);
                if (json != null) {


                    Const.USER_ID=json.getString("id");
                    Const.USER_NAME=json.getString("name");
                    Const.USER_MOBILENUMBER=json.getString("phone");
                    Const.USER_EMAIL=json.getString("email");
                    String address=json.getString("address");


                    try {
                        Picasso.with(DrawerActivity.this)

                                .load("http://52.66.101.233/Customer-Backend/public/api/v1/customer/image/200/200/"+Const.USER_ID).placeholder(R.drawable.user)
                                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                                .networkPolicy(NetworkPolicy.NO_CACHE)
                                .into(navigationDrawerImageview);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                  /*  Picasso
                            .with(this)
                            .load("http://52.66.101.233/Customer-Backend/public/api/v1/customer/image/200/200/"+Const.USER_ID)
                            .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                            .networkPolicy(NetworkPolicy.NO_CACHE)
                            .into(navigationDrawerImageview);*/


                    tv_email.setText(Const.USER_EMAIL);
                   // Picasso.with(this).load("http://52.66.101.233/Customer-Backend/public/api/v1/customer/image/200/200/"+Const.USER_ID).into(navigationDrawerImageview);

                    Bitmap photo = BitmapFactory.decodeResource(this.getResources(), R.drawable.pendingimage);
                    //new GeneralFunction().hideProgressDialog();
                }
            }
            else {
                Toast.makeText(getApplicationContext(), "Unable to get user information.", Toast.LENGTH_SHORT)
                        .show();
                //new GeneralFunction().hideProgressDialog();
            }

        } catch (Exception e) {
            //new GeneralFunction().hideProgressDialog();
           // Toast.makeText(getApplicationContext(), "Please provide valid mobile number  and password.", Toast.LENGTH_SHORT)
             //       .show();
            e.printStackTrace();
        }
    }




    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }


    //******************webservice********
    private ProgressDialog progressDialog2 = null;
    String username;
    ArrayList<Merchant> setget = new ArrayList<>();
    String fname, lname, strGender;

    // To use the AsyncTask, it must be subclassed
    private class AsyncTaskWS extends AsyncTask<Void, Integer, Void> {
        // Before running code in separate thread
        @Override
        protected void onPreExecute() {
            // Create a new progress dialog
            progressDialog2 = new ProgressDialog(DrawerActivity.this);
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
                makeUserDetailRequest();
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

        }
    }
}
