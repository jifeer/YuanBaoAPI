package cn.lw.yuanbaoapi.ui.fragment;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import cn.lw.yuanbaoapi.App;
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
    RecyclerView recycler;
    SwipeRefreshLayout swipe;
    ImageView imgEmpty;
    private CoinsTodayPresenter presenter;
    private PriOfCoinsAdapter adapter;
    private List<Coin> list;

    @Override
    public void addContentView(ViewGroup viewG) {
        LayoutInflater inflater = (LayoutInflater) App.getApplication().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.fragment_today, null);
        recycler = (RecyclerView) view.findViewById(R.id.recycler);
        swipe = (SwipeRefreshLayout) view.findViewById(R.id.swipe);
        imgEmpty = (ImageView) view.findViewById(R.id.img_empty);
        viewG.addView(view);
        initView();
        YuanbaoInterface yuanbaoInterface = YuanbaoApi.getRetrofitClient().create(YuanbaoInterface.class);
        presenter = new CoinsTodayPresenterImpl(yuanbaoInterface, this);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.loadCoins();
    }

    private void initView() {
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
    public void showEmpty() {
        recycler.setVisibility(View.GONE);
        imgEmpty.setVisibility(View.VISIBLE);
    }


}
