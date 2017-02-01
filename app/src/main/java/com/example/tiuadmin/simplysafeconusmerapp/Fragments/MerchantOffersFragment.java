package com.example.tiuadmin.simplysafeconusmerapp.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.tiuadmin.simplysafeconusmerapp.CustomAdapter.MerchantMessageCustomAdapter;
import com.example.tiuadmin.simplysafeconusmerapp.Models.MerchantMessageModel;
import com.example.tiuadmin.simplysafeconusmerapp.R;

;import java.util.ArrayList;


public class MerchantOffersFragment extends Fragment {


    ArrayList<MerchantMessageModel>merchantMessageModels;
    ListView lv_MerchantListview;
    View MerchantMessageView;

    MerchantMessageCustomAdapter messageAdapter;

    public MerchantOffersFragment() {
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

         MerchantMessageView=inflater.inflate(R.layout.fragment_two, container, false);

        initialize();

        return MerchantMessageView;
    }

    public void initialize()
    {
        lv_MerchantListview=(ListView)MerchantMessageView.findViewById(R.id.lv_MerchantMessage);
        merchantMessageModels=new ArrayList<>();

        merchantMessageModels.add(new MerchantMessageModel("1","Hello"));
        merchantMessageModels.add(new MerchantMessageModel("1","Hello"));
        merchantMessageModels.add(new MerchantMessageModel("1","Hello"));
        merchantMessageModels.add(new MerchantMessageModel("1","Hello"));
        merchantMessageModels.add(new MerchantMessageModel("1","Hello"));
        merchantMessageModels.add(new MerchantMessageModel("1","Hello"));
        merchantMessageModels.add(new MerchantMessageModel("1","Hello"));
        merchantMessageModels.add(new MerchantMessageModel("1","Hello"));
        merchantMessageModels.add(new MerchantMessageModel("1","Hello"));
        merchantMessageModels.add(new MerchantMessageModel("1","Hello"));


        messageAdapter=new MerchantMessageCustomAdapter(getActivity(),merchantMessageModels);

        lv_MerchantListview.setAdapter(messageAdapter);

    }

}
