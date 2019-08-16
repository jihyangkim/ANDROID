package com.dareum.wlgid.dareum_app.SearchRecently;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by wlgid on 2017-06-19.
 */

public class Parser extends AsyncTask<Void,Void,Integer> {
    Context c;
    String data;
    ListView lv;
    ArrayList<Spacecraft> spacecrafts=new ArrayList<>();
    public Parser(Context c, String data, ListView lv) {
        this.c = c;
        this.data = data;
        this.lv = lv;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
    @Override
    protected Integer doInBackground(Void... params) {
        return this.parse();
    }
    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
        if(integer==1)
        {
            //BIND TO LISTVIEW
            MyAdapter myAdapter = new MyAdapter(c,spacecrafts);
            lv.setAdapter(myAdapter);
        }else {
            Toast.makeText(c,"Unable to Parse",Toast.LENGTH_SHORT).show();
        }
    }
    private int parse()
    {
        try
        {
            JSONArray ja=new JSONArray(data);
            JSONObject jo=null;
            spacecrafts.clear();
            Spacecraft spacecraft;

            for(int i=0;i<ja.length();i++)
            {
                jo=ja.getJSONObject(i);
                int num = jo.getInt("num");
                String imei = jo.getString("imei");
                String image = jo.getString("image");
                String title = jo.getString("title");
                String contgo = jo.getString("contgo");
                String likego = jo.getString("likego");
                String comgo = jo.getString("comgo");
                String gominca = jo.getString("gominca");
                String sgominca = jo.getString("sgominca");
                String w_com = jo.getString("w_com");
                String m_com = jo.getString("m_com");
                String a_com = jo.getString("a_com");
                String b_img = jo.getString("b_img");
                String time = jo.getString("time");

                spacecraft = new Spacecraft();

                spacecraft.setNum(num);
                spacecraft.setImei(imei);
                spacecraft.setImage(image);
                spacecraft.setContgo(contgo);
                spacecraft.setTitle(title);
                spacecraft.setComgo(comgo);
                spacecraft.setLikego(likego);
                spacecraft.setGominca(gominca);
                spacecraft.setSgominca(sgominca);
                spacecraft.setW_com(w_com);
                spacecraft.setM_com(m_com);
                spacecraft.setA_com(a_com);
                spacecraft.setB_img(b_img);
                spacecraft.setTime(time);

                spacecrafts.add(spacecraft);
            }
            return 1;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
