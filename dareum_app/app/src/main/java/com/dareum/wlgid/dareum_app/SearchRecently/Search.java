package com.dareum.wlgid.dareum_app.SearchRecently;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


import com.dareum.wlgid.dareum_app.R;

import android.widget.ImageView;

@SuppressLint("ValidFragment")
public class Search extends Fragment{

    SRHandler srHandler;
    Cursor cursor = null;
    String[] arr = null;
    EditText edit;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.search, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        srHandler = SRHandler.open(getContext());

        cursor = srHandler.sr_selectAll();
        arr = new String[cursor.getCount()];
        int count = 0;
        while(cursor.moveToNext()){
            String comm = cursor.getString(1);
            arr[count] = comm;
            count++;
        }
        cursor.close();
        invalidate();

        edit = (EditText) getView().findViewById(R.id.editTextFilter);
        try {
            getView().findViewById(R.id.sr_btn).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {//추가하기
                    if(edit.getText().toString().equals("")){
                        Toast.makeText(getContext(), "내용을 입력해주세요.", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    long re = srHandler.sr_insert(
                            edit.getText().toString());
                    if(re == 0){
                        Toast.makeText(getContext(), "추가 실패", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Intent intent = new Intent(getActivity(),SearchBefore.class);
                        intent.putExtra("search",edit.getText().toString());
                        startActivity(intent);
                        edit.setText("");

                        cursor = srHandler.sr_selectAll();
                        arr = new String[cursor.getCount()];
                        int count = 0;
                        while(cursor.moveToNext()){
                            String comm = cursor.getString(1);
                            arr[count] = comm;
                            count++;
                        }
                        cursor.close();
                        invalidate();
                    }
                }
            });
        } catch (Exception e) {
            Log.i("disp", "err:" + e);
        }

        super.onActivityCreated(savedInstanceState);
    }


    public void invalidate(){
        ArrayList<SRdata> data = new ArrayList<SRdata>();

        for (int i=0;i<arr.length;i++){
            data.add(new SRdata(arr[i],View.VISIBLE));
        }

        SRadapter adapter = new SRadapter(getActivity(),R.layout.search_item,data);

        ListView list=(ListView)getView().findViewById(R.id.sr_listview);
        list.setAdapter(adapter);
    }

}

class SRadapter extends BaseAdapter {

    SRHandler srHandler;
    Cursor cursor = null;
    String[] arr = null;

    Context mcontext;
    int mlayout;
    private ArrayList<SRdata> mdata;

    public SRadapter(Context context,int layout,ArrayList<SRdata> data) {
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
        return mdata.get(position).mcontent;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View myView = null;
        if(convertView==null){
            myView = View.inflate(mcontext, mlayout, null);
            LayoutInflater inflater = (LayoutInflater) mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.search_item, parent,false);
        }
        else{
            myView = convertView;
        }

        TextView textView =(TextView)myView.findViewById(R.id.sr_text);
        ImageView button = (ImageView)myView.findViewById(R.id.sr_button);
        textView.setText(mdata.get(position).mcontent);
        button.setVisibility(mdata.get(position).msr);

        try {
            textView.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View v) {//추가
                    srHandler=SRHandler.open(mcontext);
                    long re = srHandler.sr_insert(mdata.get(position).mcontent);
                    if(re!=0){
                        Intent intent = new Intent(mcontext,SearchBefore.class);
                        intent.putExtra("search",mdata.get(position).mcontent);
                        mcontext.startActivity(intent);
                        cursor = srHandler.sr_selectAll();
                        arr = new String[cursor.getCount()];
                        int count = 0;
                        while(cursor.moveToNext()){
                            String comm = cursor.getString(1);
                            arr[count] = comm;
                            count++;
                        }

                        cursor.close();

                        updateResults();
                    }

                }
            });

            button.setOnClickListener(new View.OnClickListener() {//삭제하기
                @Override
                public void onClick(View v) {
                    //Toast.makeText(mcontext,Integer.toString(position),Toast.LENGTH_SHORT).show();
                    srHandler=SRHandler.open(mcontext);
                    srHandler.sr_delete(getItem(position).toString());

                    //Toast.makeText(mcontext, "삭제 완료", Toast.LENGTH_SHORT).show();

                    //여기서부터 업데이트
                    cursor = srHandler.sr_selectAll();
                    arr = new String[cursor.getCount()];
                    int count = 0;
                    while(cursor.moveToNext()){
                        String comm = cursor.getString(1);
                        arr[count] = comm;
                        count++;
                    }

                    cursor.close();

                    updateResults();
                }
            });
        }
        catch (Exception e){ Log.i("disp", "err:" + e);}
        return myView;
    }

    public void updateResults() {
        mdata = new ArrayList<SRdata>();
        for (int i=0;i<arr.length;i++){
            mdata.add(new SRdata(arr[i],View.VISIBLE));
        }
        notifyDataSetChanged();
    }

}

class SRdata{

    String mcontent;
    int msr;

    public SRdata(String content,int sr){
        mcontent=content;
        msr=sr;
    }


}