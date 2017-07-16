package cn.lw.yuanbaoapi.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2017/7/15.
 */
public class DBHelper extends SQLiteOpenHelper {
    private static int DB_VERSION = 1;
    private static final String DB_NAME = "COIN.db";
    public static final String TABLE_NAME = "BTC";
    public static final String NAME = "name";
    public static final String LOGO = "logo";
    public static final String PRICE = "price";
    public static final String MAX = "max";
    public static final String MIN = "min";
    public static final String BUY = "buy";
    public static final String SALE = "sale";
    public static final String AVAILABLE_SUPPLY = "available_supply";
    public static final String MARKET_CAP = "market_cap";
    public static final String VOLUME_24H = "volume_24h";
    public static final String CHANGE_24H = "change_24h";
    public static final String WEBSITE = "website";
    public static final String MARKETS = "markets";
    public static final String DATE = "update_date";

    private static DBHelper helper;

    private DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public static DBHelper getInstance(Context context){
        if (helper == null){
            helper = new DBHelper(context);
        }
        return helper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists " + TABLE_NAME + "( "
                + "id integer primary key autoincrement,"
                + NAME      + " varchar(50), "  +   LOGO             + " varchar(50), "
                + PRICE     + " real, "         +   MAX              + " real, "
                + MIN       + " real, "         +   BUY              + " real, "
                + SALE      + " real, "         +   AVAILABLE_SUPPLY + " integer, "
                + MARKET_CAP+ " real, "         +   VOLUME_24H       + " real, "
                + CHANGE_24H+ " real, "         +   WEBSITE          + " varchar(50), "
                + MARKETS   + " varchar(50), "  +   DATE             + " varchar(50))"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_NAME);
    }
}
