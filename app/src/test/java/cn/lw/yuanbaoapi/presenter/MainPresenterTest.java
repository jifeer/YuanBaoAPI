package cn.lw.yuanbaoapi.presenter;

import android.app.Activity;
import android.support.v4.view.ViewPager;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.annotation.Config;

import cn.lw.yuanbaoapi.BuildConfig;
import cn.lw.yuanbaoapi.MyRobolectricTestRunner;
import cn.lw.yuanbaoapi.R;
import cn.lw.yuanbaoapi.RxAsynRules;
import cn.lw.yuanbaoapi.api.YuanbaoInterface;
import cn.lw.yuanbaoapi.presenter.MainActivity.CoinsTodayPresenter;
import cn.lw.yuanbaoapi.presenter.MainActivity.CoinsTodayPresenterImpl;
import cn.lw.yuanbaoapi.ui.activity.MainActivity;
import cn.lw.yuanbaoapi.view.CoinsTodayView;

/**
 * Created by lw on 2017/7/5.
 */
@RunWith(MyRobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class MainPresenterTest {
    public CoinsTodayPresenter coinsTodayPresenter;
    public Activity activity;
    public ViewPager viewPager;
    @Rule
    public RxAsynRules rules = new RxAsynRules();
    @Mock
    public CoinsTodayView coinsTodayView;
    @Mock
    public YuanbaoInterface yuanbaoInterface;
    @Captor
    private ArgumentCaptor<String> mApiObservableCaptor;

    @BeforeClass
    public static void setUpRxSchedulers() {
    }

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        activity = Robolectric.setupActivity(MainActivity.class);
        viewPager = (ViewPager) activity.findViewById(R.id.viewpager);
        coinsTodayPresenter = new CoinsTodayPresenterImpl(coinsTodayView);
    }

    /**
     * 测试showProgress和disMissProgress是否成功调用
     */
//    @Test
//    public void loadSuccess(){
//        Coin coin = new Coin();
//        coin.setName("test");
//        Mockito.when(yuanbaoInterface.getCoin(Constant.COIN_BTC)).thenReturn(Observable.just(coin));
//        coinsTodayPresenter.loadCoins();
//
//        ArgumentCaptor<List> captor = ArgumentCaptor.forClass(List.class);
//
//        Mockito.verify(coinsTodayView).showProgress();
//        Mockito.verify(coinsTodayView).showCoins(captor.capture());
//        Mockito.verify(coinsTodayView).disMissProgress();
//
//        List<Coin> coins = captor.getValue();
//        Coin coinCaptor = coins.get(coins.size() - 1);
//        Assert.assertEquals(coins.size(), 1);
//        Assert.assertEquals(coinCaptor.getName(), "test");
//    }

//    /**
//     * 测试没有数据的时候方法调用是否正常
//     */
//    @Test
//    public void testLoadEmpty(){
//        Mockito.when(yuanbaoInterface.getCoin(Mockito.anyString())).thenReturn(Observable.<Coin>empty());
//        coinsTodayPresenter.loadCoins();
//        Mockito.verify(coinsTodayView).showEmpty();
//    }

}

