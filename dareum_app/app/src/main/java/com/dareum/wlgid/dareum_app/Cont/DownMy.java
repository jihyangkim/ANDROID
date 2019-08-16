package com.dareum.wlgid.dareum_app.Cont;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by wlgid on 2017-06-15.
 */

public class DownMy extends AsyncTask<Void,Void,String> {
    Context c;
    String urlAddress,imei,type;
    ListView lv;
    ImageView gen;
    TextView chi;

    public DownMy(Context c, String urlAddress,String type,ListView lv) {
        this.c = c;
        this.urlAddress = urlAddress;
        this.type = type;
        this.lv = lv;
    }

    public DownMy(Context c, String urlAddress,String type,String imei,ListView lv) {
        this.c = c;
        this.urlAddress = urlAddress;
        this.type = type;
        this.imei=imei;
        this.lv = lv;
    }

    public DownMy(Context c,String urlAddress,String type,String imei, ImageView gen, TextView chi) {
        this.c = c;
        this.urlAddress=urlAddress;
        this.type = type;
        this.imei=imei;
        this.gen=gen;
        this.chi=chi;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(Void... params) {
        if(type.equals("user")||type.equals("mypage")||type.equals("comment")) {
            try {
                URL url = new URL(urlAddress);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("imei", "UTF-8") + "=" + URLEncoder.encode(imei, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return this.downloadData();
    }

    @Override
    protected void onPostExecute(String jsonData) {
        super.onPostExecute(jsonData);


        if (jsonData==null){
            Toast.makeText(c,"Unsuccessful,No Data Retrieved",Toast.LENGTH_SHORT).show();
        }else {
            //PARSER
            if(type.equals("user")) {
                new DataMy(c,type,jsonData,gen,chi).execute();
            }
            else if(type.equals("content")||type.equals("mypage")||type.equals("comment")) {
                new DataMy(c,type,jsonData,lv).execute();
            }
        }
    }

    private String downloadData(){
        HttpURLConnection con = ConnMy.connect1(urlAddress);
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
