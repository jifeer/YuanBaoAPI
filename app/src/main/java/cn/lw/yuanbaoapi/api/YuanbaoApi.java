package cn.lw.yuanbaoapi.api;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lw on 2017/6/29.
 */
public class YuanbaoApi {
    private static final String BASE_URL = "https://www.yuanbao.com/api_market/getinfo_cny/";
    private static Retrofit retrofit = null;

    public static Retrofit getRetrofitClient(){
        if (retrofit == null)
            retrofit  = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        return retrofit;
    }
}
