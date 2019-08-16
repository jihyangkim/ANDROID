package com.dareum.wlgid.dareum_app.Login;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dareum.wlgid.dareum_app.R;
import com.dareum.wlgid.dareum_app.SwipeActivity;

public class NewbieWelcome extends AppCompatActivity {

    final static String urlAddress = "http://awse.dothome.co.kr/sssss.php";
    private static final int PERMISSIONS_REQUEST_READ_PHONE_STATE = 999;
    private TelephonyManager mTelephonyManager;
    ImageView gen;
    TextView nick,im;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newbie_welcome);

        if (checkSelfPermission(Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE},
                    PERMISSIONS_REQUEST_READ_PHONE_STATE);
        } else {
            String imei = getDeviceImei();

            gen = (ImageView) findViewById(R.id.gender_img);
            nick = (TextView) findViewById(R.id.result_id);
            im = (TextView) findViewById(R.id.result_imei);

            String go = "select1";
            new LDownload(getApplicationContext(), urlAddress, gen, nick, im, imei, go).execute();
        }

        findViewById(R.id.previousButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),SwipeActivity.class));
                Toast.makeText(getApplicationContext(),"다름에 오신 것을 환영합니다.",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_READ_PHONE_STATE
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            getDeviceImei();
        }
    }

    private String getDeviceImei() {
        mTelephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String deviceid = mTelephonyManager.getDeviceId();
        return deviceid;
    }

    @Override
    public void onBackPressed() {
    }


}