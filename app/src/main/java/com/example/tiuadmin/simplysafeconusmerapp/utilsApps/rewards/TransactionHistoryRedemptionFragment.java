package com.example.tiuadmin.simplysafeconusmerapp.utilsApps.rewards;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.tiuadmin.simplysafeconusmerapp.Models.ConsumerTransactionHistoryModel;
import com.example.tiuadmin.simplysafeconusmerapp.R;
import com.example.tiuadmin.simplysafeconusmerapp.Utility.Const;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TransactionHistoryRedemptionFragment extends Fragment {

    ListView lv;
ArrayList<ConsumerTransactionHistoryModel>TransactionHistoyDEBITARRAY;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_transaction_history_redemption, container, false);
        lv = (ListView) v.findViewById(R.id.listView1);
        TransactionHistoyDEBITARRAY=new ArrayList<>();



        for(int i=0;i<Const.MERCHANT_REWARDS_TRANSACTION.size();i++)
        {
            if(Const.MERCHANT_REWARDS_TRANSACTION.get(i).getType().equalsIgnoreCase("DEBIT"))
            {
                TransactionHistoyDEBITARRAY.add(Const.MERCHANT_REWARDS_TRANSACTION.get(i));
            }

        }

        AdapterTransactionHistory adapter = new AdapterTransactionHistory(getActivity(), TransactionHistoyDEBITARRAY);
        lv.setAdapter(adapter);

        return v;
    }

}
