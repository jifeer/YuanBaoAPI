package cn.lw.yuanbaoapi.dao;

import android.content.Context;

/**
 * Created by Administrator on 2017/7/15.
 */

public class CoinDao {

    private static CoinDao coinDao;
    private DBHelper helper;
    private Context context;

    public static CoinDao getInstance(Context context){
        if (coinDao == null){
            coinDao = new CoinDao(context);
        }
        return coinDao;
    }

    private CoinDao(Context context){
        this.context = context;
    }


}
