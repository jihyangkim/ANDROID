package com.dareum.wlgid.dareum_app.Write;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.dareum.wlgid.dareum_app.Comm.Comser;
import com.dareum.wlgid.dareum_app.GoServer.PicassoClient;
import com.dareum.wlgid.dareum_app.R;
import com.dareum.wlgid.dareum_app.SwipeActivity;
import com.dareum.wlgid.dareum_app.Worker;

public class MainWrite extends AppCompatActivity {
    TextView gomintitle, comgo, likego, contgo, time, txt_chk, m_imei, num;
    ImageView img, b_img;
    CheckBox check;
    String imageurl;

    private static final int PERMISSIONS_REQUEST_READ_PHONE_STATE = 999;
    private TelephonyManager mTelephonyManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_write);

        gomintitle = (TextView)findViewById(R.id.det_txt1);
        contgo = (TextView)findViewById(R.id.det_txt4);
        likego = (TextView)findViewById(R.id.det_txt6);
        comgo = (TextView)findViewById(R.id.det_txt7);
        time = (TextView)findViewById(R.id.det_txt8);
        num = (TextView)findViewById(R.id.det_txt9);
        img = (ImageView)findViewById(R.id.det_img1);
        b_img = (ImageView)findViewById(R.id.b_img_go);

        //RECEIVE DATA
        Intent i = this.getIntent();
        String go_title = i.getExtras().getString("TITLE_KEY");
        String cont_go = i.getExtras().getString("CONT_KEY");
        //String like_go = i.getExtras().getString("LIKE_KEY");
        String com_go = i.getExtras().getString("COM_KEY");
        imageurl = i.getExtras().getString("IMAGEURL_KEY");
        String time_g = i.getExtras().getString("TIME_KEY");
        int id_g = i.getExtras().getInt("ID_KEY",0);
        String b_img_g = i.getExtras().getString("BIMG_KEY");


        //BIND
        gomintitle.setText(go_title);
        contgo.setText(cont_go);
        //likego.setText(like_go);
        comgo.setText(com_go);
        PicassoClient.downloadImage(this,imageurl,img);
        time.setText(time_g);
        num.setText(Integer.toString(id_g));
        PicassoClient.downloadImage(this,b_img_g,b_img);

        findViewById(R.id.writ_btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),SwipeActivity.class));
            }
        });
        findViewById(R.id.det_img3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Comser.class);
                intent.putExtra("img_c",imageurl.toString());
                intent.putExtra("num_c",num.getText().toString());
                startActivity(intent);
            }
        });

        //imei 불러오기
        if (checkSelfPermission(Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE},
                    PERMISSIONS_REQUEST_READ_PHONE_STATE);
        } else {
            getDeviceImei();
        }

        check = (CheckBox)findViewById(R.id.det_chcek);
        txt_chk = (TextView)findViewById(R.id.det_txt6);


    }

    private String getDeviceImei() {
        mTelephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String deviceid = mTelephonyManager.getDeviceId();
        Log.d("msg", "DeviceImei " + deviceid);

        m_imei = (TextView)findViewById(R.id.m_imei);
        m_imei.setText(deviceid);
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

    public void OnInsert3(View view) {
        if (check.isChecked()){
            txt_chk.setText("1");

        } else {
            txt_chk.setText("0");
        }
        String imei = m_imei.getText().toString();
        String id = num.getText().toString();
        //int id = Integer.parseInt(num.getText().toString());
        String che_like = txt_chk.getText().toString();
        String type = "insert3";
        Worker backgroundWorker = new Worker(this);
        backgroundWorker.execute(type, imei, id, che_like);
    }
}
