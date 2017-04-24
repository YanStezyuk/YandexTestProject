package com.example.yan.translate.items;

import com.example.yan.translate.R;

// text from yandex translate

public class TranslateItem extends Item {

    public static final int VIEW_TYPE = R.layout.layout_viewholder_translate;

    private String resultText;

    public String getResultText() {
        return resultText;
    }

    public void setResultText(String resultText) {
        this.resultText = resultText;
    }

    public int getViewType() {
        return VIEW_TYPE;
    }

}
