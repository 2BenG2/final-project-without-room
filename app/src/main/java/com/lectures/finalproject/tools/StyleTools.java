package com.lectures.finalproject.tools;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.core.content.ContextCompat;

import com.lectures.finalproject.R;

public class StyleTools {

    public static void setActionBar(ActionBar actionBar, Context context)
    {
        Drawable drawable = ContextCompat.getDrawable(context, R.drawable.yellow_concrate);
        actionBar.setBackgroundDrawable(drawable);
    }
    public static void setActionBar(ActionBar actionBar, Context context, View view)
    {
        Drawable drawable = ContextCompat.getDrawable(context, R.drawable.yellow_concrate);
        actionBar.setBackgroundDrawable(drawable);
        view.setBackground(drawable);
    }


}
