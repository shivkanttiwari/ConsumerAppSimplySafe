package com.example.tiuadmin.simplysafeconusmerapp.Merchant;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
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
import com.google.zxing.Result;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

import static com.example.tiuadmin.simplysafeconusmerapp.R.id.edMerchantMobilenumber;


public class MerchantActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler{
    private List<String> list = new ArrayList<String>();
    private ZXingScannerView mScannerView;
    private static final int REQUEST_WRITE_PERMISSION = 20;
    MerchantViewAdapter mAdapter;

TextView txt_noMerchant;

    private Toolbar toolbar;


    private StaggeredGridLayoutManager mStaggeredLayoutManager;
   // private LinearLayout llProgress;

    private boolean isListView;
    private Menu menu;
    EditText edMerchantMobilenumber;
   // ArrayList<String> merchantArraay;
   ListView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant);

        txt_noMerchant=(TextView)findViewById(R.id.txt_noMerchant);
       // llProgress = (LinearLayout) findViewById(R.id.llProgress);

         mRecyclerView = (ListView) findViewById(R.id.list);

       // mStaggeredLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
       // mRecyclerView.setHasFixedSize(false);
       // mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        makeGetMerchantConsumerDatabaseRequest();
//
        //mAdapter = new MerchantViewAdapter(list,this);
       // mRecyclerView.setAdapter(mAdapter);



        toolbar = (Toolbar) findViewById(R.layout.toolbar);
        setUpActionBar();




        mStaggeredLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);

        //mRecyclerView = (RecyclerView) findViewById(R.id.list);
        //mRecyclerView.setLayoutManager(mStaggeredLayoutManager);

      //  mRecyclerView.setHasFixedSize(true); //Data size is fixed - improves performance
        if(Const.MERCHANT_DATA.size()>0)
        {
            txt_noMerchant.setVisibility(View.GONE);
        }
        else {
            txt_noMerchant.setVisibility(View.VISIBLE);
        }
        mAdapter = new MerchantViewAdapter(MerchantActivity.this,Const.MERCHANT_DATA);
        mRecyclerView.setAdapter(mAdapter);

      //  mAdapter.setOnItemClickListener(onItemClickListener);

        isListView = true;


        FloatingActionButton myFab = (FloatingActionButton) findViewById(R.id.fab_add_merchant);
        myFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mScannerView = new ZXingScannerView(MerchantActivity.this);
                Toast.makeText(getApplicationContext(),"merchant add",Toast.LENGTH_SHORT).show();


                final Dialog dialog = new Dialog(MerchantActivity.this);
                dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation_2;

                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                //setting custom layout to dialog
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
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

                TextView txtviewqrcode=(TextView)dialog.findViewById(R.id.txtviewqrcode);

                txtviewqrcode.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //Toast.makeText(getApplicationContext(),"TOast",Toast.LENGTH_LONG).show();

                        startActivity(new Intent(MerchantActivity.this,QRCodeScanActivity.class));
                       /* if (ContextCompat.checkSelfPermission(MerchantActivity.this, Manifest.permission.CAMERA)
                                != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(MerchantActivity.this,
                                    new String[]{Manifest.permission.CAMERA}, REQUEST_WRITE_PERMISSION);
                        } else {

                            setContentView(mScannerView);

                            mScannerView.setResultHandler(MerchantActivity.this); // Reg
                            mScannerView.startCamera();
                        }*/
                        // Start camera
                        dialog.dismiss();
                    }
                });
                  edMerchantMobilenumber=(EditText)dialog.findViewById(R.id.edMerchantMobilenumber);

                edMerchantMobilenumber.setText("8087448286");
                Button addMerchant = (Button) dialog.findViewById(R.id.btn_addmerchant);
                addMerchant.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String ConsumerMobilenumber=edMerchantMobilenumber.getText().toString().trim();
                        if(ConsumerMobilenumber.length()>0)
                        {

                            dialog.dismiss();
                            new AsyncTaskAddMerchant().execute();

                                //  makeAddMerchantRequest(edMerchantMobilenumber.getText().toString().trim());
                            // progressBar.setVisibility(View.GONE);


                            //mAdapter.notifyDataSetChanged();
                            // sendRequest();

                            // MerchantViewAdapter layoutManager = new MerchantViewAdapter(getActivity());
                            // mRecyclerView.setLayoutManager(layoutManager);
                           // mRecyclerView.smoothScrollToPosition(0);
                           /* mRecyclerView.post(new Runnable() {
                                @Override
                                public void run() {
                                    dialog.dismiss();
                                    makeAddMerchantRequest(edMerchantMobilenumber.getText().toString().trim());
                                    // progressBar.setVisibility(View.GONE);


                                    mAdapter.notifyDataSetChanged();
                                    // sendRequest();

                                    // MerchantViewAdapter layoutManager = new MerchantViewAdapter(getActivity());
                                    // mRecyclerView.setLayoutManager(layoutManager);
                                    mRecyclerView.smoothScrollToPosition(0);
                                }
                            });*/

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


    @Override
    public void onPause() {
        super.onPause();

        //mScannerView.stopCamera();           // Stop camera on pause
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_WRITE_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    setContentView(mScannerView);

                    mScannerView.setResultHandler(this); // Reg
                    mScannerView.startCamera();


                } else {
                    Toast.makeText(MerchantActivity.this, "Permission Denied!", Toast.LENGTH_SHORT).show();
                }
        }
    }


    public void arrayList(){
        for (int i = 0; i< 20; i++){
            list.add("This is row of number "+ i);
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
            progressDialog2 = new ProgressDialog(MerchantActivity.this);
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
                makeAddMerchantRequest(edMerchantMobilenumber.getText().toString().trim());
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


            mAdapter.notifyDataSetChanged();
            // sendRequest();

            // MerchantViewAdapter layoutManager = new MerchantViewAdapter(getActivity());
            // mRecyclerView.setLayoutManager(layoutManager);
            mRecyclerView.smoothScrollToPosition(0);
        }
    }
    /**
     * Making json object request
     */
    private void makeAddMerchantRequest(String ConsumerMobilenumber) {
       // llProgress.setVisibility(View.VISIBLE);
        String res = null;
        String responseCode = null;
        String returnResponse = null;
        try {

            String url = "http://simplypos.in/api/merchant-api.php?action=marchantRequest&merchant="+ConsumerMobilenumber+"&customerName="+Const.USER_NAME+"&customerMobile="+Const.USER_MOBILENUMBER;
          /*  JSONObject jsonrequest = new JSONObject();
            jsonrequest.put("phone", phone);
            jsonrequest.put("sspin", password);
*/
            //Const.MERCHANT_DATA.add(0,new Merchant("1","shivknat","9096572182","www.goole.com","3","pending"));

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
                    String merchantEmail=jsonuserdata.getString("email");
                    String merchantaddress=jsonuserdata.getString("address");
                    String merchantPosName=jsonuserdata.getString("pos_name");
                    String merchantMessage=jsonuserdata.getString("message");

                    String merchantPostCreated=jsonuserdata.getString("pos_create_at");
                    String merchantPOSExpires=jsonuserdata.getString("pos_demo_expiry_at");
                    String merchantPaymentStatus=jsonuserdata.getString("payment_status");



                    makeAddMerchantConsumerDatabaseRequest(new Merchant("","",merchantID,merchantName,merchantMobilenumber,merchantPOSURL,merchantType,merhantStatus
                            ,merchantEmail,merchantaddress,merchantPosName,merchantMessage,merchantPostCreated,merchantPOSExpires,merchantPaymentStatus));
                   // Collections.reverse(Const.MERCHANT_DATA);



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


    /**
     * Making json object request
     */
    private void makeAddMerchantConsumerDatabaseRequest(Merchant merchantDetail) {

        String res = null;
        String responseCode = null;
        String returnResponse = null;
        try {

            String url = "http://52.66.101.233/Customer-Backend/public/api/v1/customer/merchant";
           JSONObject jsonrequest = new JSONObject();
            jsonrequest.put("status", merchantDetail);


            JSONObject jsonres = new JSONObject();
            jsonres.put("id", merchantDetail.getMerchant_id());
            jsonres.put("name", merchantDetail.getName());
            jsonres.put("email", merchantDetail.getEmail());
            jsonres.put("phone", merchantDetail.getMobilenumber());
            jsonres.put("address", merchantDetail.getAddress());
            jsonres.put("type", merchantDetail.getMerchantType());
            jsonres.put("pos_name", merchantDetail.getPos_name());
            jsonres.put("message", merchantDetail.getMessage());
            jsonres.put("pos_url", merchantDetail.getPOSURL());
            jsonres.put("pos_create_at", merchantDetail.getPos_create_at());
            jsonres.put("pos_demo_expiry_at", merchantDetail.getPos_demo_expiry_at());
            jsonres.put("payment _status", merchantDetail.getPayment_status());


            jsonrequest.put("res", jsonres);

            //Const.MERCHANT_DATA.add(0,new Merchant("1","shivknat","9096572182","www.goole.com","3","pending"));

            WebService web = new WebService();
            res = web.postWithHeader(url,jsonrequest.toString());
            Log.d(res, res);


            if (res != null && res.length()>0) {
                JSONObject json = new JSONObject(res);
                if (json != null) {

                    String status=json.getString("status");
                    String message=json.getString("message");
                    if(status.equalsIgnoreCase("true"))
                    {
                        JSONObject merchantJsonData=json.getJSONObject("data");


                        String recordID=merchantJsonData.getString("id");
                        String customer_id=merchantJsonData.getString("customer_id");
                        String merchant_id=merchantJsonData.getString("merchant_id");
                        String merchant_name=merchantJsonData.getString("merchant_name");
                        String merchant_email=merchantJsonData.getString("merchant_email");
                        String merchant_phone=merchantJsonData.getString("merchant_phone");
                        String merchant_address=merchantJsonData.getString("merchant_address");
                        String merchant_type=merchantJsonData.getString("merchant_type");
                        String merchant_pos_name=merchantJsonData.getString("merchant_pos_name");
                        String merchant_message=merchantJsonData.getString("merchant_message");
                        String merchant_pos_url=merchantJsonData.getString("merchant_pos_url");
                        String merchant_pos_create_at=merchantJsonData.getString("merchant_pos_create_at");
                        String merchant_pos_demo_expiry_at=merchantJsonData.getString("merchant_pos_demo_expiry_at");
                        String merchant_payment_status=merchantJsonData.getString("merchant_payment_status");
                        String merchant_status=merchantJsonData.getString("merchant_status");





                        makeGetMerchantConsumerDatabaseRequest();
                      //  Const.MERCHANT_DATA.add(new Merchant(recordID,customer_id,merchant_id,merchant_name,merchant_phone,merchant_pos_url,merchant_type,merchant_status
                          //      ,merchant_email,merchant_address,merchant_pos_name,merchant_message,merchant_pos_create_at,merchant_pos_demo_expiry_at,merchant_payment_status));


                    }
                    Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();






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


    /**
     * Making json object request
     */
    private void makeGetMerchantConsumerDatabaseRequest() {

        Const.MERCHANT_DATA.clear();
        String res = null;
        String responseCode = null;
        String returnResponse = null;
        try {

            String url = "http://52.66.101.233/Customer-Backend/public/api/v1/customer/merchant/list";

            //Const.MERCHANT_DATA.add(0,new Merchant("1","shivknat","9096572182","www.goole.com","3","pending"));

            WebService web = new WebService();
            res = web.getWithHeader(url);
            Log.d(res, res);


            if (res != null && res.length()>0) {
                JSONObject json = new JSONObject(res);
                if (json != null) {

                    String status=json.getString("status");
                    String message=json.getString("message");
                    if(status.equalsIgnoreCase("true"))
                    {
                        JSONArray merchantJsonArrayData=json.getJSONArray("data");

                        for(int i=0;i<merchantJsonArrayData.length();i++)
                        {

                            JSONObject merchantJsonData = merchantJsonArrayData.getJSONObject(i);

                            String recordID=merchantJsonData.getString("id");
                            String customer_id=merchantJsonData.getString("customer_id");
                            String merchant_id=merchantJsonData.getString("merchant_id");
                            String merchant_name=merchantJsonData.getString("merchant_name");
                            String merchant_email=merchantJsonData.getString("merchant_email");
                            String merchant_phone=merchantJsonData.getString("merchant_phone");
                            String merchant_address=merchantJsonData.getString("merchant_address");
                            String merchant_type=merchantJsonData.getString("merchant_type");
                            String merchant_pos_name=merchantJsonData.getString("merchant_pos_name");
                            String merchant_message=merchantJsonData.getString("merchant_message");
                            String merchant_pos_url=merchantJsonData.getString("merchant_pos_url");
                            String merchant_pos_create_at=merchantJsonData.getString("merchant_pos_create_at");
                            String merchant_pos_demo_expiry_at=merchantJsonData.getString("merchant_pos_demo_expiry_at");
                            String merchant_payment_status=merchantJsonData.getString("merchant_payment_status");
                            String merchant_status=merchantJsonData.getString("merchant_status");

                            Const.MERCHANT_DATA.add(new Merchant(recordID,customer_id,merchant_id,merchant_name,merchant_phone,merchant_pos_url,merchant_type,merchant_status
                                    ,merchant_email,merchant_address,merchant_pos_name,merchant_message,merchant_pos_create_at,merchant_pos_demo_expiry_at,merchant_payment_status));


                        }






                        new GeneralFunction().hideProgressDialog();


                    }
                    Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();






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
    protected void onResume() {
        super.onResume();


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

    @Override
    public void handleResult(Result result) {
        Log.e("handler", result.getText()); // Prints scan results
        Log.e("handler", result.getBarcodeFormat().toString()); // Prints the scan format (qrcode)

        // show the scanner result into dialog box.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Scan Result");
        builder.setMessage(result.getText());
        AlertDialog alert1 = builder.create();
        alert1.show();
    }

    /*private void showProgress(String message) {
        ((TextView) llProgress.findViewById(R.id.tvMessage)).setText(message);
        llProgress.setVisibility(View.VISIBLE);

    }

    private void hideProgress() {
        ((TextView) llProgress.findViewById(R.id.tvMessage)).setText("");
        llProgress.setVisibility(View.GONE);
    }*/
}
