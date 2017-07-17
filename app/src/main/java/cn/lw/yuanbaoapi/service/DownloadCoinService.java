package cn.lw.yuanbaoapi.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

import cn.lw.yuanbaoapi.api.YuanbaoApi;
import cn.lw.yuanbaoapi.api.YuanbaoInterface;
import cn.lw.yuanbaoapi.commons.Constant;
import cn.lw.yuanbaoapi.dao.CoinDao;
import cn.lw.yuanbaoapi.entity.Coin;
import cn.lw.yuanbaoapi.utils.LogUtils;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/7/16.
 */

public class DownloadCoinService extends IntentService {
    private static final String TAG = "DownloadCoinService";
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     */
    public DownloadCoinService() {
        super(DownloadCoinService.class.getName());
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        LogUtils.LogD(TAG, "开始下载");
        YuanbaoInterface yuanbaoInterface = YuanbaoApi.getRetrofitClient().create(YuanbaoInterface.class);
        yuanbaoInterface.getCoin(Constant.COIN_BTC).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<Coin>() {
                    @Override
                    public void accept(@NonNull Coin coin) throws Exception {
                        coin.setUpdateTime(System.currentTimeMillis() + "");
                        CoinDao coinDao = CoinDao.getInstance();
                        coinDao.insertCoin(coin, false);
                    }
                })
                .subscribe(new Observer<Coin>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Coin coin) {

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        stopSelf();
                    }
                });
    }
}
