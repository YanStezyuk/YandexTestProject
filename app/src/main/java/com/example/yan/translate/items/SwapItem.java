package com.example.yan.translate.items;

import com.example.yan.translate.R;

// target and source languages, swap button

public class SwapItem extends Item {

    public static final int VIEW_TYPE = R.layout.layout_viewholder_swap;

    private int sourceLangIndex;
    private int targetLangIndex;

    public void setLanguages(int sourceLangIndex, int targetLangIndex) {
        this.sourceLangIndex = sourceLangIndex;
        this.targetLangIndex = targetLangIndex;
    }

    public int getTargetLangIndex() {
        return targetLangIndex;
    }

    public void setTargetLangIndex(int targetLangIndex) {
        this.targetLangIndex = targetLangIndex;
    }

    public int getSourceLangIndex() {
        return sourceLangIndex;
    }

    public void setSourceLangIndex(int sourceLangIndex) {
        this.sourceLangIndex = sourceLangIndex;
    }

    public int getViewType() {
        return VIEW_TYPE;
    }

}
