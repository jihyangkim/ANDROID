package com.dareum.wlgid.dareum_app.Login;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.dareum.wlgid.dareum_app.Worker;
import com.dareum.wlgid.dareum_app.R;

public class IntentActivity extends AppCompatActivity {

    private static final int PERMISSIONS_REQUEST_READ_PHONE_STATE = 999;
    private TelephonyManager mTelephonyManager;
    Spinner spgender,splove;
    TextView result_imei,l_love;
    String gender,love;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent);

        if (checkSelfPermission(Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE},
                    PERMISSIONS_REQUEST_READ_PHONE_STATE);
        } else {
            getDeviceImei();
        }//권한설정

        //getDeviceImei();

        // spgender -> adapter1
        spgender = (Spinner) findViewById(R.id.spgender);
        spgender.setPrompt("성별");
        ArrayAdapter adapter1 = ArrayAdapter.createFromResource(this,
                R.array.gender, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spgender.setAdapter(adapter1);

        spgender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String l_gender = spgender.getSelectedItem().toString();
                if(l_gender.equals("여자")){gender="http://awse.dothome.co.kr/picture/user_1.png";}
                if(l_gender.equals("남자")){gender="http://awse.dothome.co.kr/picture/user_3.png";}
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // splove  -> adapter2
        splove = (Spinner) findViewById(R.id.splove);

        splove.setPrompt("연애 횟수");
        ArrayAdapter adapter2 = ArrayAdapter.createFromResource(this,
                R.array.love, android.R.layout.simple_spinner_item);

        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        splove.setAdapter(adapter2);

        splove.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                love = splove.getSelectedItem().toString();
                l_love = (TextView)findViewById(R.id.l_love);

                if(love.equals("모태")){l_love.setText("마법사");}
                if(love.equals("1 ~ 3 회")){l_love.setText("연애초보");}
                if(love.equals("4 ~ 7 회")){l_love.setText("연애중수");}
                if(love.equals("8회 이상")){l_love.setText("연애고수");}
                if(love.equals("결혼성공")){l_love.setText("신");}
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
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
        Log.d("msg", "DeviceImei " + deviceid);

        result_imei = (TextView)findViewById(R.id.l_imei);
        result_imei.setText(deviceid);
        return deviceid;
    }

    public void OnInsert(View view) {
        String welove = l_love.getText().toString();

        String imei = result_imei.getText().toString();
        String type = "insert1";
        Worker backgroundWorker = new Worker(this);
        backgroundWorker.execute(type,gender,welove,imei);

        Toast.makeText(getApplicationContext(),"가입완료",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(),NewbieWelcome.class));
    }

    @Override
    public void onBackPressed() {
    }
}