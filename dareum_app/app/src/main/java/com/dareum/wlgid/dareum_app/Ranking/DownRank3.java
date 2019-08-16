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

public class DownRank3 extends AsyncTask<Void,Void,String> {
    Context c;
    String urlAddress3;
    ListView lv3;


    public DownRank3(Context c, String urlAddress3, ListView lv3) {
        this.c = c;
        this.urlAddress3 = urlAddress3;
        this.lv3 = lv3;
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
            new DParserRank3(c,jsonData,lv3).execute();
        }
    }

    private String downloadData(){
        HttpURLConnection con = ConRank.connect1(urlAddress3);
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
