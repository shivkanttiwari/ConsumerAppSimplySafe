package com.example.tiuadmin.simplysafeconusmerapp.CustomAdapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.graphics.Palette;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tiuadmin.simplysafeconusmerapp.Activity.MerchanteWebviewActivity;
import com.example.tiuadmin.simplysafeconusmerapp.Models.Merchant;
import com.example.tiuadmin.simplysafeconusmerapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by shivkanttiwari on 02/01/17.
 */

public class MerchantViewAdapter extends
        ArrayAdapter<Merchant> {


    Activity mContext;


    ArrayList<Merchant>merchantArray;
    private static LayoutInflater inflater=null;

    int[] merchantIcons = {R.drawable.borabora, R.drawable.canada,R.drawable.dubai, R.drawable.hongkong,R.drawable.iceland, R.drawable.india,R.drawable.kenya, R.drawable.london,
            R.drawable.switzerland, R.drawable.sydney,};
    public MerchantViewAdapter(Activity context, ArrayList<Merchant> merchantArrray) {
        super(context, R.layout.row_places, merchantArrray);
        this.mContext = context;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.merchantArray=merchantArrray;
    }

    @Override
    public int getCount() {
        return merchantArray.size();
    }

    @Override
    public Merchant getItem(int position) {
        //return merchantArray.get(position);
        return super.getItem(getCount() - position - 1);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        final  ViewHolder holder;
        if (convertView == null) {
            convertView =   inflater.inflate(R.layout.row_places, parent, false);
            holder = new ViewHolder();
            holder.mainholderlayout = (LinearLayout) convertView.findViewById(R.id.mainHolder);
            holder.bottmPlaceholdeLayout = (LinearLayout) convertView.findViewById(R.id.placeNameHolder);
            holder.merchantName  = (TextView) convertView.findViewById(R.id.placeName);
            holder.MerchantIMage  = (ImageView) convertView.findViewById(R.id.placeImage);
            holder.txtstatusmerchant  = (TextView) convertView.findViewById(R.id.txtstatusmerchant);


            int[] androidColors = mContext.getResources().getIntArray(R.array.androidcolors);
            int randomAndroidColor = androidColors[new Random().nextInt(androidColors.length)];
            holder.mainholderlayout.setBackgroundColor(randomAndroidColor);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }


        if(merchantArray.get(position).getStatus().equalsIgnoreCase("Pending"))
        {
            holder.txtstatusmerchant.setText("Status:"+merchantArray.get(position).getStatus());
        }
        else
        {
            holder.txtstatusmerchant.setVisibility(View.GONE);
        }


        holder.mainholderlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"Main Holder clicked",Toast.LENGTH_SHORT).show();
            }
        });

        holder.bottmPlaceholdeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(merchantArray.get(position).getStatus().equalsIgnoreCase("Pending"))
                {
                    Toast.makeText(mContext, "Account not verified.Please contact respected merchant to activiate.", Toast.LENGTH_SHORT).show();
                }
                else {


                    Toast.makeText(mContext, "pos clicked", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(mContext, MerchanteWebviewActivity.class);
                    i.putExtra("MerchantURL", merchantArray.get(position).getPOSURL());
                    mContext.startActivity(i);
                }
            }
        });


        if(merchantArray.get(position).getStatus().equalsIgnoreCase("Pendings"))
        {
            Picasso.with(mContext).load(R.drawable.pendingimage).into(holder.MerchantIMage);

            Bitmap photo = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.pendingimage);

            Palette.generateAsync(photo, new Palette.PaletteAsyncListener() {
                public void onGenerated(Palette palette) {
                    int mutedLight = palette.getMutedColor(mContext.getResources().getColor(android.R.color.black));
                    holder.bottmPlaceholdeLayout.setBackgroundColor(mutedLight);
                }
            });
        }
        else {
            Picasso.with(mContext).load(R.drawable.pendingimage).into(holder.MerchantIMage);

            Bitmap photo = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.pendingimage);

            Palette.generateAsync(photo, new Palette.PaletteAsyncListener() {
                public void onGenerated(Palette palette) {
                    int mutedLight = palette.getMutedColor(mContext.getResources().getColor(android.R.color.black));
                    holder.bottmPlaceholdeLayout.setBackgroundColor(mutedLight);
                }
            });
        }


        int MerchantType=Integer.parseInt(merchantArray.get(position).getMerchantType());


        switch (MerchantType)
        {


            case 1:
                Picasso.with(mContext).load(R.drawable.cutlery).into(holder.MerchantIMage);
                break;

            case 2:
                Picasso.with(mContext).load(R.drawable.building).into(holder.MerchantIMage);
                break;

            case 3:
                Picasso.with(mContext).load(R.drawable.cart).into(holder.MerchantIMage);
                break;

            case 4:
                Picasso.with(mContext).load(R.drawable.toaster).into(holder.MerchantIMage);
                break;

            case 5:
                Picasso.with(mContext).load(R.drawable.kirana).into(holder.MerchantIMage);
                break;

            case 6:
                Picasso.with(mContext).load(R.drawable.cinema).into(holder.MerchantIMage);
                break;



        }
        Merchant merchant = getItem(position);



        // loading album cover using Glide library
        holder.merchantName.setText(merchant.getName());

        //holder.personImageView.setImageBitmap(person.getImage());

        return convertView;

    }

    static class ViewHolder {
        private LinearLayout mainholderlayout;
        private LinearLayout bottmPlaceholdeLayout;
        private TextView merchantName;
        private ImageView MerchantIMage;
        private TextView txtstatusmerchant;
    }
}

/*
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
        holder.bind(merchantArray.get(position), mItemClickListener);

        holder.placeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(mContext,"pos clicked",Toast.LENGTH_SHORT).show();
                Intent i=new Intent(mContext, MerchanteWebviewActivity.class);
                i.putExtra("MerchantURL",merchantArray.get(position).getPOSURL());
                mContext.startActivity(i);
            }
        });

       if(merchantArray.get(position).getStatus().equalsIgnoreCase("Pendings"))
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

    public interface OnItemClickListener {
        void onItemClick(Merchant item);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
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
           // placeHolder.setOnClickListener(this);
        }
        public void bind(final Merchant item, final OnItemClickListener listener) {
            //     name.setText(item.name);
            // Picasso.with(itemView.getContext()).load(item.imageUrl).into(image);




            placeImage.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }

    }



    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

}
*/
