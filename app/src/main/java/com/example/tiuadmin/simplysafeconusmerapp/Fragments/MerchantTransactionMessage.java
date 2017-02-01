package com.example.tiuadmin.simplysafeconusmerapp.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.tiuadmin.simplysafeconusmerapp.CustomAdapter.MerchantTransactionMessageCustomAdapter;
import com.example.tiuadmin.simplysafeconusmerapp.Models.MerchantTransactionModel;
import com.example.tiuadmin.simplysafeconusmerapp.R;

import java.util.ArrayList;


public class MerchantTransactionMessage extends Fragment {


    ListView Lv_MerchantTransaction;
    View MerchantTransactionView;

    ArrayList<MerchantTransactionModel> MerchantTransactionArray;
;
    public MerchantTransactionMessage() {
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


         MerchantTransactionView= inflater.inflate(R.layout.fragment_three, container, false);

        init();

        return  MerchantTransactionView;
    }

    public  void init()
    {
        Lv_MerchantTransaction=(ListView)MerchantTransactionView.findViewById(R.id.Lv_MerchantTransaction);

        MerchantTransactionArray=new ArrayList<>();

        MerchantTransactionArray.add(new MerchantTransactionModel("1","Your account balance is 7000 Rs.Please pay early to avoid surplus charges","01/02/2017","12:02"));
        MerchantTransactionArray.add(new MerchantTransactionModel("1","Your account balance is 7000 Rs.Please pay early to avoid surplus charges","01/02/2017","12:02"));
        MerchantTransactionArray.add(new MerchantTransactionModel("1","Your account balance is 7000 Rs.Please pay early to avoid surplus charges","01/02/2017","12:02"));
        MerchantTransactionArray.add(new MerchantTransactionModel("1","Your account balance is 7000 Rs.Please pay early to avoid surplus charges","01/02/2017","12:02"));
        MerchantTransactionArray.add(new MerchantTransactionModel("1","Your account balance is 7000 Rs.Please pay early to avoid surplus charges","01/02/2017","12:02"));
        MerchantTransactionArray.add(new MerchantTransactionModel("1","Your account balance is 7000 Rs.Please pay early to avoid surplus charges","01/02/2017","12:02"));
        MerchantTransactionArray.add(new MerchantTransactionModel("1","Your account balance is 7000 Rs.Please pay early to avoid surplus charges","01/02/2017","12:02"));
        MerchantTransactionArray.add(new MerchantTransactionModel("1","Your account balance is 7000 Rs.Please pay early to avoid surplus charges","01/02/2017","12:02"));


        MerchantTransactionMessageCustomAdapter adapter=new MerchantTransactionMessageCustomAdapter(getActivity(),MerchantTransactionArray);

        Lv_MerchantTransaction.setAdapter(adapter);



    }

}
