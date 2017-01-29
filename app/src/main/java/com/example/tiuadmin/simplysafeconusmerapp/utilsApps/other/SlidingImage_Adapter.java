package com.example.tiuadmin.simplysafeconusmerapp.utilsApps.other;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tiuadmin.simplysafeconusmerapp.R;

import java.util.ArrayList;

/**
 * Created by SONU on 29/08/15.
 */
public class SlidingImage_Adapter extends PagerAdapter {

    //private ArrayList<String> IMAGES;
    private LayoutInflater inflater;
    private Context context;
    ArrayList<Integer> setget_new;

    public SlidingImage_Adapter(Context context, ArrayList<Integer> setget_new) {
        this.context = context;
        this.setget_new = setget_new;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return setget_new.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view, final int position) {
        View imageLayout = inflater.inflate(R.layout.slidingimages_layout, view, false);

        assert imageLayout != null;
        final ImageView imageView = (ImageView) imageLayout
                .findViewById(R.id.slider_imageView);
        final TextView tv_name = (TextView) imageLayout
                .findViewById(R.id.slider_name_textView);
        final TextView tv_price = (TextView) imageLayout
                .findViewById(R.id.slider_price_textView);

        imageView.setImageResource(setget_new.get(position));

//        tv_name.setText(setget_new.get(position).getTitle());
//        tv_price.setText(CommonUtilsInterface.RUPEE_TEXT + " " + setget_new.get(position).getPrice());
//        try {
//            Picasso.with(context)
//                    .load(setget_new.get(position).getImage_path())
//                    .placeholder(R.mipmap.home_slider1).into(imageView);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        view.addView(imageLayout, 0);

//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //ToastMessage.toastMsgLong(context, "" + setget_new.get(position));
//                DetailPageActivity.main_list_position = setget_new.get(position).getMain_list_position();
//                DetailPageFragment1.str_details = setget_new.get(position).getDescription();
//                DetailPageActivity.str_price = "" + setget_new.get(position).getPrice();
//                DetailPageActivity.str_img_path = setget_new.get(position).getImage_path();
//                DetailPageActivity.str_title = setget_new.get(position).getTitle();
//                DetailPageActivity.str_id = setget_new.get(position).getId();
//                DetailPageActivity.str_veg_nonveg = setget_new.get(position).getIsVegNonVeg();
//                context.startActivity(new Intent(context, DetailPageActivity.class));
//            }
//        });

        return imageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }


}
