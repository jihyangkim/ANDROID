package com.dareum.wlgid.dareum_app.Login;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dareum.wlgid.dareum_app.SwipeActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by wlgid on 2017-10-19.
 */

public class LDataParser extends AsyncTask<Void,Void,Boolean> {
    Context c;
    String jsonData,go,imei;
    ImageView gen;
    TextView nick,im;
    LSpacecraftRank s;

    ArrayList<LSpacecraftRank> spacecrafts1 = new ArrayList<>();

    public LDataParser(String go,Context c, String jsonData,ImageView gen,TextView nick,TextView im) {
        this.go = go;
        this.c = c;
        this.jsonData = jsonData;
        this.gen=gen;
        this.nick=nick;
        this.im=im;
    }

    public LDataParser(String go,Context c, String jsonData,String imei) {
        this.go = go;
        this.c = c;
        this.jsonData = jsonData;
        this.imei=imei;
    }

    @Override
    protected void onPreExecute() {super.onPreExecute();}

    @Override
    protected Boolean doInBackground(Void... params) {
        return this.parseData();
    }

    @Override
    protected void onPostExecute(Boolean parsed) {
        super.onPostExecute(parsed);

        if (parsed){
            if(go.equals("select1")) {
                //BIND
                s = spacecrafts1.get(0);//어차피 결과는 하나니까 인덱스는 0으로
                LPicassosel.downloadImage(c, s.getGender(), gen);
                nick.setText(s.getLove());
                im.setText(s.getImei());
            }
            else if (go.equals("select2")){
                s = spacecrafts1.get(0);
                if(imei.equals(s.getImei())){
                    Log.d("hjjj","true");
                    Intent intent = new Intent(c,SwipeActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    c.startActivity(intent);
                }
            }
        }else {
            if(go.equals("select2")){
                Intent intent = new Intent(c,IntentActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                c.startActivity(intent);
            }//아마 회원정보가 없으면 false값이 반환되는 듯함
            //Toast.makeText(c,"Unable to Parser",Toast.LENGTH_SHORT).show();
        }
    }

    private Boolean parseData() {
        try{
            JSONArray ja = new JSONArray(jsonData);
            JSONObject jo;

            spacecrafts1.clear();
            LSpacecraftRank spacecraftRank1;

            for (int i=0; i<ja.length(); i++){
                jo = ja.getJSONObject(i);

                String gender = jo.getString("gender");
                String love = jo.getString("love");
                String imei = jo.getString("imei");

                spacecraftRank1 = new LSpacecraftRank();

                spacecraftRank1.setGender(gender);
                spacecraftRank1.setLove(love);
                spacecraftRank1.setImei(imei);

                spacecrafts1.add(spacecraftRank1);
            }
            return true;

        } catch (JSONException eve) {
            eve.printStackTrace();
        }
        return false;
    }

}