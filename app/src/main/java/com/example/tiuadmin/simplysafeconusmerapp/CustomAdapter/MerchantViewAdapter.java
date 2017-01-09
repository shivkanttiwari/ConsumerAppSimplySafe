package com.example.tiuadmin.simplysafeconusmerapp.CustomAdapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tiuadmin.simplysafeconusmerapp.Models.Merchant;
import com.example.tiuadmin.simplysafeconusmerapp.R;
import com.example.tiuadmin.simplysafeconusmerapp.Utility.Place;
import com.example.tiuadmin.simplysafeconusmerapp.Utility.PlaceData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by shivkanttiwari on 02/01/17.
 */

public class MerchantViewAdapter extends
        RecyclerView.Adapter<MerchantViewAdapter.ViewHolder> {

    Activity mContext;
    OnItemClickListener mItemClickListener;

    ArrayList<Merchant>merchantArray;

    int[] merchantIcons = {R.drawable.borabora, R.drawable.canada,R.drawable.dubai, R.drawable.hongkong,R.drawable.iceland, R.drawable.india,R.drawable.kenya, R.drawable.london,
            R.drawable.switzerland, R.drawable.sydney,};

    public MerchantViewAdapter(Activity context, ArrayList<Merchant>merchantArrray) {
        this.mContext = context;
        this.merchantArray=merchantArrray;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_places, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Place place = new PlaceData().placeList().get(position);

        holder.placeName.setText(merchantArray.get(position).getName());

       if(position>2)
        {
            Picasso.with(mContext).load(R.drawable.pendingimage).into(holder.placeImage);

            Bitmap photo = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.pendingimage);

            Palette.generateAsync(photo, new Palette.PaletteAsyncListener() {
                public void onGenerated(Palette palette) {
                    int mutedLight = palette.getMutedColor(mContext.getResources().getColor(android.R.color.black));
                    holder.placeNameHolder.setBackgroundColor(mutedLight);
                }
            });
        }
        else {
            Picasso.with(mContext).load(R.drawable.pendingimage).into(holder.placeImage);

            Bitmap photo = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.pendingimage);

            Palette.generateAsync(photo, new Palette.PaletteAsyncListener() {
                public void onGenerated(Palette palette) {
                    int mutedLight = palette.getMutedColor(mContext.getResources().getColor(android.R.color.black));
                    holder.placeNameHolder.setBackgroundColor(mutedLight);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return merchantArray.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public LinearLayout placeHolder;
        public LinearLayout placeNameHolder;
        public TextView placeName;
        public ImageView placeImage;

        public ViewHolder(View itemView) {
            super(itemView);
            placeHolder = (LinearLayout) itemView.findViewById(R.id.mainHolder);
            placeName = (TextView) itemView.findViewById(R.id.placeName);
            placeNameHolder = (LinearLayout) itemView.findViewById(R.id.placeNameHolder);
            placeImage = (ImageView) itemView.findViewById(R.id.placeImage);
            placeHolder.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(itemView, getPosition());
            }
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

}
