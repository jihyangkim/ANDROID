package com.dareum.wlgid.dareum_app.SgoServer;

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

public class SDataParser extends AsyncTask<Void,Void,Boolean> {
    Context c;
    String jsonData;
    ListView lv;

    SwipeRefreshLayout swipeRefreshLayout2;
    ArrayList<SSpacecraft> spacecrafts2 = new ArrayList<>();

    public SDataParser(Context c, String jsonData, ListView lv, SwipeRefreshLayout swipeRefreshLayout2) {
        this.c = c;
        this.jsonData = jsonData;
        this.lv = lv;
        this.swipeRefreshLayout2 = swipeRefreshLayout2;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        if(!swipeRefreshLayout2.isRefreshing()){
            swipeRefreshLayout2.setRefreshing(true);
        }
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        return this.parseData();
    }

    @Override
    protected void onPostExecute(Boolean parsed) {
        super.onPostExecute(parsed);

        swipeRefreshLayout2.setRefreshing(false);

        if (parsed){
            //BIND
            SCustomAdapter adapter = new SCustomAdapter(c,spacecrafts2);
            lv.setAdapter(adapter);
        }else {
            Toast.makeText(c,"Unable to Parser",Toast.LENGTH_SHORT).show();
        }
    }

    private Boolean parseData() {
        try{
            JSONArray ja = new JSONArray(jsonData);
            JSONObject jo;

            spacecrafts2.clear();
            SSpacecraft spacecraft2;

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

                spacecraft2 = new SSpacecraft();

                spacecraft2.setNum(num);
                spacecraft2.setImei(imei);
                spacecraft2.setImage(image);
                spacecraft2.setContgo(contgo);
                spacecraft2.setTitle(title);
                spacecraft2.setComgo(comgo);
                spacecraft2.setLikego(likego);
                spacecraft2.setGominca(gominca);
                spacecraft2.setSgominca(sgominca);
                spacecraft2.setW_com(w_com);
                spacecraft2.setM_com(m_com);
                spacecraft2.setA_com(a_com);
                spacecraft2.setB_img(b_img);
                spacecraft2.setTime(time);

                spacecrafts2.add(spacecraft2);
            }
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }
}
