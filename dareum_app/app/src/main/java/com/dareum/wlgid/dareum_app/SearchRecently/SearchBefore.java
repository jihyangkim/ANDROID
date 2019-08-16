package com.dareum.wlgid.dareum_app.SearchRecently;

import android.annotation.SuppressLint;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.widget.ListView;
import android.widget.TextView;

import com.dareum.wlgid.dareum_app.R;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

@SuppressLint("ValidFragment")
public class SearchBefore extends AppCompatActivity {
    String urlAddress="http://awse.dothome.co.kr/gomin_search.php";
    SearchView sv;
    ListView lv;
    TextView noDataImg,noNetworkImg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_before);

        lv= (ListView)findViewById(R.id.lv);
        sv= (SearchView)findViewById(R.id.sv);
        noDataImg= (TextView)findViewById(R.id.nodataImg);
        noNetworkImg= (TextView)findViewById(R.id.noserver);

        Intent inten = getIntent();
        String stri = inten.getStringExtra("search");
        sv.setQuery(stri,false);
        sv.setQueryHint("글을 입력하세요");

        SenderReceiver sr=new SenderReceiver(SearchBefore.this,urlAddress,stri,lv,noDataImg,noNetworkImg);
        sr.execute();

        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String title) {
                SenderReceiver sr=new SenderReceiver(SearchBefore.this,urlAddress,title,lv,noDataImg,noNetworkImg);
                sr.execute();
                return false;
            }
            @Override
            public boolean onQueryTextChange(String title) {
                /*SenderReceiver sr=new SenderReceiver(SearchBefore.this,urlAddress, title,lv,noDataImg,noNetworkImg);
                sr.execute();*/
                return false;
            }
        });
    }
}