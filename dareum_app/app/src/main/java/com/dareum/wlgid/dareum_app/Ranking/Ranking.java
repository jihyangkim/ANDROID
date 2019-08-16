package com.dareum.wlgid.dareum_app.Ranking;


import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.dareum.wlgid.dareum_app.R;

public class Ranking extends  Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ranking,container,false);
        return view;
    }

    final static String urlAddress = "http://awse.dothome.co.kr/golike_select.php";
    final static String urlAddress2 = "http://awse.dothome.co.kr/gocomm_select.php";
    final static String urlAddress3 = "http://awse.dothome.co.kr/user_select_B.php";
    final static String urlAddress4 = "http://awse.dothome.co.kr/user_select_R.php";

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        /*ArrayList<RankingList> rk1 = new ArrayList<RankingList>();
        ArrayList<RankingList> rk2 = new ArrayList<RankingList>();

        for (int i=0;i<4;i++) {
            rk1.add(new RankingList(R.drawable.heart_red,"으아아아아"));
            rk2.add(new RankingList(R.drawable.comm_vio,"으아아아아"));
        }

        MyListAdapter1 mla1 = new MyListAdapter1(getActivity(),R.layout.ranking_item,rk1);
        MyListAdapter2 mla2 = new MyListAdapter2(getActivity(),R.layout.ranking_item,rk2);

        ListView RL = (ListView)getView().findViewById(R.id.red_list);
        RL.setAdapter(mla1);
        ListView BL = (ListView)getView().findViewById(R.id.blue_list);
        BL.setAdapter(mla2);*/
        final ListView lv = (ListView)getView().findViewById(R.id.red_list);
        final ListView lv2 = (ListView)getView().findViewById(R.id.blue_list);
        final ListView lv3 = (ListView)getView().findViewById(R.id.list_male);
        final ListView lv4 = (ListView)getView().findViewById(R.id.list_female);


        /*lv3.setBackgroundColor(Color.BLUE);
        lv4.setBackgroundColor(Color.RED);*/


        new DownRank1(getActivity(),urlAddress,lv).execute();
        new DownRank2(getActivity(),urlAddress2,lv2).execute();
        new DownRank3(getActivity(),urlAddress3,lv3).execute();
        new DownRank4(getActivity(),urlAddress4,lv4).execute();

        super.onActivityCreated(savedInstanceState);
    }
}

/*class MyListAdapter1 extends BaseAdapter{
    Context mcontext;
    int mlayout;
    ArrayList<RankingList> mdata;

    public MyListAdapter1(Context context,int layout,ArrayList<RankingList> data) {
        mcontext=context;
        mlayout=layout;
        mdata=data;
    }

    @Override
    public int getCount() {
        return mdata.size();
    }

    @Override
    public Object getItem(int position) {
        return mdata.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View myView = null;
        if(convertView==null){
            myView = View.inflate(mcontext, mlayout, null);
            LayoutInflater inflater = (LayoutInflater) mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.ranking_item, parent, false);
        }
        else{
            myView = convertView;
        }


        ImageView listimg = (ImageView)myView.findViewById(R.id.ranking_img);
        TextView listtext = (TextView)myView.findViewById(R.id.ranking_text);
        LinearLayout linear = (LinearLayout)myView.findViewById(R.id.ranking_linear);

        listimg.setImageResource(mdata.get(position).mlistimg);
        listtext.setText(mdata.get(position).mlisttext);

        return myView;
    }
}

class MyListAdapter2 extends BaseAdapter{
    Context mcontext;
    int mlayout;
    ArrayList<RankingList> mdata;

    public MyListAdapter2(Context context,int layout,ArrayList<RankingList> data) {
        mcontext=context;
        mlayout=layout;
        mdata=data;
    }

    @Override
    public int getCount() {
        return mdata.size();
    }

    @Override
    public Object getItem(int position) {
        return mdata.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View myView = null;
        if(convertView==null){
            myView = View.inflate(mcontext, mlayout, null);
            LayoutInflater inflater = (LayoutInflater) mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.ranking_item, parent, false);
        }
        else{
            myView = convertView;
        }

        ImageView listimg = (ImageView)myView.findViewById(R.id.ranking_img);
        TextView listtext = (TextView)myView.findViewById(R.id.ranking_text);

        listimg.setImageResource(mdata.get(position).mlistimg);
        listtext.setText(mdata.get(position).mlisttext);

        return myView;
    }
}

class RankingList{

    int mlistimg;
    String mlisttext;

    public RankingList(int listimg,String listtext) {
        mlistimg = listimg;
        mlisttext = listtext;
    }
}*/
