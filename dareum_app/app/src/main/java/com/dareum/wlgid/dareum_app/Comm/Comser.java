package com.dareum.wlgid.dareum_app.Comm;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.dareum.wlgid.dareum_app.GoServer.*;
import com.dareum.wlgid.dareum_app.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by wlgid on 2017-09-26.
 */

public class Comser extends AppCompatActivity {
    //댓글입력(EditText)
    EditText edit;
    TextView imei_c, num_c, imgurl_c;

    final static String urlAddress = "http://awse.dothome.co.kr/Comsertest.php";

    private static final int PERMISSIONS_REQUEST_READ_PHONE_STATE = 999;
    private TelephonyManager mTelephonyManager;

    ListView lv;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comser_main);

        final Intent intent = getIntent();

        String cnum, cimgurl;

        cimgurl = intent.getStringExtra("img_c");
        cnum = intent.getStringExtra("num_c");

        imgurl_c = (TextView)findViewById(R.id.c_imgurl);
        num_c = (TextView)findViewById(R.id.c_num);

        imgurl_c.setText(cimgurl);
        num_c.setText(cnum);

        //imei 불러오기
        if (checkSelfPermission(Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE},
                    PERMISSIONS_REQUEST_READ_PHONE_STATE);
        } else {
            getDeviceImei();
        }

        //댓글입력(EditText)
        edit = (EditText)findViewById(R.id.ser_edit);

        lv = (ListView)findViewById(R.id.ser_list);
        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipe);

        new Downloader(getApplicationContext(),urlAddress,lv,swipeRefreshLayout,num_c.getText().toString()).execute();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Downloader(getApplicationContext(),urlAddress,lv,swipeRefreshLayout,num_c.getText().toString()).execute();
            }
        });

    }

    private String getDeviceImei() {
        mTelephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String deviceid = mTelephonyManager.getDeviceId();
        Log.d("msg", "DeviceImei " + deviceid);

        imei_c = (TextView)findViewById(R.id.c_imei);
        imei_c.setText(deviceid);
        return deviceid;
    }

    @Override
    protected void onResume() {
        super.onResume();
        new Handler().postDelayed(new Runnable() {
            public void run() {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.showSoftInput((EditText) findViewById(R.id.wr_content_edit),InputMethodManager.SHOW_IMPLICIT);
            }
        }, 100);
    }

    //댓글입력(실행코드)
    public void OnInsert(View view) {
        String ser_content = edit.getText().toString();
        String imei = imei_c.getText().toString();
        String imageurl = imgurl_c.getText().toString();
        String num = num_c.getText().toString();
        String type = "insert";
        Worker backgroundWorker = new Worker(this);
        backgroundWorker.execute(type, ser_content, imei, imageurl, num);
        new Downloader(getApplicationContext(),urlAddress,lv,swipeRefreshLayout,num_c.getText().toString()).execute();
        edit.setText("");
        Toast.makeText(getApplicationContext(),"글 작성완료!",Toast.LENGTH_SHORT).show();
    }
}
