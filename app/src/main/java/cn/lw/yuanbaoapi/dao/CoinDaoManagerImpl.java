package cn.lw.yuanbaoapi.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import cn.lw.yuanbaoapi.dao.rx.IRxDBManager;
import cn.lw.yuanbaoapi.entity.Coin;
import cn.lw.yuanbaoapi.utils.LogUtils;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;

import static android.content.ContentValues.TAG;

/**
 * Created by Administrator on 2017/7/18.
 */

public class CoinDaoManagerImpl implements IRxDBManager {

    private DBHelper dbHelper;
    private static CoinDaoManagerImpl coinDaoManager;

    private CoinDaoManagerImpl(DBHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    public static CoinDaoManagerImpl getInstance(DBHelper dbHelper) {
        if (coinDaoManager == null) {
            coinDaoManager = new CoinDaoManagerImpl(dbHelper);
        }
        return coinDaoManager;
    }

    /**
     * 遍历所有的Coin数据
     * @return
     */
    @Override
    public Observable<List<Coin>> queryAllCoins() {
        return Observable.create(new ObservableOnSubscribe<List<Coin>>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<List<Coin>> e) throws Exception {
                SQLiteDatabase db = dbHelper.getReadableDatabase();
                Cursor cursor = db.rawQuery("select * from " + DBHelper.TABLE_NAME, null);
            }
        });
    }

    /**
     * 分页遍历数据
     * @param limit
     * @param offset
     * @return
     */
    @Override
    public Observable<List<Coin>> queryPageCoins(final int limit, final int offset) {
        return Observable.create(new ObservableOnSubscribe<List<Coin>>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<List<Coin>> e) throws Exception {
                SQLiteDatabase db = dbHelper.getReadableDatabase();
                List<Coin> list = new ArrayList<>();
                try {
                    Cursor cursor = db.rawQuery("select * from " + DBHelper.TABLE_NAME + " limit=?" + limit + " offset=?", new String[]{limit + "", offset + ""});
                    while (cursor.moveToNext()) {
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
                    cursor.close();
                    e.onNext(list);
                    e.onComplete();
                } catch (Exception exception) {
                    LogUtils.LogE(TAG, exception.getMessage());
                } finally {
                    if (db != null && db.isOpen()) {
                        db.close();
                    }
                }
            }
        }).subscribeOn(Schedulers.io());
    }

    /**
     * 插入Coin
     * @param coin
     * @param isListen
     * @return
     */
    @Override
    public Observable<Boolean> insertCoin(final Coin coin, boolean isListen) {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Boolean> e) throws Exception {
                SQLiteDatabase db = dbHelper.getReadableDatabase();
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
                    long index = db.insert(DBHelper.TABLE_NAME, null, cv);
                    if (index != -1) {
                        e.onNext(true);
                    } else {
                        e.onNext(false);
                    }
                    e.onComplete();
                } catch (Exception exception) {
                    LogUtils.LogE(TAG, exception.getMessage());
                } finally {
                    if (db != null && db.isOpen()) {
                        db.close();
                    }
                }
            }
        }).subscribeOn(Schedulers.io());
    }

    /**
     * 获取最新的一条数据
     * @return
     */
    @Override
    public Observable<Coin> queryTheLastCoin() {
        return Observable.create(new ObservableOnSubscribe<Coin>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Coin> e) throws Exception {
                SQLiteDatabase db = dbHelper.getReadableDatabase();
                try {
                    Cursor cursor = db.rawQuery("select last_insert_rowid()", null);
                    cursor.moveToFirst();
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
                    cursor.close();
                    e.onNext(coin);
                    e.onComplete();
                } catch (Exception exception) {
                    LogUtils.LogE(TAG, exception.getMessage());
                } finally {
                    if (db != null && db.isOpen()) {
                        db.close();
                    }
                }
            }
        }).subscribeOn(Schedulers.io());
    }
}
