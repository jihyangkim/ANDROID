package com.dareum.wlgid.dareum_app.WriteInsert;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.dareum.wlgid.dareum_app.R;

/**
 * Created by wlgid on 2017-10-10.
 */

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private int displayWidth; //화면크기
    private int size; //이미지 크기
    private int pad; //패딩

    private int[] imageIDs = new int[]{
            R.mipmap.bg_img1,R.mipmap.bg_img2,R.mipmap.bg_img3,
            R.mipmap.bg_img4,R.mipmap.bg_img5,R.mipmap.bg_img6,
            R.mipmap.bg_img7,R.mipmap.bg_img8,R.mipmap.bg_img9,
            R.mipmap.bg_img10,R.mipmap.bg_img11,R.mipmap.bg_img12,
            R.mipmap.bg_img13,R.mipmap.bg_img14,R.mipmap.bg_img15,
            R.mipmap.bg_img16,R.mipmap.bg_img17,R.mipmap.bg_img18,
            R.mipmap.bg_img19,R.mipmap.bg_img20
    };

    public ImageAdapter(Context c, int displayWidth) {
        mContext = c;
        this.displayWidth = displayWidth;
        size = displayWidth/3;
        pad = 2;
    }

    @Override
    public int getCount() {
        return imageIDs.length;
    }

    @Override
    public Object getItem(int position) {
        return imageIDs[position];
    }

    @Override
    public long getItemId(int position) {
        return imageIDs[position];
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            //이후에 이미지 자동크기변경이 필요함
            imageView.setLayoutParams(new GridView.LayoutParams(size , size));
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setPadding(pad,pad,pad,pad);
        } else {
            imageView = (ImageView) convertView;
        }
        imageView.setImageResource(imageIDs[position]);
        return imageView;
    }
}