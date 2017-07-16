package cn.lw.yuanbaoapi.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Administrator on 2017/7/16.
 */

public class BaseDao {
    private static DBHelper dbHelper;
    public static SQLiteDatabase db;

    public static void initDb(Context context){
        dbHelper = DBHelper.getInstance(context);
        db = dbHelper.getWritableDatabase();
    }

    public static void closeDb(){
        if (db != null){
            db.close();
        }
    }
}
