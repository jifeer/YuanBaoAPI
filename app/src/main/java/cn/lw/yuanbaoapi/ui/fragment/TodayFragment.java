package cn.lw.yuanbaoapi.ui.fragment;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import cn.lw.yuanbaoapi.App;
import cn.lw.yuanbaoapi.R;
import cn.lw.yuanbaoapi.entity.Coin;
import cn.lw.yuanbaoapi.presenter.MainActivity.CoinsTodayPresenter;
import cn.lw.yuanbaoapi.presenter.MainActivity.CoinsTodayPresenterImpl;
import cn.lw.yuanbaoapi.ui.adapter.PriOfCoinsAdapter;
import cn.lw.yuanbaoapi.view.CoinsTodayView;
import cn.lw.yuanbaoapi.view.widget.EmptyRecyclerView;

/**
 * A simple {@link Fragment} subclass.
 */
public class TodayFragment extends BaseFragemnt implements CoinsTodayView {
    EmptyRecyclerView recycler;
    SwipeRefreshLayout swipe;
    private CoinsTodayPresenter presenter;
    private PriOfCoinsAdapter adapter;
    private List<Coin> list;
    private int pastVisibleItems, visibleItemCount, totalItemCount;
    private boolean isLoading = false;

    @Override
    public View initContentView() {
        LayoutInflater inflater = (LayoutInflater) App.getApplication().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.fragment_today, null);
        recycler = (EmptyRecyclerView) view.findViewById(R.id.recycler);
        swipe = (SwipeRefreshLayout) view.findViewById(R.id.swipe);
        initView();

        presenter = new CoinsTodayPresenterImpl(this);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.loadCoins(CoinsTodayPresenter.TYPE_REFRESH);
    }

    private void initView() {
        list = new ArrayList<>();
        adapter = new PriOfCoinsAdapter(list);
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View emptyView = inflater.inflate(R.layout.layout_empty, null);
        recycler.setEmptyView(emptyView);
        recycler.setAdapter(adapter);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.loadCoins(CoinsTodayPresenter.TYPE_REFRESH);
            }
        });
        recycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) {
                    visibleItemCount = recyclerView.getLayoutManager().getChildCount();
                    totalItemCount = recyclerView.getLayoutManager().getItemCount();
                    pastVisibleItems = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();

                    if (pastVisibleItems + visibleItemCount >= totalItemCount) {
                        presenter.loadCoins(CoinsTodayPresenter.TYPE_MORE);
                        isLoading = false;
                    } else {
                        isLoading = true;
                    }
                }
            }
        });
    }


    @Override
    public void showProgress() {
        swipe.setRefreshing(true);
    }

    @Override
    public void showCoins(int type, List<Coin> coins) {
        adapter.initData(type, coins);
    }

    @Override
    public void disMissProgress() {
        swipe.setRefreshing(false);
    }

}
