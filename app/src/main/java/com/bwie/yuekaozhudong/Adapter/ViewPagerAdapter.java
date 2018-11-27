package com.bwie.yuekaozhudong.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> frags;
    public ViewPagerAdapter(FragmentManager fm, List<Fragment> frags) {
        super(fm);
        this.frags = frags;
    }

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return frags.get(i);
    }

    @Override
    public int getCount() {
        return frags.size();
    }
}
