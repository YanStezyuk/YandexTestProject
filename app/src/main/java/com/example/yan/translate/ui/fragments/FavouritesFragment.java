package com.example.yan.translate.ui.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.yan.translate.ui.adapters.lists.FavouritesAdapter;
import com.example.yan.translate.events.FavouritesClearingEvent;
import com.example.yan.translate.db.model.FavouritesModel;
import com.example.yan.translate.db.HistoryManager;
import com.example.yan.translate.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

// fragment for favourite requests

public class FavouritesFragment extends Fragment {

    FavouritesAdapter adapter;
    List<FavouritesModel> list;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_favourites, container, false);
        RecyclerView recyclerView = (RecyclerView) v;

        list = HistoryManager.getFavs();
        adapter = new FavouritesAdapter(getContext(), list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

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
    public void onEvent(FavouritesClearingEvent event){
        HistoryManager.clearFavsTable();
        list.clear();
        adapter.notifyDataSetChanged();
        Toast.makeText(getActivity(), "Favourites cleared" , Toast.LENGTH_SHORT);
    }

}
