package com.dareum.wlgid.dareum_app.Ranking;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by wlgid on 2017-06-15.
 */

public class DParserRank2 extends AsyncTask<Void,Void,Boolean> {
    Context c;
    String jsonData;
    ListView lv2;

    ArrayList<SpacecraftRank> spacecrafts1 = new ArrayList<>();

    public DParserRank2(Context c, String jsonData, ListView lv2) {
        this.c = c;
        this.jsonData = jsonData;
        this.lv2 = lv2;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected Boolean doInBackground(Void... params) {
        return this.parseData();
    }

    @Override
    protected void onPostExecute(Boolean parsed) {
        super.onPostExecute(parsed);


        if (parsed){
            //BIND
            CustomRank2 adapter = new CustomRank2(c,spacecrafts1);
            lv2.setAdapter(adapter);
        }else {
            Toast.makeText(c,"Unable to Parser",Toast.LENGTH_SHORT).show();
        }
    }

    private Boolean parseData() {
        try{
            JSONArray ja = new JSONArray(jsonData);
            JSONObject jo;

            spacecrafts1.clear();
            SpacecraftRank spacecraftRank1;

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

                spacecraftRank1 = new SpacecraftRank();

                spacecraftRank1.setNum(num);
                spacecraftRank1.setImei(imei);
                spacecraftRank1.setImage(image);
                spacecraftRank1.setContgo(contgo);
                spacecraftRank1.setTitle(title);
                spacecraftRank1.setComgo(comgo);
                spacecraftRank1.setLikego(likego);
                spacecraftRank1.setGominca(gominca);
                spacecraftRank1.setSgominca(sgominca);
                spacecraftRank1.setW_com(w_com);
                spacecraftRank1.setM_com(m_com);
                spacecraftRank1.setA_com(a_com);
                spacecraftRank1.setB_img(b_img);
                spacecraftRank1.setTime(time);

                spacecrafts1.add(spacecraftRank1);
            }
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }
}
