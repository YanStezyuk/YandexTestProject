package com.example.yan.translate.ui.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yan.translate.R;

import butterknife.BindView;
import butterknife.ButterKnife;

// holder for cache item

public class CacheViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.sourceText) TextView sourceText;
    @BindView(R.id.targetText) TextView targetText;
    @BindView(R.id.translateDirection) TextView translateDirection;
    @BindView(R.id.favouriteLabel) ImageView favLabel;

    public CacheViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public ImageView getFavLabel() {
        return favLabel;
    }

    public void setFavLabel(ImageView favLabel) {
        this.favLabel = favLabel;
    }

    public TextView getTranslateDirection() {
        return translateDirection;
    }

    public void setTranslateDirection(TextView translateDirection) {
        this.translateDirection = translateDirection;
    }

    public TextView getTargetText() {
        return targetText;
    }

    public void setTargetText(TextView targetText) {
        this.targetText = targetText;
    }

    public TextView getSourceText() {
        return sourceText;
    }

    public void setSourceText(TextView sourceText) {
        this.sourceText = sourceText;
    }

}
