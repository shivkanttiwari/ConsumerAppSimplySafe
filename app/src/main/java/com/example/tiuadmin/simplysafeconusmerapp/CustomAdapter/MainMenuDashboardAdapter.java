package com.example.tiuadmin.simplysafeconusmerapp.CustomAdapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tiuadmin.simplysafeconusmerapp.APPUtility.UtilityActivity;
import com.example.tiuadmin.simplysafeconusmerapp.Merchant.MerchantActivity;
import com.example.tiuadmin.simplysafeconusmerapp.Models.MainMenuDashboard;
import com.example.tiuadmin.simplysafeconusmerapp.R;
import com.example.tiuadmin.simplysafeconusmerapp.User.MyPreferencesActivity;
import com.example.tiuadmin.simplysafeconusmerapp.Utility.GeneralFunction;
import com.example.tiuadmin.simplysafeconusmerapp.utilsApps.entertainment.WebViewListsActivity;
import com.example.tiuadmin.simplysafeconusmerapp.utilsApps.rewards.RewardHomeActivity;
import com.example.tiuadmin.simplysafeconusmerapp.utilsApps.utils.UtilsConstants;

import java.util.List;
import java.util.Random;


public class MainMenuDashboardAdapter extends RecyclerView.Adapter<MainMenuDashboardAdapter.MyViewHolder> {

    private Activity mContext;
    private List<MainMenuDashboard> albumList;
    private static final int SETTINGS_RESULT = 1;

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


    public MainMenuDashboardAdapter(Activity mContext, List<MainMenuDashboard> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.album_card, parent, false);

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

        holder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(holder.overflow);
            }
        });

        holder.relativelayoutAlbumAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                switch (position) {
                    case 0:
                        Intent merchantIntent = new Intent(mContext, MerchantActivity.class);
                        mContext.startActivity(merchantIntent);

                        break;
                    case 1:
                        Intent utiltyIntent = new Intent(mContext, UtilityActivity.class);
                        mContext.startActivity(utiltyIntent);
                        break;
                    case 2:
                        mContext.startActivity(new Intent(mContext, RewardHomeActivity.class));
                        break;
                    case 3:

                        Intent i = new Intent(mContext, MyPreferencesActivity.class);
                        mContext.startActivityForResult(i, SETTINGS_RESULT);
                        break;
                    case 4:

                       // Intent i1 = new Intent(mContext, SettingActivity.class);
                       // mContext.startActivityForResult(i1, SETTINGS_RESULT);
                        break;
                    case 5:
                        WebViewListsActivity.loadList = UtilsConstants.MUSIC_RADIO;
                        mContext.startActivity(new Intent(mContext, WebViewListsActivity.class));
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


    /**
     * Showing popup menu when tapping on 3 dots
     */
    private void showPopupMenu(View view) {
        // inflate menu
        PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_album, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
        popup.show();
    }

    /**
     * Click listener for popup menu items
     */
    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        public MyMenuItemClickListener() {
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_add_favourite:
                    // Toast.makeText(mContext, "Add to favourite", Toast.LENGTH_SHORT).show();
                    AlertDialog dialog = new GeneralFunction().AskOption(mContext);
                    dialog.show();
                    Button nbutton = dialog.getButton(DialogInterface.BUTTON_NEGATIVE);
                    // nbutton.setBackgroundColor(Color.MAGENTA);
                    nbutton.setTextColor(R.color.black);
                    Button pbutton = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
                    // pbutton.setBackgroundColor(Color.YELLOW);
                    pbutton.setTextColor(R.color.black);
//                    AlertDialog alertDialog = new AlertDialog.Builder(mContext).create();
//                    alertDialog.setTitle("Title");
//                    alertDialog.setMessage("Are you ok?");
//                    alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int which) {
//                            // here you can add ok click events
//                        }
//                    });
//                    alertDialog.setIcon(R.drawable.cross_delete);
//
//                    alertDialog.show();
//                    Button nbutton = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
//                    nbutton.setBackgroundColor(Color.MAGENTA);
//                    Button pbutton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
//                    pbutton.setBackgroundColor(Color.YELLOW);
                    return true;
                case R.id.action_play_next:
                    //  Toast.makeText(mContext, "Play next", Toast.LENGTH_SHORT).show();


                    return true;
                default:
            }
            return false;
        }
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }


}
