package com.example.yan.translate.ui.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.yan.translate.ui.adapters.lists.CacheAdapter;
import com.example.yan.translate.events.CacheClearingEvent;
import com.example.yan.translate.db.model.CacheModel;
import com.example.yan.translate.db.HistoryManager;
import com.example.yan.translate.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;


// fragment for previous requests

public class CacheFragment extends Fragment {

    private CacheAdapter adapter;
    private List<CacheModel> list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_cache, container, false);
        RecyclerView recyclerView = (RecyclerView) v;

        list = HistoryManager.getCache();

        adapter = new CacheAdapter(getContext(), list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        for(int i=0; i < list.size(); i++)
            Log.d("APP TAG", list.get(i).getSourceText() + list.get(i).isFavourite());

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(CacheClearingEvent event){
        HistoryManager.clearCacheTable();
        list.clear();
        adapter.notifyDataSetChanged();
        Toast.makeText(getActivity(), "Cache cleared" , Toast.LENGTH_SHORT);
    }
}
