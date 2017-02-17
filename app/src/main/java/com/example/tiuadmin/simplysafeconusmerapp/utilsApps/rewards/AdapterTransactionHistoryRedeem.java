package com.example.tiuadmin.simplysafeconusmerapp.utilsApps.rewards;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.tiuadmin.simplysafeconusmerapp.Models.ConsumerTransactionHistoryModel;
import com.example.tiuadmin.simplysafeconusmerapp.R;

import java.util.ArrayList;

/**
 * Created by tiuadmin on 17/02/17.
 */

public class AdapterTransactionHistoryRedeem extends ArrayAdapter<ConsumerTransactionHistoryModel> {

    private Activity context;
    ArrayList<ConsumerTransactionHistoryModel> transactionList;

    public AdapterTransactionHistoryRedeem(Activity context, ArrayList<ConsumerTransactionHistoryModel> str) {
        super(context, R.layout.custom_list_transaction_earns, str);

        // TODO Auto-generated constructor stub
        this.context = context;
        this.transactionList = str;

    }

    @Override
    public int getCount() {
        return transactionList.size();
    }

    private class ViewHolder {
        TextView txt_date, txt_heading, txt_description,txt_remarks;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(R.layout.custom_list_transaction_earns, parent, false);

            holder.txt_heading=(TextView)convertView.findViewById(R.id.txt_merchantname);
            holder.txt_description=(TextView)convertView.findViewById(R.id.txt_tranactiontype);
            holder.txt_remarks=(TextView)convertView.findViewById(R.id.txt_remarks);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.txt_heading.setText(transactionList.get(position).getMerchantName());
        holder.txt_description.setText(transactionList.get(position).getType());
        holder.txt_remarks.setText(transactionList.get(position).getRemark());

        return convertView;
    }
}