package cn.lw.yuanbaoapi.presenter.MainActivity;

/**
 * Created by Administrator on 2017/6/29.
 */

public interface CoinsTodayPresenter {

    int TYPE_REFRESH = 0;
    int TYPE_MORE = 1;

    /**
     *
     * @param type 加载类型
     */
    void loadCoins(int type);
}
