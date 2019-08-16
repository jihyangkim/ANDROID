package com.dareum.wlgid.dareum_app.Ranking;

import android.content.Context;
import android.widget.ImageView;

import com.dareum.wlgid.dareum_app.R;
import com.squareup.picasso.Picasso;

/**
 * Created by wlgid on 2017-06-15.
 */

public class PicassoClient {
    public static void downloadImage(Context c, String imageUrl, ImageView img)
    {
        if(imageUrl!=null && imageUrl.length()>0)
        {
            Picasso.with(c).load(imageUrl).placeholder(R.drawable.placeholder).into(img);
        }else {
            Picasso.with(c).load(R.drawable.placeholder).into(img);
        }
    }
}
