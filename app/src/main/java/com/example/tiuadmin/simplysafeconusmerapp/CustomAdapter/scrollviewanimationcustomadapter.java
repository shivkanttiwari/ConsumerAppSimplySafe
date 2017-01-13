package com.example.tiuadmin.simplysafeconusmerapp.CustomAdapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.tiuadmin.simplysafeconusmerapp.R;

/**
 * Created by tiuadmin on 13/01/17.
 */

public class scrollviewanimationcustomadapter extends BaseAdapter {



    /**
     * Created by tiuadmin on 13/01/17.
     */



        LayoutInflater inflater=null;
        public scrollviewanimationcustomadapter(Activity context) {

            inflater = ( LayoutInflater )context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        public int getCount() {
            return 10;
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            View vi=convertView;

                vi = inflater.inflate(R.layout.scrollviewanimationcustomrow, null);


            return vi;
        }
    }

