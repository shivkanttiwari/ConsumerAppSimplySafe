package com.example.tiuadmin.simplysafeconusmerapp.utilsApps.rewards;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.tiuadmin.simplysafeconusmerapp.R;

import java.util.ArrayList;

public class AdapterTransactionHistory extends ArrayAdapter<String> {

    private Activity context;
    ArrayList<String> str;

    public AdapterTransactionHistory(Activity context, ArrayList<String> str) {
        super(context, R.layout.custom_list_transaction_earns, str);

        // TODO Auto-generated constructor stub
        this.context = context;
        this.str = str;

    }

    private class ViewHolder {
        TextView txt_date, txt_heading, txt_description;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(R.layout.custom_list_transaction_earns, parent, false);


            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        return convertView;
    }
}