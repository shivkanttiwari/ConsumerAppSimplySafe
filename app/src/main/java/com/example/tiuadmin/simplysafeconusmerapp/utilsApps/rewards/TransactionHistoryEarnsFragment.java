package com.example.tiuadmin.simplysafeconusmerapp.utilsApps.rewards;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.tiuadmin.simplysafeconusmerapp.Callbacks.Callback;
import com.example.tiuadmin.simplysafeconusmerapp.Callbacks.MyAsynckTask;
import com.example.tiuadmin.simplysafeconusmerapp.Models.ConsumerTransactionHistoryModel;
import com.example.tiuadmin.simplysafeconusmerapp.R;
import com.example.tiuadmin.simplysafeconusmerapp.Utility.Const;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TransactionHistoryEarnsFragment extends Fragment {

    ListView lv;
    String url="http://52.66.101.233/Customer-Backend/public/api/v1/customer/tranactions";
    String status,message;
    ArrayList<ConsumerTransactionHistoryModel> ConsumerTransactionLIst;
    AdapterTransactionHistory adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_transaction_history_earns, container, false);
        lv = (ListView) v.findViewById(R.id.listView1);

        ConsumerTransactionLIst = new ArrayList<>();


         adapter = new AdapterTransactionHistory(getActivity(), ConsumerTransactionLIst);
         lv.setAdapter(adapter);

        MyAsynckTask obj=new MyAsynckTask(new Callback() {
            @Override
            public void onResult(String result) {

                String res=result;


                try {




                    if (res != null && res.length() > 0) {
                        JSONObject json = new JSONObject(res);
                        if (json != null) {

                            status = json.getString("status");
                            message = json.getString("message");
                            if (status.equalsIgnoreCase("true")) {
                               // JSONObject jsonuserdata = json.getJSONObject("data");


                                JSONArray jsonArrayTransactionRecord = json.getJSONArray("data");

                                for (int i=0; i<jsonArrayTransactionRecord.length(); i++) {


                                    JSONObject TransactionRecordJson=jsonArrayTransactionRecord.getJSONObject(i);


                                    String id=TransactionRecordJson.getString("id");
                                    String customerId=TransactionRecordJson.getString("customerId");
                                    String merchantId=TransactionRecordJson.getString("merchantId");
                                    String merchantName=TransactionRecordJson.getString("merchantName");
                                    String points=TransactionRecordJson.getString("points");
                                    String merchantOrderId=TransactionRecordJson.getString("merchantOrderId");
                                    String type=TransactionRecordJson.getString("transactionType");
                                    String rewardOrderId=TransactionRecordJson.getString("rewardOrderId");
                                    String remark=TransactionRecordJson.getString("remark");




                                    Const.MERCHANT_REWARDS_TRANSACTION.add(new ConsumerTransactionHistoryModel(id, customerId, merchantId,
                                            merchantName, points, merchantOrderId, type, rewardOrderId, remark));
                                    if(type.equalsIgnoreCase("CREDIT")) {
                                        ConsumerTransactionLIst.add(new ConsumerTransactionHistoryModel(id, customerId, merchantId,
                                                merchantName, points, merchantOrderId, type, rewardOrderId, remark));
                                    }
                                }


                                adapter.notifyDataSetChanged();



                            }


                            // new GeneralFunction().hideProgressDialog();
                        }
                    } else {
                        message="Unable to get user information";
                        // new GeneralFunction().hideProgressDialog();
                    }

                } catch (Exception e) {
                    message="Unable to get user information";
                    //new GeneralFunction().hideProgressDialog();
                    e.printStackTrace();
                }


            }
        },url, getActivity());
        obj.execute();
        return v;
    }

}
