package com.example.yan.translate.ui.adapters.lists;

import android.support.v7.widget.RecyclerView;

import com.example.yan.translate.items.Item;

import java.util.ArrayList;
import java.util.List;

abstract class AbstractItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected List<Item> items;

    public AbstractItemAdapter() {
        items = new ArrayList<>();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        Item item = items.get(position);
        if (item != null) {
            return item.getViewType();
        }
        return -1;
    }

    public void insert(int position, Item item) {
        if (position > items.size() || position < 0) {
            items.add(item);
            notifyItemInserted(items.size() - 1);
        } else {
            items.add(position, item);
            notifyItemInserted(position);
        }
    }

    public void set(int position, Item item) {
        items.set(position, item);
        notifyItemRangeChanged(position, 1);
    }

}
