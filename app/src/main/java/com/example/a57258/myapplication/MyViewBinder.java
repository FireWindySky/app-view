package com.example.a57258.myapplication;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

public class MyViewBinder implements SimpleAdapter.ViewBinder {

    @Override
    public boolean setViewValue(View view, Object data, String textRepresentation) {
        if ((view instanceof ImageView) && (data instanceof Bitmap)) {
            ImageView iv = (ImageView) view;
            Bitmap bm = (Bitmap) data;
            iv.setImageBitmap(bm);
            return true;
        }
        return false;
    }

}
