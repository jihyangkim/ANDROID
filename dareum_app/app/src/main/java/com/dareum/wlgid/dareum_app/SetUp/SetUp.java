package com.dareum.wlgid.dareum_app.SetUp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import com.dareum.wlgid.dareum_app.Cont.UserProfile;
import com.dareum.wlgid.dareum_app.MainNotice;
import com.dareum.wlgid.dareum_app.Tutorial.AgainIntro;
import com.dareum.wlgid.dareum_app.R;

import java.util.ArrayList;

@SuppressLint("ValidFragment")
public class SetUp extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.set_up, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        final ListView setlist = (ListView)getView().findViewById(R.id.set_list);

        final ArrayList<SetData> data = new ArrayList<SetData>();
        data.add(new SetData(R.mipmap.set_1,"댓글 알림",View.VISIBLE));
        data.add(new SetData(R.mipmap.set_2,"하트 알림",View.VISIBLE));
        data.add(new SetData(R.mipmap.set_3,"도움말",View.GONE));
        data.add(new SetData(R.mipmap.set_4,"프로그램 정보",View.GONE));
        data.add(new SetData(R.mipmap.set_5,"내 계정",View.GONE));
        data.add(new SetData(R.mipmap.set_6,"암호",View.GONE));
        data.add(new SetData(R.mipmap.set_7,"공지사항",View.GONE));
        data.add(new SetData(R.mipmap.set_8,"튜토리얼",View.GONE));
        SetAdapter adapter = new SetAdapter(getActivity(),R.layout.sub_set_up,data);

        setlist.setAdapter(adapter);
        setlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
                ListView listView = (ListView) adapterView;
                //String item = (String)  listView.getItemAtPosition(pos);
                listView.getAdapter().getItem(pos);
                switch (pos){
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        startActivity(new Intent(getActivity(), HelpActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(getActivity(), InfoProgram.class));
                        break;
                    case 4:
                        startActivity(new Intent(getActivity(), UserProfile.class));
                        break;
                    case 5:
                        startActivity(new Intent(getActivity(),PwSetup.class));
                        break;
                    case 6:
                        startActivity(new Intent(getActivity(),MainNotice.class));
                        break;
                    case 7:
                        startActivity(new Intent(getActivity(), AgainIntro.class));
                        break;
                }

            }
        });

        super.onActivityCreated(savedInstanceState);
    }



}

class SetAdapter extends BaseAdapter{
    Context mcontext;
    int mlayout;
    ArrayList<SetData> mdata;

    public SetAdapter(Context context,int layout,ArrayList<SetData> data) {
        mcontext = context;
        mlayout = layout;
        mdata = data;
    }
    @Override
    public int getCount() {
        return mdata.size();
    }

    @Override
    public Object getItem(int position) {
        return mdata.get(position).msettext;
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
            convertView = inflater.inflate(R.layout.sub_set_up, parent,false);
        }
        else{
            myView = convertView;
        }

        ImageView setimg = (ImageView) myView.findViewById(R.id.set_img1);
        TextView settext = (TextView) myView.findViewById(R.id.set_txt1);
        Switch setswitch = (Switch) myView.findViewById(R.id.set_swi1);

        setimg.setImageResource(mdata.get(position).msetimg);
        settext.setText(mdata.get(position).msettext);
        setswitch.setVisibility(mdata.get(position).msetsw);

        return myView;
    }
}


