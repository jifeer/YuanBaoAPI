package cn.lw.yuanbaoapi.api;

import cn.lw.yuanbaoapi.entity.Coin;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Administrator on 2017/6/29.
 */

public interface YuanbaoInterface {

    @GET("coin/{name}")
    Observable<Coin> getCoin(@Path("name") String name);
}
