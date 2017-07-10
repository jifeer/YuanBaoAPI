package cn.lw.yuanbaoapi.presenter.MainActivity;

import java.util.ArrayList;
import java.util.List;

import cn.lw.yuanbaoapi.api.YuanbaoInterface;
import cn.lw.yuanbaoapi.commons.Constant;
import cn.lw.yuanbaoapi.entity.Coin;
import cn.lw.yuanbaoapi.utils.LogUtils;
import cn.lw.yuanbaoapi.view.CoinsTodayView;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lw on 2017/6/29.
 */
public class CoinsTodayPresenterImpl implements CoinsTodayPresenter {
    private static final String TAG = "CoinsTodayPresenterImpl";
    private CoinsTodayView coinsTodayView;
    private YuanbaoInterface yuanbaoInterface;

    public CoinsTodayPresenterImpl(YuanbaoInterface yuanbaoInterface, CoinsTodayView coinsTodayView) {
        this.coinsTodayView = coinsTodayView;
        this.yuanbaoInterface = yuanbaoInterface;
    }

    @Override
    public void loadCoins() {
        final List<Coin> list = new ArrayList<>();
        coinsTodayView.showProgress();
        Observable.just(Constant.COIN_BTC, Constant.COIN_DODGE, Constant.COIN_MONKEY, Constant.COIN_MRYC)
                .flatMap(new Function<String, ObservableSource<Coin>>() {
                    @Override
                    public ObservableSource<Coin> apply(@NonNull String s) throws Exception {
                        return yuanbaoInterface.getCoin(s);
                    }
                })
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Coin>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        LogUtils.LogD(TAG, "onSubscribe: " + d.isDisposed());
                    }

                    @Override
                    public void onNext(@NonNull Coin coin) {
                        LogUtils.LogD(TAG, "onNext: " + coin.toString());
                        list.add(coin);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        LogUtils.LogD(TAG, "onError: " + e.getMessage());
                        coinsTodayView.disMissProgress();
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.LogD(TAG, "onComplete: ");
                        coinsTodayView.disMissProgress();
                        coinsTodayView.showCoins(list);
                    }
                });
    }
}
