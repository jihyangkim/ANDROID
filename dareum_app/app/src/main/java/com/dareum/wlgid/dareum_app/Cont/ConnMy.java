package com.dareum.wlgid.dareum_app.Cont;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by wlgid on 2017-06-15.
 */

public class ConnMy {
    public static HttpURLConnection connect1(String urlAddress){
        try{
            URL url = new URL(urlAddress);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();

            con.setRequestMethod("GET");
            con.setConnectTimeout(15000);
            con.setConnectTimeout(15000);
            con.setDoInput(true);
            return con;
        } catch (MalformedURLException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
