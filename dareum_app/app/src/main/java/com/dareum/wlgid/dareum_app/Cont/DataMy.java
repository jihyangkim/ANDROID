package com.dareum.wlgid.dareum_app.Cont;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by wlgid on 2017-06-15.
 */

public class DataMy extends AsyncTask<Void,Void,Boolean> {
    Context c;
    String jsonData,type;
    ListView lv;
    ImageView gen;
    TextView chi;
    USpacecraftMy u;

    ArrayList<USpacecraftMy> spacecrafts0 = new ArrayList<>();
    ArrayList<SpacecraftMy> spacecrafts1 = new ArrayList<>();

    public DataMy(Context c, String type,String jsonData, ListView lv) {
        this.c = c;
        this.type = type;
        this.jsonData = jsonData;
        this.lv = lv;
    }

    public DataMy(Context c, String type,String jsonData,ImageView gen, TextView chi) {
        this.c = c;
        this.type = type;
        this.jsonData=jsonData;
        this.gen = gen;
        this.chi = chi;
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
            if(type.equals("user")) {
                u = spacecrafts0.get(0);
                PicassoClient.downloadImage(c,u.getGender(),gen);
                chi.setText(u.getLove());
            }
            else if (type.equals("content")||type.equals("mypage")||type.equals("comment")) {
                CustomMy adapter = new CustomMy(c,spacecrafts1);
                lv.setAdapter(adapter);
            }
        }else {
            Toast.makeText(c,"Unable to Parser",Toast.LENGTH_SHORT).show();
        }
    }

    private Boolean parseData() {
        try{
            JSONArray ja = new JSONArray(jsonData);
            JSONObject jo;

            if(type.equals("user")){
                spacecrafts0.clear();
                USpacecraftMy uspacecraftMy;

                for (int i = 0;i < ja.length(); i++){
                    jo = ja.getJSONObject(i);

                    String gender = jo.getString("gender");
                    String love = jo.getString("love");
                    String imei = jo.getString("imei");

                    uspacecraftMy = new USpacecraftMy();

                    uspacecraftMy.setGender(gender);
                    uspacecraftMy.setLove(love);
                    uspacecraftMy.setImei(imei);

                    spacecrafts0.add(uspacecraftMy);
                }
            }
            else if(type.equals("content")||type.equals("mypage")||type.equals("comment")) {
                spacecrafts1.clear();
                SpacecraftMy spacecraftMy1;

                for (int i = 0; i < ja.length(); i++) {
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

                    spacecraftMy1 = new SpacecraftMy();

                    spacecraftMy1.setNum(num);
                    spacecraftMy1.setImei(imei);
                    spacecraftMy1.setImage(image);
                    spacecraftMy1.setContgo(contgo);
                    spacecraftMy1.setTitle(title);
                    spacecraftMy1.setComgo(comgo);
                    spacecraftMy1.setLikego(likego);
                    spacecraftMy1.setGominca(gominca);
                    spacecraftMy1.setSgominca(sgominca);
                    spacecraftMy1.setW_com(w_com);
                    spacecraftMy1.setM_com(m_com);
                    spacecraftMy1.setA_com(a_com);
                    spacecraftMy1.setB_img(b_img);
                    spacecraftMy1.setTime(time);

                    spacecrafts1.add(spacecraftMy1);
                }
            }
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }

}
