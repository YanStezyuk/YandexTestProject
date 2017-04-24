package com.example.yan.translate.items;

import com.example.yan.translate.R;

// source text to translate

public class InputTextItem extends Item {

    public static final int VIEW_TYPE = R.layout.layout_viewholder_input;

    private String sourceText;

    public String getSourceText() {
        return sourceText;
    }

    public void setSourceText(String sourceText) {
        this.sourceText = sourceText;
    }

    public int getViewType() {
        return VIEW_TYPE;
    }

}
