package com.example.tiuadmin.simplysafeconusmerapp.Utility;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class SquareLinearLayout extends LinearLayout {
    static int a = 0;

    public SquareLinearLayout(Context paramContext) {
        super(paramContext);
    }

    public SquareLinearLayout(Context paramContext,
                              AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet);
    }

    public SquareLinearLayout(Context paramContext,
                              AttributeSet paramAttributeSet, int paramInt) {
        super(paramContext, paramAttributeSet, paramInt);
    }

    protected void onMeasure(int paramInt1, int paramInt2) {
        super.onMeasure(paramInt1, paramInt2);
        //int widthh = getMeasuredWidth() / 4;
        setMeasuredDimension(getMeasuredWidth(), getMeasuredWidth());
    }
}
