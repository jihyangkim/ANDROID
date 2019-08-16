package com.dareum.wlgid.dareum_app.Cont;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.dareum.wlgid.dareum_app.R;

public class Mybook extends AppCompatActivity {
    final static String urlAddress = "http://awse.dothome.co.kr/gomin_select.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mybook);

        final ListView lv = (ListView)findViewById(R.id.mybook);
        String type = "content";
        new DownMy(getApplicationContext(),urlAddress,type,lv).execute();
    }
}
