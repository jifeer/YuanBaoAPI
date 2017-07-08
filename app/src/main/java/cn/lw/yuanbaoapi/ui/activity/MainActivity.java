package cn.lw.yuanbaoapi.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.lw.yuanbaoapi.R;
import cn.lw.yuanbaoapi.presenter.MainPresenterImpl;
import cn.lw.yuanbaoapi.ui.adapter.MainFragmentsAdapter;
import cn.lw.yuanbaoapi.ui.fragment.HistoryFragment;
import cn.lw.yuanbaoapi.ui.fragment.TodayFragment;
import cn.lw.yuanbaoapi.view.MainView;

public class MainActivity extends AppCompatActivity implements MainView,NavigationView.OnNavigationItemSelectedListener {
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.tabs) TabLayout tabs;
    @BindView(R.id.appbar) AppBarLayout appbar;
    @BindView(R.id.viewpager) ViewPager viewpager;
    @BindView(R.id.navigation_view) NavigationView navigationView;
    @BindView(R.id.activity_main) DrawerLayout activityMain;

    MainPresenterImpl presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenter = new MainPresenterImpl(this);
        initView();
    }


    private void initView(){
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, activityMain, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        activityMain.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        List<Fragment> fragments = new ArrayList<>();
        TodayFragment todayFragment = new TodayFragment();
        todayFragment.setTitle("今日行情");
        HistoryFragment historyFragment = new HistoryFragment();
        historyFragment.setTitle("历史行情");
        fragments.add(todayFragment);
        fragments.add(historyFragment);
        viewpager.setAdapter(new MainFragmentsAdapter(getSupportFragmentManager(), fragments));
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                presenter.switchView(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        tabs.setupWithViewPager(viewpager);
        tabs.setTabMode(TabLayout.MODE_FIXED);
    }

    @Override
    public void switchToTodayInfo() {
        viewpager.setCurrentItem(0);
    }

    @Override
    public void switchToHistoryInfo() {
        viewpager.setCurrentItem(1);
    }

    @Override
    public void setToolbarTitle(String title) {
        toolbar.setTitle(title);
    }

    @Override
    public void setMenuSelected(int position) {
        navigationView.getMenu().getItem(position).setChecked(true);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.today:
                item.setChecked(true);
                presenter.switchView(0);
                break;
            case R.id.history:
                item.setChecked(true);
                presenter.switchView(1);
                break;
            case R.id.weather:
                item.setChecked(true);
                break;
        }
        activityMain.closeDrawer(GravityCompat.START);
        return false;
    }
}
