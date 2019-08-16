package com.dareum.wlgid.dareum_app.Comm;

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
                String imageurl = jo.getString("imageurl");
                String content = jo.getString("content");

                spacecraft1 = new Spacecraft();

                spacecraft1.setNum(num);
                spacecraft1.setImei(imei);
                spacecraft1.setImageurl(imageurl);
                spacecraft1.setContent(content);

                spacecrafts1.add(spacecraft1);
            }
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }
}
