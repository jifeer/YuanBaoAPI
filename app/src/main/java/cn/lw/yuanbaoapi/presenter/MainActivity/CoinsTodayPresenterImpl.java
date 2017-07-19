package cn.lw.yuanbaoapi.presenter.MainActivity;

import java.util.ArrayList;
import java.util.List;

import cn.lw.yuanbaoapi.api.YuanbaoInterface;
import cn.lw.yuanbaoapi.dao.CoinDao;
import cn.lw.yuanbaoapi.entity.Coin;
import cn.lw.yuanbaoapi.view.CoinsTodayView;

/**
 * Created by lw on 2017/6/29.
 */
public class CoinsTodayPresenterImpl implements CoinsTodayPresenter {
    private static final String TAG = "CoinsTodayPresenterImpl";
    private CoinsTodayView coinsTodayView;
    private YuanbaoInterface yuanbaoInterface;
    private CoinDao coinDao;
    private int page = 0;//分页页码

    public CoinsTodayPresenterImpl(YuanbaoInterface yuanbaoInterface, CoinsTodayView coinsTodayView) {
        this.coinsTodayView = coinsTodayView;
        this.yuanbaoInterface = yuanbaoInterface;
        this.coinDao = CoinDao.getInstance();
    }

    @Override
    public void loadCoins(int type) {
        final List<Coin> list = new ArrayList<>();
        coinsTodayView.showProgress();
        if (type == CoinsTodayPresenter.TYPE_REFRESH){
            page = 0;
        }else{
            page ++;
        }
        list.addAll(CoinDao.getInstance().query(page));
        coinsTodayView.disMissProgress();
        coinsTodayView.showCoins(type, list);
    }
}
