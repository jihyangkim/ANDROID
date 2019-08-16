package com.dareum.wlgid.dareum_app.Ranking;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

/**
 * Created by wlgid on 2017-06-15.
 */

public class DownRank1 extends AsyncTask<Void,Void,String> {
    Context c;
    String urlAddress;
    ListView lv;


    public DownRank1(Context c, String urlAddress, ListView lv) {
        this.c = c;
        this.urlAddress = urlAddress;
        this.lv = lv;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected String doInBackground(Void... params) {
        return this.downloadData();
    }

    @Override
    protected void onPostExecute(String jsonData) {
        super.onPostExecute(jsonData);


        if (jsonData==null){
            Toast.makeText(c,"Unsuccessful,No Data Retrieved",Toast.LENGTH_SHORT).show();
        }else {
            //PARSER
            new DParserRank1(c,jsonData,lv).execute();
        }
    }

    private String downloadData(){
        HttpURLConnection con = ConRank.connect1(urlAddress);
        if (con==null){
            return null;
        }
        try{
            InputStream is = new BufferedInputStream(con.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String line;
            StringBuffer jsonData = new StringBuffer();

            while ((line = br.readLine()) != null){
                jsonData.append(line + "\n");
            }

            br.close();
            is.close();
            return jsonData.toString();
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
