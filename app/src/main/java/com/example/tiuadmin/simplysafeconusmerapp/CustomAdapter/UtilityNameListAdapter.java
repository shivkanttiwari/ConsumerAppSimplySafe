package com.example.tiuadmin.simplysafeconusmerapp.CustomAdapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tiuadmin.simplysafeconusmerapp.R;

import java.util.ArrayList;

/**
 * Created by tiuadmin on 04/01/17.
 */

public class UtilityNameListAdapter extends BaseAdapter {

    Activity context;
    ArrayList<String>utiltilyName;

    public  UtilityNameListAdapter(Activity context, ArrayList<String>utilityName)
    {
        this.context=context;
        this.utiltilyName=utilityName;
    }
    @Override
    public int getCount() {
        return utiltilyName.size();
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

        TextView txt_utilityName=(TextView)itemView.findViewById(R.id.txtutlitityname);

        txt_utilityName.setText(utiltilyName.get(position));

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (position)
                {
                    case 0:
                        Intent i = new Intent();
                        i.setClassName("com.android.calculator2", "com.android.calculator2.Calculator");
                        context.startActivity(i);

                        break;
                    case 1:


                        break;
                    case 2:


                        break;
                    case 3:


                        break;
                    case 4:


                        break;
                    case 5:


                        break;




                }
            }
        });



        return itemView;
    }
}
