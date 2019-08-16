package com.dareum.wlgid.dareum_app.Comm;

import android.content.Context;
import android.os.AsyncTask;

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
 * Created by wlgid on 2017-05-11.
 */

public class Worker extends AsyncTask<String,Void,String> {
    Context context;
    //AlertDialog alertDialog;
    public Worker(Context ctxt){
        context = ctxt;
    }
    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        String url1 = "http://awse.dothome.co.kr/Comtest.php";
        if (type.equals("insert")){
            try {
                //String user_id = params[2];
                //String user_img = params[3];
                String content = params[1];
                String imei = params[2];
                String imageurl = params[3];
                String num = params[4];
                URL url = new URL(url1);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("content","UTF-8")+"="+URLEncoder.encode(content,"UTF-8")+"&"
                        + URLEncoder.encode("imei","UTF-8")+"="+URLEncoder.encode(imei,"UTF-8")+"&"
                        + URLEncoder.encode("imageurl","UTF-8")+"="+URLEncoder.encode(imageurl,"UTF-8")+"&"
                        + URLEncoder.encode("num","UTF-8")+"="+URLEncoder.encode(num,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null){
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return  result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /*@Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("SUCCESSFULL!!");
    }

    @Override
    protected void onPostExecute(String result) {
        alertDialog.setMessage(result);
        alertDialog.show();
    }*/

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
