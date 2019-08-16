package com.dareum.wlgid.dareum_app.WriteInsert;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.dareum.wlgid.dareum_app.R;

public class WrActivity extends AppCompatActivity {
    Button btn_gomin,btn_sgomin,btn_w_only,btn_m_only,btn_both;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wr);

        btn_gomin=(Button)findViewById(R.id.btn_gomin);
        btn_sgomin=(Button)findViewById(R.id.btn_sgomin);
        btn_w_only=(Button)findViewById(R.id.btn_w_only);
        btn_m_only=(Button)findViewById(R.id.btn_m_only);
        btn_both=(Button)findViewById(R.id.btn_both);

        final Intent intent = new Intent(getApplicationContext(),wr_main.class);

        btn_gomin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_gomin.setTextColor(getColor(R.color.colorChange));
                btn_gomin.setBackgroundColor(ContextCompat.getColor(WrActivity.this,R.color.colorMain));// use this to set color as background
                btn_sgomin.setBackgroundResource(R.drawable.blank);// use this to set color as background
                btn_sgomin.setTextColor(getColor(R.color.colorMain));
                /*if(btn_gomin.isClickable()==true){
                    intent.putExtra("Gomin","1");
                }
                if (btn_gomin.isClickable()==false){
                    intent.putExtra("Gomin","0");
                }*/
                intent.putExtra("Gomin","1");
            }
        });
        intent.putExtra("Gomin","0");
        btn_sgomin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_sgomin.setTextColor(getColor(R.color.colorChange));
                btn_sgomin.setBackgroundColor(ContextCompat.getColor(WrActivity.this,R.color.colorMain));// use this to set color as background
                btn_gomin.setBackgroundResource(R.drawable.blank);// use this to set color as background
                btn_gomin.setTextColor(getColor(R.color.colorMain));
                /*if(btn_sgomin.isClickable()==true){
                    intent.putExtra("SGomin","1");
                }
                if (btn_sgomin.isClickable()==false){
                    intent.putExtra("SGomin","0");
                }*/
                intent.putExtra("SGomin","1");
            }
        });
        intent.putExtra("SGomin","0");
        btn_w_only.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_w_only.setTextColor(getColor(R.color.colorChange));
                btn_w_only.setBackgroundColor(ContextCompat.getColor(WrActivity.this,R.color.colorMain));// use this to set color as background
                btn_m_only.setBackgroundResource(R.drawable.blank);// use this to set color as background
                btn_m_only.setTextColor(getColor(R.color.colorMain));
                btn_both.setBackgroundResource(R.drawable.blank);
                btn_both.setTextColor(getColor(R.color.colorMain));
                /*if(btn_w_only.isClickable()==true){
                    intent.putExtra("WOnly","1");
                }
                if (btn_w_only.isClickable()==false){
                    intent.putExtra("WOnly","0");
                }*/
                intent.putExtra("WOnly","1");
            }
        });
        intent.putExtra("WOnly","0");
        btn_m_only.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_m_only.setTextColor(getColor(R.color.colorChange));
                btn_m_only.setBackgroundColor(ContextCompat.getColor(WrActivity.this,R.color.colorMain));// use this to set color as background
                btn_w_only.setBackgroundResource(R.drawable.blank);// use this to set color as background
                btn_w_only.setTextColor(getColor(R.color.colorMain));
                btn_both.setBackgroundResource(R.drawable.blank);
                btn_both.setTextColor(getColor(R.color.colorMain));
                /*if(btn_m_only.isClickable()==true){
                    intent.putExtra("MOnly","1");
                }
                if (btn_m_only.isClickable()==false){
                    intent.putExtra("MOnly","0");
                }*/
                intent.putExtra("MOnly","1");
            }
        });
        intent.putExtra("MOnly","0");
        btn_both.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_both.setTextColor(getColor(R.color.colorChange));
                btn_both.setBackgroundColor(ContextCompat.getColor(WrActivity.this,R.color.colorMain));// use this to set color as background
                btn_w_only.setBackgroundResource(R.drawable.blank);// use this to set color as background
                btn_w_only.setTextColor(getColor(R.color.colorMain));
                btn_m_only.setBackgroundResource(R.drawable.blank);
                btn_m_only.setTextColor(getColor(R.color.colorMain));
                /*if(btn_both.isClickable()==true){
                    intent.putExtra("Every","1");
                }
                if (btn_both.isClickable()==false){
                    intent.putExtra("Every","0");
                }*/
                intent.putExtra("Every","1");
            }
        });
        intent.putExtra("Every","0");
        findViewById(R.id.yes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(getApplicationContext(),wr_main.class));
                //Intent intent = new Intent(getApplicationContext(),wr_main.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.no).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
