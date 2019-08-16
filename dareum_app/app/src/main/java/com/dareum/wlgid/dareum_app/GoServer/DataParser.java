package com.dareum.wlgid.dareum_app.GoServer;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by wlgid on 2017-06-15.
 */

public class DataParser extends AsyncTask<Void,Void,Boolean> {
    Context c;
    String jsonData;
    ListView lv;

    SwipeRefreshLayout swipeRefreshLayout;
    ArrayList<Spacecraft> spacecrafts1 = new ArrayList<>();

    public DataParser(Context c, String jsonData, ListView lv, SwipeRefreshLayout swipeRefreshLayout) {
        this.c = c;
        this.jsonData = jsonData;
        this.lv = lv;
        this.swipeRefreshLayout = swipeRefreshLayout;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        if(!swipeRefreshLayout.isRefreshing()){
            swipeRefreshLayout.setRefreshing(true);
        }
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        return this.parseData();
    }

    @Override
    protected void onPostExecute(Boolean parsed) {
        super.onPostExecute(parsed);

        swipeRefreshLayout.setRefreshing(false);

        if (parsed){
            //BIND
            CustomAdapter adapter = new CustomAdapter(c,spacecrafts1);
            lv.setAdapter(adapter);
        }else {
            Toast.makeText(c,"Unable to Parser",Toast.LENGTH_SHORT).show();
        }
    }

    private Boolean parseData() {
        try{
            JSONArray ja = new JSONArray(jsonData);
            JSONObject jo;

            spacecrafts1.clear();
            Spacecraft spacecraft1;

            for (int i=0; i<ja.length(); i++){
                jo = ja.getJSONObject(i);

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

                spacecraft1 = new Spacecraft();

                spacecraft1.setNum(num);
                spacecraft1.setImei(imei);
                spacecraft1.setImage(image);
                spacecraft1.setContgo(contgo);
                spacecraft1.setTitle(title);
                spacecraft1.setComgo(comgo);
                spacecraft1.setLikego(likego);
                spacecraft1.setGominca(gominca);
                spacecraft1.setSgominca(sgominca);
                spacecraft1.setW_com(w_com);
                spacecraft1.setM_com(m_com);
                spacecraft1.setA_com(a_com);
                spacecraft1.setB_img(b_img);
                spacecraft1.setTime(time);

                spacecrafts1.add(spacecraft1);
            }
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }
}
