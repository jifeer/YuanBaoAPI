package cn.lw.yuanbaoapi.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import cn.lw.yuanbaoapi.ui.fragment.BaseFragemnt;

/**
 * Created by lw on 2017/6/28.
 */
public class MainFragmentsAdapter extends FragmentPagerAdapter {
    private List<Fragment> list;
    public MainFragmentsAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return list == null? null : list.get(position);
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size() ;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return ((BaseFragemnt) list.get(position)).getTitle();
    }
}
