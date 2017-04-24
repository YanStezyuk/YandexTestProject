package com.example.yan.translate.ui.adapters.lists;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yan.translate.items.ArticleItem;
import com.example.yan.translate.items.InputTextItem;
import com.example.yan.translate.items.SwapItem;
import com.example.yan.translate.items.TranslateItem;
import com.example.yan.translate.ui.viewholders.ArticleTextViewHolder;
import com.example.yan.translate.ui.viewholders.InputTextViewHolder;
import com.example.yan.translate.ui.viewholders.SimpleViewHolder;
import com.example.yan.translate.ui.viewholders.SwapButtonsViewHolder;
import com.example.yan.translate.ui.viewholders.TranslatedTextViewHolder;

// adapter for main fragment

public class TranslationAdapter extends AbstractItemAdapter {

    private SwapButtonsViewHolder.targetLanguageCallback targetCallback;
    private SwapButtonsViewHolder.sourceLanguageCallback sourceCallback;

    private InputTextViewHolder.inputTextCallback inputCallback;
    private InputTextViewHolder.favouriteCallback favCallback;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(viewType, viewGroup, false);

        switch (viewType) {

            case ArticleItem.VIEW_TYPE:
                viewHolder = new ArticleTextViewHolder(view);
                break;

            case InputTextItem.VIEW_TYPE:
                viewHolder = new InputTextViewHolder(view, inputCallback, favCallback);
                break;

            case SwapItem.VIEW_TYPE:
                viewHolder = new SwapButtonsViewHolder(view, sourceCallback, targetCallback);
                break;

            case TranslateItem.VIEW_TYPE:
                viewHolder = new TranslatedTextViewHolder(view);
                break;

            default:
                viewHolder = new SimpleViewHolder(view);
                break;

        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        switch (viewHolder.getItemViewType()) {

            case ArticleItem.VIEW_TYPE:
                ArticleTextViewHolder vh1 = (ArticleTextViewHolder) viewHolder;
                configureViewHolderArticle(vh1, position);
                break;

            case InputTextItem.VIEW_TYPE:
                InputTextViewHolder vh2 = (InputTextViewHolder) viewHolder;
                configureViewHolderEnter(vh2, position);
                break;

            case SwapItem.VIEW_TYPE:
                SwapButtonsViewHolder vh3 = (SwapButtonsViewHolder) viewHolder;
                configureViewHolderSwap(vh3, position);
                break;

            case TranslateItem.VIEW_TYPE:
                TranslatedTextViewHolder vh4 = (TranslatedTextViewHolder) viewHolder;
                configureViewHolderTranslator(vh4, position);
                break;

            default:
                SimpleViewHolder vh = (SimpleViewHolder) viewHolder;
                configureDefaultViewHolder(vh, position);
                break;
        }
    }

    private void configureViewHolderTranslator(TranslatedTextViewHolder vh4, int position) {

        TranslateItem translate = (TranslateItem) items.get(position);
        vh4.getTranslatedText().setText(translate.getResultText());

    }

    private void configureViewHolderSwap(SwapButtonsViewHolder vh3, int position) { }

    private void configureDefaultViewHolder(SimpleViewHolder vh, int position) {

        vh.getLabel().setText((CharSequence) items.get(position));

    }

    private void configureViewHolderEnter(InputTextViewHolder vh2, int position) {

        InputTextItem enter = (InputTextItem) items.get(position);
        vh2.getInputText().setText(enter.getSourceText());

    }

    private void configureViewHolderArticle(ArticleTextViewHolder vh1, int position) {

        ArticleItem article = (ArticleItem) items.get(position);
        vh1.getArticleText().setText(article.getTranslatedText());

    }

    public void setSwapLanguagesCallback(SwapButtonsViewHolder.sourceLanguageCallback sourceCallback,
                                         SwapButtonsViewHolder.targetLanguageCallback targetCallback) {
        this.targetCallback = targetCallback;
        this.sourceCallback = sourceCallback;
    }

    public void setInputTextCallback(InputTextViewHolder.inputTextCallback inputCallback,
                                     InputTextViewHolder.favouriteCallback favCallback) {
        this.inputCallback = inputCallback;
        this.favCallback = favCallback;
    }

}
