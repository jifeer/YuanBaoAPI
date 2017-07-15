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
    private static final String TABLE_NAME = "BTC";
    private static final String NAME = "name";
    private static final String LOGO = "logo";
    private static final String PRICE = "price";
    private static final String MAX = "max";
    private static final String MIN = "min";
    private static final String BUY = "buy";
    private static final String SALE = "sale";
    private static final String AVAILABLE_SUPPLY = "available_supply";
    private static final String MARKET_CAP = "market_cap";
    private static final String VOLUME_24H = "volume_24h";
    private static final String CHANGE_24H = "change_24h";
    private static final String WEBSITE = "website";
    private static final String MARKETS = "markets";

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
                + CHANGE_24H+ " real,"          +   WEBSITE          + " varchar(50), "
                + MARKETS   + " varchar(50))"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_NAME);
    }
}
