package cn.lw.yuanbaoapi.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.lw.yuanbaoapi.R;
import cn.lw.yuanbaoapi.api.YuanbaoApi;
import cn.lw.yuanbaoapi.api.YuanbaoInterface;
import cn.lw.yuanbaoapi.entity.Coin;
import cn.lw.yuanbaoapi.presenter.MainActivity.CoinsTodayPresenter;
import cn.lw.yuanbaoapi.presenter.MainActivity.CoinsTodayPresenterImpl;
import cn.lw.yuanbaoapi.ui.adapter.PriOfCoinsAdapter;
import cn.lw.yuanbaoapi.view.CoinsTodayView;

/**
 * A simple {@link Fragment} subclass.
 */
public class TodayFragment extends BaseFragemnt implements CoinsTodayView {


    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.swipe)
    SwipeRefreshLayout swipe;
    Unbinder unbinder;
    @BindView(R.id.loding_pb)
    ContentLoadingProgressBar lodingPb;
    @BindView(R.id.loading)
    FrameLayout loading;
    private String title;
    private CoinsTodayPresenter presenter;
    private PriOfCoinsAdapter adapter;
    private List<Coin> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_today, container, false);
        unbinder = ButterKnife.bind(this, view);
        YuanbaoInterface yuanbaoInterface = YuanbaoApi.getRetrofitClient().create(YuanbaoInterface.class);
        presenter = new CoinsTodayPresenterImpl(yuanbaoInterface, this);
        presenter.loadCoins();
        initView();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.loadCoins();
    }

    private void initView(){
        list = new ArrayList<>();
        adapter = new PriOfCoinsAdapter(list);
        recycler.setAdapter(adapter);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));

        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.loadCoins();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showProgress() {
        swipe.setRefreshing(true);
    }

    @Override
    public void showCoins(List<Coin> coins) {
        adapter.initData(coins);
    }

    @Override
    public void disMissProgress() {
        swipe.setRefreshing(false);
    }

    @Override
    public void showError() {

    }
}
