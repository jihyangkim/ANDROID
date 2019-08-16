package com.dareum.wlgid.dareum_app;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

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
        String insert_url1 = "http://awse.dothome.co.kr/gendertest.php";
        String insert_url2 = "http://awse.dothome.co.kr/writetest.php";
        String insert_url3 = "http://awse.dothome.co.kr/like_insert.php";
        if (type.equals("insert1")){
            try {
                String gender = params[1];
                String love = params[2];
                String imei = params[3];
                URL url = new URL(insert_url1);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("gender","UTF-8")+"="+URLEncoder.encode(gender,"UTF-8")+"&"
                        + URLEncoder.encode("love","UTF-8")+"="+URLEncoder.encode(love,"UTF-8")+"&"
                        + URLEncoder.encode("imei","UTF-8")+"="+URLEncoder.encode(imei,"UTF-8");
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
        }else if (type.equals("insert2")){
            try {
                String imei = params[1];
                String gominca = params[2];
                String sgominca = params[3];
                String w_com = params[4];
                String m_com = params[5];
                String a_com = params[6];
                String b_img = params[7];
                String image = params[8];
                String comgo = params[9];
                String likego = params[10];
                String title = params[11];
                String contgo = params[12];
                URL url = new URL(insert_url2);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("imei","UTF-8")+"="+URLEncoder.encode(imei,"UTF-8")+"&"
                        + URLEncoder.encode("gominca","UTF-8")+"="+URLEncoder.encode(gominca,"UTF-8")+"&"
                        + URLEncoder.encode("sgominca","UTF-8")+"="+URLEncoder.encode(sgominca,"UTF-8")+"&"
                        + URLEncoder.encode("w_com","UTF-8")+"="+URLEncoder.encode(w_com,"UTF-8")+"&"
                        + URLEncoder.encode("m_com","UTF-8")+"="+URLEncoder.encode(m_com,"UTF-8")+"&"
                        + URLEncoder.encode("a_com","UTF-8")+"="+URLEncoder.encode(a_com,"UTF-8")+"&"
                        + URLEncoder.encode("b_img","UTF-8")+"="+URLEncoder.encode(b_img,"UTF-8")+"&"
                        + URLEncoder.encode("image","UTF-8")+"="+URLEncoder.encode(image,"UTF-8")+"&"
                        + URLEncoder.encode("comgo","UTF-8")+"="+URLEncoder.encode(comgo,"UTF-8")+"&"
                        + URLEncoder.encode("likego","UTF-8")+"="+URLEncoder.encode(likego,"UTF-8")+"&"
                        + URLEncoder.encode("title","UTF-8")+"="+URLEncoder.encode(title,"UTF-8")+"&"
                        + URLEncoder.encode("contgo","UTF-8")+"="+URLEncoder.encode(contgo,"UTF-8");
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
        }else if (type.equals("insert3")){
            try {
                String imei = params[1];
                String num = params[2];
                String tr_false = params[3];
                URL url = new URL(insert_url3);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("imei","UTF-8")+"="+URLEncoder.encode(imei,"UTF-8")+"&"
                        + URLEncoder.encode("num","UTF-8")+"="+URLEncoder.encode(num,"UTF-8")+"&"
                        + URLEncoder.encode("tr_false","UTF-8")+"="+URLEncoder.encode(tr_false,"UTF-8");
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
