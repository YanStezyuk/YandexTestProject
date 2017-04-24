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

import com.example.yan.translate.db.model.FavouritesModel;
import com.example.yan.translate.R;
import com.example.yan.translate.ui.viewholders.CacheViewHolder;

import java.util.List;

// adapter for favourite requests

public class FavouritesAdapter extends RecyclerView.Adapter {

    private List<FavouritesModel> items;
    private Context context;

    public FavouritesAdapter(Context context, List<FavouritesModel> items) {
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
        View view = inflater.inflate(R.layout.cache_item, parent,false);
        CacheViewHolder viewHolder = new CacheViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        CacheViewHolder holder = (CacheViewHolder) viewHolder;
        FavouritesModel favItem = items.get(position);
        TextView sourceText = holder.getSourceText();
        TextView targetText = holder.getTargetText();
        TextView translateDirection = holder.getTranslateDirection();
        ImageView favLabel = holder.getFavLabel();
        DrawableCompat.setTint(favLabel.getDrawable(), ContextCompat.getColor(context, R.color.favTrue));

        sourceText.setText(favItem.getSourceText());
        targetText.setText(favItem.getTargetText());
        translateDirection.setText(favItem.getTranslateDirection());

    }

}
