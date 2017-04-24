package com.example.yan.translate.ui.adapters.pagers;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.yan.translate.R;
import com.example.yan.translate.ui.fragments.CacheFragment;
import com.example.yan.translate.ui.fragments.FavouritesFragment;

// pageradapter for history fragment

public class HistoryPagerAdapter extends FragmentPagerAdapter {

    String[] tabTitles;

    final int PAGE_COUNT = 2;
    private Context context;

    public HistoryPagerAdapter(FragmentManager fm, Context context) {

        super(fm);
        this.context = context;
        tabTitles = context.getResources().getStringArray(R.array.tabTitles);

    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

    @Override
    public Fragment getItem(int position){

        switch (position) {

            case 0:
                return new CacheFragment();

            case 1:
                return new FavouritesFragment();

            default:
                return new CacheFragment();

        }

    }

}
