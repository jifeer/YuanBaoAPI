package cn.lw.yuanbaoapi.presenter.MainActivity;

import java.util.ArrayList;
import java.util.List;

import cn.lw.yuanbaoapi.dao.CoinDao;
import cn.lw.yuanbaoapi.entity.Coin;
import cn.lw.yuanbaoapi.view.CoinsTodayView;

/**
 * Created by lw on 2017/6/29.
 */
public class CoinsTodayPresenterImpl implements CoinsTodayPresenter {
    private CoinsTodayView coinsTodayView;
    private int page = 0;//分页页码

    public CoinsTodayPresenterImpl(CoinsTodayView coinsTodayView) {
        this.coinsTodayView = coinsTodayView;
    }

    @Override
    public void loadCoins(int type) {
        final List<Coin> list = new ArrayList<>();
        coinsTodayView.showProgress();
        if (type == CoinsTodayPresenter.TYPE_REFRESH){
            page = 0;
        }else{
            //一页七个元素
            page = page + 7;
        }
        list.addAll(CoinDao.getInstance().query(page));
        coinsTodayView.disMissProgress();
        coinsTodayView.showCoins(type, list);
    }
}
