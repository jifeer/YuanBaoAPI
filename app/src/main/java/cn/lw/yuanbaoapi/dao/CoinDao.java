package cn.lw.yuanbaoapi.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import cn.lw.yuanbaoapi.dao.listener.DataChangeListener;
import cn.lw.yuanbaoapi.entity.Coin;
import cn.lw.yuanbaoapi.utils.LogUtils;

/**
 * Created by Administrator on 2017/7/15.
 */

public class CoinDao extends BaseDao{
    private static final String TAG = "CoinDao";
    private static CoinDao coinDao;
    private Context context;
    private List<DataChangeListener> dataChangeListeners = new ArrayList<>();

    public static CoinDao getInstance(Context context){
        if (coinDao == null){
            coinDao = new CoinDao(context);
        }
        return coinDao;
    }

    private CoinDao(Context context){
        this.context = context;
    }

    /**
     * 插入一条比特币数据
     * @param coin
     */
    public void insertCoin(Coin coin){
        initDb(context);
        try {
            ContentValues cv = new ContentValues();
            cv.put(DBHelper.NAME, coin.getName());
            cv.put(DBHelper.LOGO, coin.getLogo());
            cv.put(DBHelper.PRICE, coin.getPrice());
            cv.put(DBHelper.MAX, coin.getMax());
            cv.put(DBHelper.MIN, coin.getMin());
            cv.put(DBHelper.BUY, coin.getBuy());
            cv.put(DBHelper.SALE, coin.getSale());
            cv.put(DBHelper.AVAILABLE_SUPPLY, coin.getAvailable_supply());
            cv.put(DBHelper.MARKET_CAP, coin.getMarket_cap());
            cv.put(DBHelper.VOLUME_24H, coin.getVolume_24h());
            cv.put(DBHelper.CHANGE_24H, coin.getChange_24h());
            cv.put(DBHelper.WEBSITE, coin.getWebsite());
            cv.put(DBHelper.MARKETS, coin.getMarkets());
            cv.put(DBHelper.DATE, coin.getUpdateTime());
            db.insert(DBHelper.TABLE_NAME, null, cv);
        }catch (Exception e){
            LogUtils.LogE(TAG, e.getMessage());
        }finally {
            closeDb();
        }
    }

    /**
     * 根据更新时间删除数据
     * @param coin
     * @return
     */
    public int deleteCoin(Coin coin){
        int index = -1;
        initDb(context);
        try {
            index = db.delete(DBHelper.TABLE_NAME, DBHelper.DATE + "=?", new String[]{coin.getUpdateTime()});
        }catch (Exception e){
            LogUtils.LogE(TAG, e.getMessage());
            index = -1;
        }finally {
            closeDb();
        }
        return index;
    }

    /**
     * 遍历数据
     */
    public List<Coin> query(int limit, boolean isListen){
        initDb(context);
        List <Coin> list = new ArrayList<>();
        try{
            Cursor cursor = db.rawQuery("select * from " + DBHelper.TABLE_NAME + " limit=?" + limit + " offset 5", new String[]{limit + ""});
            while (cursor.moveToNext()){
                Coin coin = new Coin();
                coin.setName(cursor.getString(cursor.getColumnIndex(DBHelper.NAME)));
                coin.setLogo(cursor.getString(cursor.getColumnIndex(DBHelper.LOGO)));
                coin.setPrice(cursor.getString(cursor.getColumnIndex(DBHelper.PRICE)));
                coin.setMax(cursor.getString(cursor.getColumnIndex(DBHelper.MAX)));
                coin.setMin(cursor.getString(cursor.getColumnIndex(DBHelper.MIN)));
                coin.setBuy(cursor.getString(cursor.getColumnIndex(DBHelper.BUY)));
                coin.setSale(cursor.getString(cursor.getColumnIndex(DBHelper.SALE)));
                coin.setAvailable_supply(cursor.getLong(cursor.getColumnIndex(DBHelper.AVAILABLE_SUPPLY)));
                coin.setMarket_cap(cursor.getDouble(cursor.getColumnIndex(DBHelper.MARKET_CAP)));
                coin.setVolume_24h(cursor.getString(cursor.getColumnIndex(DBHelper.VOLUME_24H)));
                coin.setChange_24h(cursor.getDouble(cursor.getColumnIndex(DBHelper.CHANGE_24H)));
                coin.setWebsite(cursor.getString(cursor.getColumnIndex(DBHelper.WEBSITE)));
                coin.setMarkets(cursor.getString(cursor.getColumnIndex(DBHelper.MARKETS)));
                coin.setUpdateTime(cursor.getString(cursor.getColumnIndex(DBHelper.DATE)));
                list.add(coin);
            }
            return list;
        }catch (Exception e){
            LogUtils.LogE(TAG, e.getMessage());
        }
        closeDb();
        return list;
    }

    /**
     * 注册数据库数据变化监听
     * @param listener
     */
    public void registerDataChangeListener(DataChangeListener listener){
        dataChangeListeners.add(listener);
    }

    /**
     * 移除数据库状态监听
     * @param dataChangeListener
     */
    public void unRegisterDataChangeListener(DataChangeListener dataChangeListener){
        dataChangeListeners.remove(dataChangeListener);
    }


}
