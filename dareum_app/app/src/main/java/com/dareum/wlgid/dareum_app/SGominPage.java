package com.dareum.wlgid.dareum_app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.dareum.wlgid.dareum_app.SgoServer.SDownloader;


@SuppressLint("ValidFragment")
public class SGominPage extends Fragment {
    Context mContext;
    Intent intent=new Intent();
    public SGominPage(Context context){mContext = context;}

    final static String urlAddress = "http://awse.dothome.co.kr/sgomin_select.php";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.s_gomin_page,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        final ListView lv = (ListView)getView().findViewById(R.id.s_gomin_main);
        final SwipeRefreshLayout swipeRefreshLayout2 = (SwipeRefreshLayout)getView().findViewById(R.id.swipe);

        new SDownloader(getActivity(),urlAddress,lv,swipeRefreshLayout2).execute();

        swipeRefreshLayout2.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new SDownloader(getActivity(),urlAddress,lv,swipeRefreshLayout2).execute();
            }
        });

        super.onActivityCreated(savedInstanceState);
    }
}
