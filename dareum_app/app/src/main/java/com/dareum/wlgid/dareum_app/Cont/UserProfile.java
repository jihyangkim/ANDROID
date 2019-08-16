package com.dareum.wlgid.dareum_app.Cont;

import android.Manifest;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.dareum.wlgid.dareum_app.R;

public class UserProfile extends TabActivity {

    private static final String SCROLL_SPEC = "Scroll";
    private static final String IMAGE_SPEC = "Image";
    private static final String BOOK_SPEC = "Book";
    final static String urlAddress = "http://awse.dothome.co.kr/sssss.php";
    private static final int PERMISSIONS_REQUEST_READ_PHONE_STATE = 999;
    private TelephonyManager mTelephonyManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        ImageView gen = (ImageView)findViewById(R.id.ivUserProfilePhoto);
        TextView chi = (TextView) findViewById(R.id.cldgh);

        if (checkSelfPermission(Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE},
                    PERMISSIONS_REQUEST_READ_PHONE_STATE);
        } else {
            String type = "user";
            new DownMy(getApplicationContext(),urlAddress,type,getDeviceImei(), gen, chi).execute();
        }

        TabHost tabHost = getTabHost();

        //스크롤 탭
        TabHost.TabSpec scrollSpec = tabHost.newTabSpec(SCROLL_SPEC);
        //탭 아이콘
        scrollSpec.setIndicator("",getDrawable(R.drawable.icon_scroll));

        Intent scrollIntent = new Intent(this,Mypage.class);
        //탭 내용
        scrollSpec.setContent(scrollIntent);

        TabHost.TabSpec imageSpec = tabHost.newTabSpec(IMAGE_SPEC);
        imageSpec.setIndicator("",getDrawable(R.drawable.icon_image));

        Intent imageIntent = new Intent(this,Mycomm.class);
        imageSpec.setContent(imageIntent);

        TabHost.TabSpec bookSpec = tabHost.newTabSpec(BOOK_SPEC);
        bookSpec.setIndicator("",getDrawable(R.drawable.icon_like));

        Intent bookIntent = new Intent(this,Mybook.class);
        bookSpec.setContent(bookIntent);
        tabHost.addTab(scrollSpec);
        tabHost.addTab(imageSpec);
        tabHost.addTab(bookSpec);

        findViewById(R.id.pro_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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


}
