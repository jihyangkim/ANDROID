package com.dareum.wlgid.dareum_app;

import android.annotation.SuppressLint;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.dareum.wlgid.dareum_app.GoServer.Downloader;

@SuppressLint("ValidFragment")
public class GominPage extends Fragment {

    public static GominPage newInstance(String str){
        GominPage gominPage = new GominPage();
        Bundle bundle = new Bundle();
        bundle.putString("str",str);
        gominPage.setArguments(bundle);
        return gominPage;
    }

    final static String urlAddress = "http://awse.dothome.co.kr/gomin_select.php";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.gomin_page,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        final ListView lv = (ListView)getView().findViewById(R.id.gomin_main);
        final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout)getView().findViewById(R.id.swipe);

        new Downloader(getActivity(),urlAddress,lv,swipeRefreshLayout).execute();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Downloader(getActivity(),urlAddress,lv,swipeRefreshLayout).execute();
            }
        });

        super.onActivityCreated(savedInstanceState);
    }

}
