package com.example.yan.translate.ui.fragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.yan.translate.events.CacheClearingEvent;
import com.example.yan.translate.events.FavouritesClearingEvent;
import com.example.yan.translate.ui.adapters.pagers.HistoryPagerAdapter;
import com.example.yan.translate.R;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;

// fragment includes cached and favourite requests

public class HistoryFragment extends Fragment {

    @BindView(R.id.viewpager) ViewPager viewPager;
    @BindView(R.id.tabs) TabLayout tabLayout;
    @BindView(R.id.clear_cache_button) ImageButton clearCacheButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_history, container, false);
        ButterKnife.bind(this, v);

        final HistoryPagerAdapter pagerAdapter = new HistoryPagerAdapter(getChildFragmentManager(), this.getContext());
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        clearCacheButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                switch (tabLayout.getSelectedTabPosition()){
                    case 0:
                        EventBus.getDefault().post(new CacheClearingEvent());
                        break;
                    case 1:
                        EventBus.getDefault().post(new FavouritesClearingEvent());
                        break;
                }

                pagerAdapter.notifyDataSetChanged();

            }

        });

        return v;
    }

}
