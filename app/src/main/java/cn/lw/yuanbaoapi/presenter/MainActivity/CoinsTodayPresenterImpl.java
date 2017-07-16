package cn.lw.yuanbaoapi.presenter.MainActivity;

import java.util.ArrayList;
import java.util.List;

import cn.lw.yuanbaoapi.api.YuanbaoInterface;
import cn.lw.yuanbaoapi.commons.Constant;
import cn.lw.yuanbaoapi.dao.CoinDao;
import cn.lw.yuanbaoapi.entity.Coin;
import cn.lw.yuanbaoapi.view.CoinsTodayView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lw on 2017/6/29.
 */
public class CoinsTodayPresenterImpl implements CoinsTodayPresenter {
    private static final String TAG = "CoinsTodayPresenterImpl";
    private CoinsTodayView coinsTodayView;
    private YuanbaoInterface yuanbaoInterface;
    private CoinDao coinDao;

    public CoinsTodayPresenterImpl(YuanbaoInterface yuanbaoInterface, CoinsTodayView coinsTodayView) {
        this.coinsTodayView = coinsTodayView;
        this.yuanbaoInterface = yuanbaoInterface;
        this.coinDao = CoinDao.getInstance();
    }

    @Override
    public void loadCoins() {
        final List<Coin> list = new ArrayList<>();
        coinsTodayView.showProgress();
        yuanbaoInterface.getCoin(Constant.COIN_BTC).subscribeOn(Schedulers.newThread()).observeOn(Schedulers.io())
                .doOnNext(new Consumer<Coin>() {
                    @Override
                    public void accept(@NonNull Coin coin) throws Exception {
                        list.add(coin);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Coin>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Coin coin) {

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        coinsTodayView.showEmpty();
                    }

                    @Override
                    public void onComplete() {
                        coinsTodayView.disMissProgress();
                        if (list == null || list.size() == 0) {
                            coinsTodayView.showEmpty();
                        } else {
                            coinsTodayView.showCoins(list);
                        }
                    }
                });
    }
}
