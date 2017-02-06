package com.example.tiuadmin.simplysafeconusmerapp.utilsApps.entertainment;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tiuadmin.simplysafeconusmerapp.Models.MainMenuDashboard;
import com.example.tiuadmin.simplysafeconusmerapp.R;

import java.util.List;
import java.util.Random;

import cz.martykan.forecastie.activities.SplashActivity;

/**
 * Created by mayur.p on 1/24/2017.
 */

public class EntertainmentAdapter extends RecyclerView.Adapter<EntertainmentAdapter.MyViewHolder> {

    private Activity mContext;
    private List<MainMenuDashboard> albumList;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, count;
        public ImageView thumbnail, overflow;
        public CardView cardview;
        public RelativeLayout relativelayoutAlbumAdapter;

        public MyViewHolder(View view) {
            super(view);
            cardview = (CardView) view.findViewById(R.id.card_view);
            title = (TextView) view.findViewById(R.id.title);
            count = (TextView) view.findViewById(R.id.count);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            overflow = (ImageView) view.findViewById(R.id.overflow);
            relativelayoutAlbumAdapter = (RelativeLayout) view.findViewById(R.id.relativelayoutAlbumAdapter);
        }
    }

    public EntertainmentAdapter(Activity mContext, List<MainMenuDashboard> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }


    @Override
    public EntertainmentAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.album_card, parent, false);

        //itemView.setOnClickListener(mOnClickListener);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        MainMenuDashboard album = albumList.get(position);
        holder.title.setText(album.getName());
        //   holder.count.setText(album.getNumOfSongs() + " songs");


        int[] androidColors = mContext.getResources().getIntArray(R.array.androidcolors);
        int randomAndroidColor = androidColors[new Random().nextInt(androidColors.length)];
        holder.thumbnail.setBackgroundColor(randomAndroidColor);

        // loading album cover using Glide library
        Glide.with(mContext).load(album.getImage()).into(holder.thumbnail);

//        holder.overflow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showPopupMenu(holder.overflow);
//            }
//        });

        holder.relativelayoutAlbumAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                switch (position) {
                    case 0:
                        Intent i = new Intent();
                        i.setClassName("com.android.calculator2",
                                "com.android.calculator2.Calculator");
                        mContext.startActivity(i);

                        break;
                    case 1:
                         Intent utiltyIntent = new Intent(mContext, SplashActivity.class);
                         mContext.startActivity(utiltyIntent);
                        break;
                    case 2:

                        break;
                    case 3:

                        mContext.startActivity(new Intent(mContext, EntertainmentActivity.class));
                        break;
                    case 4:


                        break;
                    case 5:

                        break;


                }


            /*    if(position==0)
                {
                    merchantIntent.putExtra("MerchantURL", "https://www.flipkart.com/");
                }
                else
                {


                    merchantIntent.putExtra("MerchantURL", "http://www.amazon.in/");
                }
                mContext.startActivity(merchantIntent);*/
            }
        });
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }
}
