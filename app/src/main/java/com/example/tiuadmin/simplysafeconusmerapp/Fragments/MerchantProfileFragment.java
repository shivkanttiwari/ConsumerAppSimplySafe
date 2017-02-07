package com.example.tiuadmin.simplysafeconusmerapp.Fragments;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tiuadmin.simplysafeconusmerapp.Models.Merchant;
import com.example.tiuadmin.simplysafeconusmerapp.Models.MerchantDetailMessageModel;
import com.example.tiuadmin.simplysafeconusmerapp.Models.MerchantMessageModel;
import com.example.tiuadmin.simplysafeconusmerapp.Models.MerchantTransactionModel;
import com.example.tiuadmin.simplysafeconusmerapp.R;
import com.example.tiuadmin.simplysafeconusmerapp.Utility.Const;
import com.example.tiuadmin.simplysafeconusmerapp.Webservices.WebService;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class MerchantProfileFragment extends Fragment {

    EditText user_pro_fullname_edittext;//=(EditText)view.findViewById(user_pro_fullname_edittext);
    EditText user_pro_email_editText;//=(EditText)view.findViewById(R.id.user_pro_email_editText);
    EditText user_pro_mobno_editText;//=(EditText)view.findViewById(user_pro_mobno_editText);
    EditText user_pro_address_editText;//=(EditText)view.findViewById(user_pro_address_editText);
    public MerchantProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view= inflater.inflate(R.layout.fragment_one, container, false);

         user_pro_fullname_edittext=(EditText)view.findViewById(R.id.user_pro_fullname_edittext);
         user_pro_email_editText=(EditText)view.findViewById(R.id.user_pro_email_editText);
         user_pro_mobno_editText=(EditText)view.findViewById(R.id.user_pro_mobno_editText);
         user_pro_address_editText=(EditText)view.findViewById(R.id.user_pro_address_editText);

       // new AsyncTaskAddMerchant().execute();


        if(Const.merchantDetailMessageModels.size()>0) {
            user_pro_fullname_edittext.setText(Const.merchantDetailMessageModels.get(0).getMerchantDetail().getName());
            user_pro_email_editText.setText(Const.merchantDetailMessageModels.get(0).getMerchantDetail().getEmail());
            user_pro_mobno_editText.setText(Const.merchantDetailMessageModels.get(0).getMerchantDetail().getMobilenumber());
            user_pro_address_editText.setText(Const.merchantDetailMessageModels.get(0).getMerchantDetail().getAddress());
        }


        return  view;
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
            progressDialog2 = new ProgressDialog(getActivity());
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

            if(Const.merchantDetailMessageModels.size()>0) {
                user_pro_fullname_edittext.setText(Const.merchantDetailMessageModels.get(0).getMerchantDetail().getName());
                user_pro_email_editText.setText(Const.merchantDetailMessageModels.get(0).getMerchantDetail().getEmail());
                user_pro_mobno_editText.setText(Const.merchantDetailMessageModels.get(0).getMerchantDetail().getMobilenumber());
                user_pro_address_editText.setText(Const.merchantDetailMessageModels.get(0).getMerchantDetail().getAddress());
            }
            else {
                Toast.makeText(getActivity(),"Problem while retriving merchant detail,please try again",Toast.LENGTH_SHORT).show();
            }
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
            res = web.getWithHeader(url);
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
                Toast.makeText(getActivity(), "Unable to get user information.", Toast.LENGTH_SHORT)
                        .show();

            }

        } catch (Exception e) {

            Toast.makeText(getActivity(), "Please provide valid mobile number.", Toast.LENGTH_SHORT)
                    .show();
            e.printStackTrace();
        }
    }
}
