package com.example.tiuadmin.simplysafeconusmerapp.Merchant;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tiuadmin.simplysafeconusmerapp.CustomAdapter.MerchantViewAdapter;
import com.example.tiuadmin.simplysafeconusmerapp.JSON.JSONPARSER;
import com.example.tiuadmin.simplysafeconusmerapp.Models.Merchant;
import com.example.tiuadmin.simplysafeconusmerapp.R;
import com.example.tiuadmin.simplysafeconusmerapp.Utility.Const;
import com.example.tiuadmin.simplysafeconusmerapp.Utility.GeneralFunction;
import com.example.tiuadmin.simplysafeconusmerapp.Utility.Utils;
import com.example.tiuadmin.simplysafeconusmerapp.Webservices.WebService;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MerchantActivity extends AppCompatActivity {
    private List<String> list = new ArrayList<String>();
    MerchantViewAdapter mAdapter;



    private Toolbar toolbar;


    private StaggeredGridLayoutManager mStaggeredLayoutManager;

    private boolean isListView;
    private Menu menu;
   // ArrayList<String> merchantArraay;
    RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant);


         mRecyclerView = (RecyclerView) findViewById(R.id.list);

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
        mAdapter = new MerchantViewAdapter(MerchantActivity.this,Const.MERCHANT_DATA);
        mRecyclerView.setAdapter(mAdapter);

      //  mAdapter.setOnItemClickListener(onItemClickListener);

        isListView = true;


        FloatingActionButton myFab = (FloatingActionButton) findViewById(R.id.fab_add_merchant);
        myFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(),"merchant add",Toast.LENGTH_SHORT).show();


                final Dialog dialog = new Dialog(MerchantActivity.this);

                //setting custom layout to dialog
                dialog.setContentView(R.layout.addcustomerdialog);
                dialog.setTitle("Add Merchant");

                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
              //  lp.height = WindowManager.LayoutParams.MATCH_PARENT;

                dialog.getWindow().setAttributes(lp);

                //adding text dynamically
               // TextView txt = (TextView) dialog.findViewById(R.id.textView);
               // txt.setText("How ");

               // ImageView image = (ImageView)dialog.findViewById(R.id.image);
               // image.setImageDrawable(getResources().getDrawable(android.R.drawable.ic_dialog_info));

                //adding button click event

                final EditText edMerchantMobilenumber=(EditText)dialog.findViewById(R.id.edMerchantMobilenumber);

                edMerchantMobilenumber.setText("8087448286");
                Button addMerchant = (Button) dialog.findViewById(R.id.btn_addmerchant);
                addMerchant.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String ConsumerMobilenumber=edMerchantMobilenumber.getText().toString().trim();
                        if(ConsumerMobilenumber.length()>0)
                        {
                            makeAddMerchantRequest(edMerchantMobilenumber.getText().toString().trim());


                            //    merchantArraay.add(0,"New Merchant");
                            mAdapter.notifyDataSetChanged();
                            sendRequest();
                            dialog.dismiss();
                            // MerchantViewAdapter layoutManager = new MerchantViewAdapter(getActivity());
                            // mRecyclerView.setLayoutManager(layoutManager);
                            mRecyclerView.smoothScrollToPosition(0);
                        }
                        else {
                            Toast.makeText(MerchantActivity.this,"Please provide valid mobile number",Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                dialog.show();
            }
        });



    }




    public void arrayList(){
        for (int i = 0; i< 20; i++){
            list.add("This is row of number "+ i);
        }
    }


    /**
     * Making json object request
     */
    private void makeAddMerchantRequest(String ConsumerMobilenumber) {
        new GeneralFunction().showProgressDialog(this);
        String res = null;
        String responseCode = null;
        String returnResponse = null;
        try {

            String url = "http://simplypos.in/api/merchant-api.php?action=marchantRequest&merchant="+ConsumerMobilenumber+"&customerName="+Const.USER_NAME+"&customerMobile="+Const.USER_MOBILENUMBER;
          /*  JSONObject jsonrequest = new JSONObject();
            jsonrequest.put("phone", phone);
            jsonrequest.put("sspin", password);
*/


            WebService web = new WebService();
            res = web.getWithoutHeader(url);
            Log.d(res, res);


            if (res != null && res.length()>0) {
                JSONObject json = new JSONObject(res);
                if (json != null) {

                    String merhantStatus=json.getString("status");
                    JSONObject jsonuserdata=new JSONObject();

                    jsonuserdata=json.getJSONObject("res");

                    String merchantID=jsonuserdata.getString("id");
                    String merchantName=jsonuserdata.getString("name");
                    String merchantMobilenumber=jsonuserdata.getString("phone");
                    String merchantPOSURL=jsonuserdata.getString("pos_url");
                    String merchantType=jsonuserdata.getString("type");

                    Const.MERCHANT_DATA.add(new Merchant(merchantID,merchantName,merchantMobilenumber,merchantPOSURL,merchantType,merhantStatus));





                   /* String logintoken = json.getString("access_token");

                    Const.LOGIN_TOKEN=logintoken;
                    Const.TOKEN_WITH_BEARER+=Const.LOGIN_TOKEN;
                    Log.d("token",Const.TOKEN_WITH_BEARER);
                    startActivity(new Intent(DrawerActivity.this, DrawerActivity.class));

                   finish();*/
                    new GeneralFunction().hideProgressDialog();
                }
            }
            else {
                Toast.makeText(getApplicationContext(), "Unable to get user information.", Toast.LENGTH_SHORT)
                        .show();
                new GeneralFunction().hideProgressDialog();
            }

        } catch (Exception e) {
            new GeneralFunction().hideProgressDialog();
            Toast.makeText(getApplicationContext(), "Please provide valid mobile number  and password.", Toast.LENGTH_SHORT)
                    .show();
            e.printStackTrace();
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

    private void sendRequest(){

        StringRequest stringRequest = new StringRequest(Utils.JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        showJSON(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MerchantActivity.this,error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String json){
        JSONPARSER pj = new JSONPARSER(json);
        pj.parseJSON();
       // CustomList cl = new CustomList(this, ParseJSON.ids,ParseJSON.names,ParseJSON.emails);
      //  listView.setAdapter(cl);
    }
}
