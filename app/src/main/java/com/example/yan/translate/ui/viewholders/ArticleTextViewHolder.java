package com.example.yan.translate.ui.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.yan.translate.R;


// holder for text from yandex dictionary

public class ArticleTextViewHolder extends RecyclerView.ViewHolder {

    private TextView articleText;

    public ArticleTextViewHolder(View v){
        super(v);
        articleText = (TextView) v.findViewById(R.id.article_text);
    }

    public TextView getArticleText() {
        return articleText;
    }

    public void setArticleText(TextView articleText) {
        this.articleText = articleText;
    }

}
