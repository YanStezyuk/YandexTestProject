package com.example.yan.translate.items;

import com.example.yan.translate.R;

// text from yandex dictionary

public class ArticleItem extends Item {

    public static final int VIEW_TYPE = R.layout.layout_viewholder_article;

    private String translatedText;

    public String getTranslatedText() {
        return translatedText;
    }

    public void setTranslatedText(String translatedText) {
        this.translatedText = translatedText;
    }

    public int getViewType() {
        return VIEW_TYPE;
    }

}
