//package cn.lw.yuanbaoapi.presenter;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.ArgumentCaptor;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.robolectric.annotation.Config;
//
//import java.util.List;
//
//import cn.lw.yuanbaoapi.BuildConfig;
//import cn.lw.yuanbaoapi.MyRobolectricTestRunner;
//import cn.lw.yuanbaoapi.api.YuanbaoInterface;
//import cn.lw.yuanbaoapi.entity.Coin;
//import cn.lw.yuanbaoapi.view.CoinsTodayView;
//import io.reactivex.Observable;
//
////import org.mockito.Mock;
//
///**
// * Created by Administrator on 2017/7/5.
// */
//@RunWith(MyRobolectricTestRunner.class)
//@Config(constants = BuildConfig.class, sdk = 23)
//public class CoinsTodayPresenterTest {
//
//    CoinsTodayPresenter coinsTodayPresenter;
//    @Mock
//    YuanbaoInterface yuanbaoInterface;
//    @Mock
//    private CoinsTodayView coinsTodayView;
//
//    @Before
//    public void setUp(){
//        MockitoAnnotations.initMocks(this);
//        Coin coin = new Coin();
//        coinsTodayPresenter = new CoinsTodayPresenterImpl(yuanbaoInterface, coinsTodayView);
//        Mockito.when(yuanbaoInterface.getCoin(Mockito.anyString())).thenReturn(Observable.just(coin));
//        coinsTodayPresenter.loadCoins();
//
//        ArgumentCaptor<List> captor = ArgumentCaptor.forClass(List.class);
//        Mockito.verify(coinsTodayView).showProgress();
//        Mockito.verify(coinsTodayView).showCoins(captor.capture());
//        Mockito.verify(coinsTodayView).disMissProgress();
//    }
//
//    @Test
//    public void loadCoins() throws Exception {
//    }
//
//}