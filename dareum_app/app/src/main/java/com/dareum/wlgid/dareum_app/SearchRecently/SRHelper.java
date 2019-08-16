package com.dareum.wlgid.dareum_app.SearchRecently;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class SRHelper extends SQLiteOpenHelper {
    private static final String SR_NAME = "tb_search";  // 저장될 DB 이름
    private static final int    SR_VER = 1;             // DB 버전

    public SRHelper(Context context) {
        super(context, SR_NAME, null, SR_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //테이블 생성 및 초기 작업
        String sql = "create table tb_search(" +
                "code integer primary key autoincrement," +
                "search text not null)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //DB의 버전이 바뀌면 수행
        db.execSQL("drop table if exist tb_search");
        onCreate(db);
    }
}
