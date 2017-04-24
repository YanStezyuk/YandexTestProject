package com.example.yan.translate.ui.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.yan.translate.R;

// holder for text from yandex translate

public class TranslatedTextViewHolder extends RecyclerView.ViewHolder {

    private TextView translatedText;

    public TranslatedTextViewHolder(View v){
        super(v);
        translatedText = (TextView) v.findViewById(R.id.translated_text);
    }

    public TextView getTranslatedText() {
        return translatedText;
    }

    public void setTranslatedText(TextView translatedText) {
        this.translatedText = translatedText;
    }
}