class SetData{
    int msetimg;
    String msettext;
    int msetsw;
    public SetData(int setimg,String settext,int setsw){
        msetimg = setimg;
        msettext = settext;
        msetsw = setsw;
    }
}

    /*@Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        ListView setlist = (ListView)getView().findViewById(R.id.set_list);

        ArrayList<SetData> data = new ArrayList<SetData>();
        data.add(new SetData(R.mipmap.set_1,"댓글 알림",View.VISIBLE));
        data.add(new SetData(R.mipmap.set_2,"하트 알림",View.VISIBLE));
        data.add(new SetData(R.mipmap.set_3,"도움말",View.GONE));
        data.add(new SetData(R.mipmap.set_4,"프로그램 정보",View.GONE));
        data.add(new SetData(R.mipmap.set_5,"내 계정",View.GONE));
        data.add(new SetData(R.mipmap.set_6,"암호",View.GONE));
        data.add(new SetData(R.mipmap.set_7,"공지사항",View.GONE));

        SetAdapter adapter = new SetAdapter(getActivity(),R.layout.sub_set_up,data);

        setlist.setAdapter(adapter);

    *//*

        super.onActivityCreated(savedInstanceState);
    }
}
class SetAdapter extends BaseAdapter{
    Context mcontext;
    int mlayout;
    ArrayList<SetData> mdata;

    public SetAdapter(Context context,int layout,ArrayList<SetData> data) {
        mcontext = context;
        mlayout = layout;
        mdata = data;
    }
    @Override
    public int getCount() {
        return mdata.size();
    }

    @Override
    public Object getItem(int position) {
        return mdata.get(position).msettext;
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
            convertView = inflater.inflate(R.layout.sub_set_up, parent,false);
        }
        else{
            myView = convertView;
        }

        ImageView setimg1 = (ImageView) myView.findViewById(R.id.set_img1);
        ImageView setimg2 = (ImageView) myView.findViewById(R.id.set_img2);
        ImageView setimg3 = (ImageView) myView.findViewById(R.id.set_img3);
        ImageView setimg4 = (ImageView) myView.findViewById(R.id.set_img4);
        ImageView setimg5 = (ImageView) myView.findViewById(R.id.set_img5);
        ImageView setimg6 = (ImageView) myView.findViewById(R.id.set_img6);
        ImageView setimg7 = (ImageView) myView.findViewById(R.id.set_img7);
        TextView settext1 = (TextView) myView.findViewById(R.id.set_txt1);
        TextView settext2 = (TextView) myView.findViewById(R.id.set_txt2);
        TextView settext3 = (TextView) myView.findViewById(R.id.set_txt3);
        TextView settext4 = (TextView) myView.findViewById(R.id.set_txt4);
        TextView settext5 = (TextView) myView.findViewById(R.id.set_txt5);
        TextView settext6 = (TextView) myView.findViewById(R.id.set_txt6);
        TextView settext7 = (TextView) myView.findViewById(R.id.set_txt7);
        Switch setswitch1 = (Switch) myView.findViewById(R.id.set_swi1);
        Switch setswitch2 = (Switch) myView.findViewById(R.id.set_swi2);

        setimg1.setImageResource(mdata.get(position).msetimg);
        setimg2.setImageResource(mdata.get(position).msetimg);
        setimg3.setImageResource(mdata.get(position).msetimg);
        setimg4.setImageResource(mdata.get(position).msetimg);
        setimg5.setImageResource(mdata.get(position).msetimg);
        setimg6.setImageResource(mdata.get(position).msetimg);
        setimg7.setImageResource(mdata.get(position).msetimg);
        settext1.setText(mdata.get(position).msettext);
        settext2.setText(mdata.get(position).msettext);
        settext3.setText(mdata.get(position).msettext);
        settext4.setText(mdata.get(position).msettext);
        settext5.setText(mdata.get(position).msettext);
        settext6.setText(mdata.get(position).msettext);
        settext7.setText(mdata.get(position).msettext);
        setswitch1.setVisibility(mdata.get(position).msetsw);
        setswitch2.setVisibility(mdata.get(position).msetsw);

        return myView;
    }
}


class SetData{
    int msetimg;
    String msettext;
    int msetsw;
    public SetData(int setimg,String settext,int setsw){
        msetimg = setimg;
        msettext = settext;
        msetsw = setsw;
    }
}
*/