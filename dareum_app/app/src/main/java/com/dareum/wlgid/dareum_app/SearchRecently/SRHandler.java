package com.dareum.wlgid.dareum_app.SearchRecently;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;



public class SRHandler {
    private Context ctx;
    private SRHelper helper;
    private SQLiteDatabase db;

    public SRHandler(Context ctx) {
        this.ctx = ctx;
        helper = new SRHelper(ctx);
        db = helper.getWritableDatabase(); //DB가 open 됨
    }

    public static SRHandler open(Context ctx) throws SQLException {
        SRHandler handler = new SRHandler(ctx);
        return handler;
    }

    public void close(){
        helper.close();
    }

    //SQL문 작성
    //INSERT
    public long sr_insert(String search){
        ContentValues values = new ContentValues();
        values.put("search", search);
        long result = db.insert("tb_search", null, values);

        Cursor cursor = db.query("tb_search",
                new String[]{"code", "search"},
                "search=?",new String[]{search}, null, null, null);//내용이 같은 레코드를 조회

        if(cursor.getCount()>1){//중복조회
            db.execSQL("delete from tb_search where rowid < (select max(rowid) from tb_search where search='"+search+"') and search='"+search+"'");
            //db.delete("tb_search","rowid < (select min(rowid) from tb_search where search=?)",new String[]{search});
            //rowid의 값이 더 작은 쪽을 삭제한다.
        }

        return result;
    }

    //SELECT
    public Cursor sr_selectAll(){
        Cursor cursor = db.query(true, "tb_search",
                new String[]{"code", "search"},
                null, null, null,null, "code desc", null);
        return cursor;
    }

    //DELETE
    public void sr_delete(String search){
        db.delete("tb_search","search=?",new String[]{search});
    }
}