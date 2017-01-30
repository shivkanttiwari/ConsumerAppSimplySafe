package com.example.tiuadmin.simplysafeconusmerapp.CustomAdapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.graphics.Palette;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tiuadmin.simplysafeconusmerapp.Activity.MerchanteWebviewActivity;
import com.example.tiuadmin.simplysafeconusmerapp.Merchant.MerchantActivity;
import com.example.tiuadmin.simplysafeconusmerapp.Merchant.SimpleTabsActivity;
import com.example.tiuadmin.simplysafeconusmerapp.Models.Merchant;
import com.example.tiuadmin.simplysafeconusmerapp.R;
import com.example.tiuadmin.simplysafeconusmerapp.Utility.Const;
import com.example.tiuadmin.simplysafeconusmerapp.Webservices.WebService;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

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

    String Merchant_ID="";
    String MerchantStatus="";
    String MerchantDeleteStatus="";
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
        return merchantArray.get(position);
       // return super.getItem(getCount() - position - 1);
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
holder.deleteMerchant=(ImageView) convertView.findViewById(R.id.imgdeleteMerchant);

            int[] androidColors = mContext.getResources().getIntArray(R.array.androidcolors);
            int randomAndroidColor = androidColors[new Random().nextInt(androidColors.length)];
            holder.mainholderlayout.setBackgroundColor(randomAndroidColor);
            convertView.setTag(holder);

            int MerchantType=Integer.parseInt(merchantArray.get(position).getMerchantType());
            Bitmap photo = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.pendingimage);

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

                case -1:
                    Picasso.with(mContext).load(R.drawable.addmerchantdummey).into(holder.MerchantIMage);
                    break;



            }
            Palette.generateAsync(photo, new Palette.PaletteAsyncListener() {
                public void onGenerated(Palette palette) {
                    int mutedLight = palette.getMutedColor(mContext.getResources().getColor(android.R.color.black));
                    holder.bottmPlaceholdeLayout.setBackgroundColor(mutedLight);
                }
            });
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        int MerchantType=Integer.parseInt(merchantArray.get(position).getMerchantType());
        Bitmap photo = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.pendingimage);

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

            case -1:
                Picasso.with(mContext).load(R.drawable.addmerchantdummey).into(holder.MerchantIMage);
                break;



        }
        holder.deleteMerchant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"delete",Toast.LENGTH_SHORT).show();

                AlertDialog myQuittingDialogBox = new AlertDialog.Builder(mContext)
                        //set message, title, and icon
                        .setTitle("Delete")
                        .setMessage("Do you want to Delete this merchant?")
                        .setIcon(R.drawable.cross_delete)

                        .setPositiveButton("Delete", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                //your deleting code
                                dialog.dismiss();
                                makeForgetPasswordRequest(merchantArray.get(position).getMobilenumber(),position);
                            }

                        })


                        .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.dismiss();

                            }
                        })
                        .create();

                myQuittingDialogBox.show();

            }
        });
        if(merchantArray.get(position).getStatus().equalsIgnoreCase("Pending"))
        {
            holder.txtstatusmerchant.setVisibility(View.VISIBLE);
            holder.txtstatusmerchant.setText("Status:"+merchantArray.get(position).getStatus());
        }
        else
        {
            holder.txtstatusmerchant.setVisibility(View.INVISIBLE);
        }


        holder.mainholderlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   Toast.makeText(mContext,"Main Holder clicked",Toast.LENGTH_SHORT).show();

                mContext.startActivity(new Intent(mContext, SimpleTabsActivity.class));
            }
        });

        holder.bottmPlaceholdeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


               // Merchant_ID=merchantArray.get(position).getMerchant_id();
                //new AsyncTaskUpdateProfile().execute();
                if(merchantArray.get(position).getStatus().equalsIgnoreCase("false"))
                {
                    Toast.makeText(mContext, "Account not verified.Please contact respected merchant to activiate.", Toast.LENGTH_SHORT).show();
                }
                else {


                     Merchant_ID=merchantArray.get(position).getMerchant_id();
                    new AsyncTaskUpdateProfile().execute();
                }




            }
        });


        /*if(merchantArray.get(position).getStatus().equalsIgnoreCase("Pendings"))
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
        }*/



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
        private ImageView deleteMerchant;
    }




    //******************webservice********
    private ProgressDialog progressDialog2 = null;
    String username;
    ArrayList<Merchant> setget = new ArrayList<>();
    String fname, lname, strGender;

    // To use the AsyncTask, it must be subclassed
    private class AsyncTaskUpdateProfile extends AsyncTask<Void, Integer, Void> {
        // Before running code in separate thread
        @Override
        protected void onPreExecute() {
            // Create a new progress dialog
            progressDialog2 = new ProgressDialog(mContext);
            progressDialog2.getWindow().setBackgroundDrawableResource(R.color.colorPrimaryDark);
            progressDialog2.getWindow().setGravity(Gravity.CENTER);
            // Set the progress dialog to display a horizontal progress bar
            progressDialog2.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            // Set the dialog title to 'Loading...'
            // Set the dialog message to 'Loading application View, please
            // wait...'
            progressDialog2.setMessage("Pleaes Wait...");
            // This dialog can't be canceled by pressing the back key
            progressDialog2.setCancelable(false);
            // This dialog isn't indeterminate
            progressDialog2.setIndeterminate(false);
            // Display the progress dialog
            progressDialog2.show();
        }

        // The code to be executed in a background thread.
        @Override
        protected Void doInBackground(Void... params) {
            try {
                makeConsumerAutentiationRequest();
            } catch (Exception e) {
                progressDialog2.dismiss();
                e.printStackTrace();
            }
            return null;
        }

        // after executing the code in the thread
        @Override
        protected void onPostExecute(Void result) {
            progressDialog2.dismiss();
            if (MerchantStatus.equalsIgnoreCase("true"))
                {

                }
                else
            {
                Toast.makeText(mContext,"You are not authorized with this merchant",Toast.LENGTH_SHORT)
.show();            }

        }
    }


    /**
     * Making json object request
     */
    private void makeConsumerAutentiationRequest() {

        String res = null;
        String responseCode = null;
        String returnResponse = null;
        try {

            String url = "http://52.66.101.233/Customer-Backend/public/api/v1/customer/merchant/"+Merchant_ID;
          /*  JSONObject jsonrequest = new JSONObject();
            jsonrequest.put("phone", phone);
            jsonrequest.put("sspin", password);
*/



                WebService web = new WebService();
                res = web.getWithHeader(url);
                Log.d(res, res);


                if (res != null && res.length() > 0) {
                    JSONObject json = new JSONObject(res);
                    if (json != null) {


                        MerchantStatus = json.getString("status");
                        String message = json.getString("message");
                        if (MerchantStatus.equalsIgnoreCase("true")) {

                            JSONObject MerchantStatusJSON=new JSONObject();
                            MerchantStatusJSON=json.getJSONObject("data");

                            String id=MerchantStatusJSON.getString("id");
                            String customer_id=MerchantStatusJSON.getString("customer_id");

                            String merchant_id=MerchantStatusJSON.getString("merchant_id");
                            String merchant_name=MerchantStatusJSON.getString("merchant_name");
                            String merchant_email=MerchantStatusJSON.getString("merchant_email");
                            String merchant_phone=MerchantStatusJSON.getString("merchant_phone");
                            String merchant_address=MerchantStatusJSON.getString("merchant_address");
                            String merchant_type=MerchantStatusJSON.getString("merchant_type");
                            String merchant_pos_name=MerchantStatusJSON.getString("merchant_pos_name");
                            String merchant_message=MerchantStatusJSON.getString("merchant_message");
                            String merchant_pos_url=MerchantStatusJSON.getString("merchant_pos_url");
                            String merchant_pos_create_at=MerchantStatusJSON.getString("merchant_pos_create_at");
                            String merchant_pos_demo_expiry_at=MerchantStatusJSON.getString("merchant_pos_demo_expiry_at");
                            String merchant_payment_status=MerchantStatusJSON.getString("merchant_payment_status");
                            String merchant_status=MerchantStatusJSON.getString("merchant_status");
                            String merchant_password=MerchantStatusJSON.getString("merchant_password");
                            String created_at=MerchantStatusJSON.getString("created_at");
                            String updated_at=MerchantStatusJSON.getString("updated_at");


                            if(merchant_status.equalsIgnoreCase("Pending"))
                            {
                                MerchantStatus="false";
                            }
                            else {
                                Intent merchantShopIntent=new Intent(mContext, MerchanteWebviewActivity.class);

                                merchantShopIntent.putExtra("MerchantURL",merchant_pos_url);
                                merchantShopIntent.putExtra("Passkey",merchant_password);
                                mContext.startActivity(merchantShopIntent);
                            }




                        }



                    }
                }





        } catch (Exception e) {

            Toast.makeText(mContext, "Unable to authenticate consumer .Please try again..", Toast.LENGTH_SHORT)
                    .show();
            e.printStackTrace();
        }
    }

    /**
     * Making json object request
     */
    private void makeForgetPasswordRequest(String phone,int position) {

        String res = null;
        String responseCode = null;
        String returnResponse = null;
        try {

            String url = Const.MERCHANT_DELETE_API;
            JSONObject jsonrequest = new JSONObject();

            jsonrequest.put("phone", phone);




            WebService web = new WebService();
            res = web.postWithHeader(url, jsonrequest.toString());
            Log.d(res, res);


            if (res != null) {
                JSONObject json = new JSONObject(res);
                if (json != null) {

                    MerchantDeleteStatus = json.getString("status");
                    String message = json.getString("message");

                    Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();

                    merchantArray.remove(position);
                    MerchantActivity.mAdapter.notifyDataSetChanged();


                }
            }

        } catch (Exception e) {

            Toast.makeText(mContext,"There is a problem whiile deleting this merchant,Please try agian later",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
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
