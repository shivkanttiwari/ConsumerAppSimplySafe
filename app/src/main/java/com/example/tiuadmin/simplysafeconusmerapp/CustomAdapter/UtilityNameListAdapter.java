package com.example.tiuadmin.simplysafeconusmerapp.CustomAdapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tiuadmin.simplysafeconusmerapp.R;

/**
 * Created by tiuadmin on 04/01/17.
 */

public class UtilityNameListAdapter extends BaseAdapter {

    Activity context;
    String[] utiltilyName;

    public UtilityNameListAdapter(Activity context, String[] utilityName) {
        this.context = context;
        this.utiltilyName = utilityName;
    }

    @Override
    public int getCount() {
        return utiltilyName.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.utilitynamelistlayout, parent, false);

        TextView txt_utilityName = (TextView) itemView.findViewById(R.id.txtutlitityname);
        txt_utilityName.setText(utiltilyName[position]);

        return itemView;
    }
}
