package com.example.tiuadmin.simplysafeconusmerapp.CustomAdapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tiuadmin.simplysafeconusmerapp.Models.MerchantMessageModel;
import com.example.tiuadmin.simplysafeconusmerapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by shivkanttiwari on 30/01/17.
 */

public class MerchantMessageCustomAdapter extends BaseAdapter {

    private  ArrayList<MerchantMessageModel>MerchantMessageList;
    private  Activity activityContext;

    public  MerchantMessageCustomAdapter(Activity a, ArrayList<MerchantMessageModel>MerchantMessageList)
    {
        this.activityContext=a;
        this.MerchantMessageList=MerchantMessageList;
    }

    @Override
    public int getCount() {
        return MerchantMessageList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater=activityContext.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.merchantmessagecustomcell, null,true);

        TextView txt_MerchantMessage = (TextView) rowView.findViewById(R.id.merchantmessage);
        txt_MerchantMessage.setText(MerchantMessageList.get(i).getMerchantMessage());


        ImageView img_PromotionImage = (ImageView) rowView.findViewById(R.id.imageView5);
        // UserSearchID="";
        try {
            Picasso.with(activityContext)
                    .load(MerchantMessageList.get(i).getMerchantMessageImage()).placeholder(R.drawable.user).into(img_PromotionImage);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rowView;
    }
}
