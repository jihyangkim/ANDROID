package com.dareum.wlgid.dareum_app.GoServer;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dareum.wlgid.dareum_app.Write.MainWrite;
import com.dareum.wlgid.dareum_app.R;

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
            convertView = LayoutInflater.from(c).inflate(R.layout.model,parent,false);
        }

        TextView mod_txt1 = (TextView)convertView.findViewById(R.id.mod_txt1);
        TextView mod_txt2 = (TextView)convertView.findViewById(R.id.mod_txt2);
        TextView mod_txt3 = (TextView)convertView.findViewById(R.id.mod_txt3);
        TextView mod_txt4 = (TextView)convertView.findViewById(R.id.mod_txt4);
        TextView mod_txt5 = (TextView)convertView.findViewById(R.id.mod_txt5);
        ImageView img = (ImageView)convertView.findViewById(R.id.spacecraftImage);

        final Spacecraft s = (Spacecraft)this.getItem(position);

        mod_txt1.setText(s.getContgo());
        mod_txt2.setText(s.getTitle());
        mod_txt3.setText(s.getLikego());
        mod_txt4.setText(s.getComgo());
        mod_txt5.setText(s.getTime());
        PicassoClient.downloadImage(c,s.getImage(),img);

        //ITEM CLICK
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //OPEN DETAIL ACTIVITY
                openWriteMain(s.getImage(),s.getTitle(),s.getContgo(),s.getLikego(),s.getComgo(), s.getTime(), s.getNum(),s.getB_img());
            }
        });
        return convertView;
    }

    //OPEN DETAIL ACTIVITY
    private void openWriteMain(String imgUrl,String go_title,String cont_go, String like_go, String com_go, String time, int num, String b_img){
        Intent i = new Intent(c,MainWrite.class);

        //DATA
        i.putExtra("IMAGEURL_KEY",imgUrl);
        i.putExtra("TITLE_KEY",go_title);
        i.putExtra("CONT_KEY",cont_go);
        i.putExtra("LIKE_KEY",like_go);
        i.putExtra("COM_KEY",com_go);
        i.putExtra("TIME_KEY",time);
        i.putExtra("ID_KEY",num);
        i.putExtra("BIMG_KEY",b_img);

        c.startActivity(i);
    }
}