package cn.lw.yuanbaoapi.dao.rx;

import java.util.List;

import cn.lw.yuanbaoapi.entity.Coin;
import io.reactivex.Observable;

/**
 * Created by Administrator on 2017/7/18.
 */
public interface IRxDBManager {
    Observable<List<Coin>> queryAllCoins();

    Observable<List<Coin>> queryPageCoins(int limit, int offset);

    Observable<Boolean> insertCoin(Coin coin, boolean isListen);

    Observable<Coin> queryTheLastCoin();
}
