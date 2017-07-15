package cn.lw.yuanbaoapi.view;

import java.util.List;

import cn.lw.yuanbaoapi.entity.Coin;

/**
 * Created by Administrator on 2017/6/29.
 */

public interface CoinsTodayView {

    void showProgress();

    void showCoins(List<Coin> list);

    void disMissProgress();

    void showEmpty();
}
