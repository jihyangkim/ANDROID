package com.dareum.wlgid.dareum_app.SearchRecently;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

/**
 * Created by wlgid on 2017-06-19.
 */

public class SenderReceiver extends AsyncTask<Void,Void,String> {
    Context c;
    String urlAddress;
    String title;
    ListView lv;
    TextView noDataImg,noNetworkImg;
    ProgressDialog pd;
    public SenderReceiver(Context c, String urlAddress, String title, ListView lv, TextView...textViews) {
        this.c = c;
        this.urlAddress = urlAddress;
        this.title = title;
        this.lv = lv;
        this.noDataImg=textViews[0];
        this.noNetworkImg=textViews[1];
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd=new ProgressDialog(c);
        pd.setTitle("Search");
        pd.setMessage("Searching...Please wait");
        pd.show();
    }
    @Override
    protected String doInBackground(Void... params) {
        return this.sendAndReceive();
    }
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        pd.dismiss();
        //RESET LISTVIEW
        lv.setAdapter(null);
        if(s != null)
        {
            if(!s.contains("null"))
            {
                Parser p=new Parser(c,s,lv);
                p.execute();
                noNetworkImg.setVisibility(View.INVISIBLE);
                noDataImg.setVisibility(View.INVISIBLE);
            }else
            {
                noNetworkImg.setVisibility(View.INVISIBLE);
                noDataImg.setVisibility(View.VISIBLE);
            }
        }else {
            noNetworkImg.setVisibility(View.VISIBLE);
            noDataImg.setVisibility(View.INVISIBLE);
        }
    }
    private String sendAndReceive()
    {
        HttpURLConnection con=Connector.connect(urlAddress);
        if(con==null)
        {
            return null;
        }
        try
        {
            OutputStream os=con.getOutputStream();
            BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(os));
            bw.write(new DataPackager(title).packageData());
            bw.flush();
            //RELEASE RES
            bw.close();
            os.close();
            //SOME RESPONSE ????
            int responseCode=con.getResponseCode();
            //DECODE
            if(responseCode==con.HTTP_OK)
            {
                //RETURN SOME DATA stream
                InputStream is=con.getInputStream();
                //READ IT
                BufferedReader br=new BufferedReader(new InputStreamReader(is));
                String line;
                StringBuffer response=new StringBuffer();
                if(br != null)
                {
                    while ((line=br.readLine()) != null)
                    {
                        response.append(line+"n");
                    }
                }else {
                    return null;
                }
                return response.toString();
            }else {
                return String.valueOf(responseCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
