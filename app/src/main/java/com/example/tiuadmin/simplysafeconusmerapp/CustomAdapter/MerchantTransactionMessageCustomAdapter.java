package com.example.tiuadmin.simplysafeconusmerapp.CustomAdapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tiuadmin.simplysafeconusmerapp.Models.MerchantTransactionModel;
import com.example.tiuadmin.simplysafeconusmerapp.R;

import java.util.ArrayList;

/**
 * Created by tiuadmin on 01/02/17.
 */

public class MerchantTransactionMessageCustomAdapter extends BaseAdapter {
    private ArrayList<MerchantTransactionModel> MerchantTransactionMessageList;
    private Activity activityContext;

    public  MerchantTransactionMessageCustomAdapter(Activity a, ArrayList<MerchantTransactionModel>MerchantTransactionMessageList)
    {
        this.activityContext=a;
        this.MerchantTransactionMessageList=MerchantTransactionMessageList;
    }

    @Override
    public int getCount() {
        return MerchantTransactionMessageList.size();
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
        View rowView=inflater.inflate(R.layout.merchant_transaction_message_custom_row, null,true);

        TextView txt_merchant_Transaction_Message_heading = (TextView) rowView.findViewById(R.id.txt_merchant_Transaction_Message_heading);
        TextView txt_merchant_Transaction_date = (TextView) rowView.findViewById(R.id.txt_merchant_Transaction_date);
        TextView txt_message = (TextView) rowView.findViewById(R.id.textView4);


        txt_merchant_Transaction_Message_heading.setText(MerchantTransactionMessageList.get(i).getMerchantMessageHeading());
        txt_merchant_Transaction_date.setText(MerchantTransactionMessageList.get(i).getDateTime());

        txt_message.setText(MerchantTransactionMessageList.get(i).getMerchantMessage());

        // txt_MerchantMessage.setText(MerchantMessageList.get(i).getMerchantMessage());

        return rowView;
    }
}
