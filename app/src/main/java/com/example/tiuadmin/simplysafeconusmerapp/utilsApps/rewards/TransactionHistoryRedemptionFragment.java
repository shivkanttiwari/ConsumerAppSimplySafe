package com.example.tiuadmin.simplysafeconusmerapp.utilsApps.rewards;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.tiuadmin.simplysafeconusmerapp.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TransactionHistoryRedemptionFragment extends Fragment {

    ListView lv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_transaction_history_redemption, container, false);
        lv = (ListView) v.findViewById(R.id.listView1);

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("");
        arrayList.add("");
        arrayList.add("");
        arrayList.add("");
        arrayList.add("");
        arrayList.add("");

        AdapterTransactionHistory adapter = new AdapterTransactionHistory(getActivity(), arrayList);
        lv.setAdapter(adapter);

        return v;
    }

}
