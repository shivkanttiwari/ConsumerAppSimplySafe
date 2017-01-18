package com.example.tiuadmin.simplysafeconusmerapp.Utility;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by admin on 6/14/2016.
 */
public class SquareImageView extends ImageView {
    static int a = 0;

    public SquareImageView(Context paramContext) {
        super(paramContext);
    }

    public SquareImageView(Context paramContext,
                           AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet);
    }

    public SquareImageView(Context paramContext,
                           AttributeSet paramAttributeSet, int paramInt) {
        super(paramContext, paramAttributeSet, paramInt);
    }

    protected void onMeasure(int paramInt1, int paramInt2) {
        super.onMeasure(paramInt1, paramInt2);
        //int widthh = getMeasuredWidth() / 4;
        setMeasuredDimension(getMeasuredWidth(), getMeasuredWidth() );
    }
}
