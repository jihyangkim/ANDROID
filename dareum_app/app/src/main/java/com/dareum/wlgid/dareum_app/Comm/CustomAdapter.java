package com.dareum.wlgid.dareum_app.Comm;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dareum.wlgid.dareum_app.R;
import com.dareum.wlgid.dareum_app.Write.MainWrite;

import java.util.ArrayList;

/**
 * Created by wlgid on 2017-06-15.
 */

public class CustomAdapter extends BaseAdapter {
    Context c;
    ArrayList<Spacecraft> spacecrafts;

    public CustomAdapter(Context c, ArrayList<Spacecraft> spacecrafts) {
        this.c = c;
        this.spacecrafts = spacecrafts;
    }

    @Override
    public int getCount() {
        return spacecrafts.size();
    }

    @Override
    public Object getItem(int position) {
        return spacecrafts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            convertView = LayoutInflater.from(c).inflate(R.layout.comser_item,parent,false);
        }

        TextView mod_txt1 = (TextView)convertView.findViewById(R.id.ser_cont);
        ImageView img = (ImageView)convertView.findViewById(R.id.img_c);

        final Spacecraft s = (Spacecraft)this.getItem(position);

        mod_txt1.setText(s.getContent());
        PicassoClient.downloadImage(c,s.getImageurl(),img);

        return convertView;
    }
}