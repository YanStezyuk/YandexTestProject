package com.example.yan.translate.ui.adapters.lists;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yan.translate.db.model.CacheModel;
import com.example.yan.translate.R;
import com.example.yan.translate.ui.viewholders.CacheViewHolder;

import java.util.List;

// adapter for cache fragment

public class CacheAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<CacheModel> items;
    private Context context;

    public CacheAdapter(Context context, List<CacheModel> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    public Context getContext() {
        return context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.cache_item, parent, false);
        CacheViewHolder viewHolder = new CacheViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        CacheViewHolder holder = (CacheViewHolder) viewHolder;
        CacheModel cacheItem = items.get(position);
        TextView sourceText = holder.getSourceText();
        TextView targetText = holder.getTargetText();
        TextView translateDirection = holder.getTranslateDirection();
        ImageView favLabel = holder.getFavLabel();

        if (cacheItem.isFavourite()) {
            DrawableCompat.setTint(
                    favLabel.getDrawable(), ContextCompat.getColor(context, R.color.favTrue)
            );
        } else {
            DrawableCompat.setTint(
                    favLabel.getDrawable(), ContextCompat.getColor(context, R.color.favFalse)
            );
        }

        sourceText.setText(cacheItem.getSourceText());
        targetText.setText(cacheItem.getTargetText());
        translateDirection.setText(cacheItem.getTranslateDirection());

    }
}
