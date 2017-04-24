package com.example.yan.translate.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yan.translate.R;
import com.example.yan.translate.api.ApiHelper;
import com.example.yan.translate.api.model.Article;
import com.example.yan.translate.api.model.Translation;
import com.example.yan.translate.db.HistoryManager;
import com.example.yan.translate.db.model.CacheModel;
import com.example.yan.translate.db.model.CacheModel_Table;
import com.example.yan.translate.db.model.FavouritesModel;
import com.example.yan.translate.db.model.TranslationCacheModel;
import com.example.yan.translate.items.ArticleItem;
import com.example.yan.translate.items.InputTextItem;
import com.example.yan.translate.items.SwapItem;
import com.example.yan.translate.items.TranslateItem;
import com.example.yan.translate.ui.adapters.lists.TranslationAdapter;
import com.example.yan.translate.ui.viewholders.InputTextViewHolder;
import com.example.yan.translate.ui.viewholders.SwapButtonsViewHolder;
import com.example.yan.translate.util.Logger;
import com.example.yan.translate.util.StringUtils;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// starting fragment including swap buttons, input field, translated text, synonyms, etc

public class TranslationFragment extends BaseFragment {

    private Call<Translation> translationCall;
    private Call<Article> articleCall;

    public String means;
    public ArrayList<String> meansList = new ArrayList<>();

    public String inputString;
    public String articleString;
    public String translateString;

    private TranslationCacheModel translation;

    private static final String API_KEY_TRANSLATOR = "trnsl.1.1.20170316T175754Z.f808d7cd324c4396.deb9028efd22d9a4d802893a80d678cd3e9afc86";
    private static final String API_KEY_DICT = "dict.1.1.20170320T172947Z.7363aa6e92f77be4.0c085151f45304fe3fc89ac75d4cb107aca64d83";

    private static final String STATE_SELECTED_SOURCE_LANG_INDEX = "state_selected_source_lang_index";
    private static final String STATE_SELECTED_TARGET_LANG_INDEX = "state_selected_target_lang_index";
    private static final String STATE_SOURCE_TEXT = "state_source_text";
    private static final String STATE_TRANSLATION = "state_translation";
    private static final String STATE_ARTICLE = "state_article";

    private static final String FORMAT_LANGUAGE_PARAM = "%1$s-%2$s";

    private TranslationAdapter adapter;

    private int sourceLangIdx = 0;
    private int targetLangIdx = 1;

    @BindArray(R.array.abblist) String[] ablang;
    @BindView(R.id.recyclerMain) RecyclerView complexRecycler;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Retrieve cached user input,
        translation = HistoryManager.getLastTranslation();
        if (translation == null) {
            translation = new TranslationCacheModel();
        }

        adapter = new TranslationAdapter();
        adapter.setSwapLanguagesCallback(
                new SwapButtonsViewHolder.sourceLanguageCallback() {

                    @Override
                    public void handle(int selectedLanguageIdx) {
                        translation.setSourceLang(selectedLanguageIdx);
                    }

                },
                new SwapButtonsViewHolder.targetLanguageCallback() {

                    @Override
                    public void handle(int selectedLanguageIdx) {
                        translation.setTargetLang(selectedLanguageIdx);
                    }

                }
        );

        adapter.setInputTextCallback(
                new InputTextViewHolder.inputTextCallback() {
                    @Override
                    public void handle(String inputText) {
                        inputString = inputText;
                        translateText(inputText);
                    }
                },
                new InputTextViewHolder.favouriteCallback() {
                    @Override
                    public void handle() {
                        addToFavourites();
                    }
                }
        );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_translation, container, false);
        ButterKnife.bind(this, v);

        SwapItem swap = new SwapItem();
        InputTextItem enter = new InputTextItem();

        adapter.insert(0, swap);
        adapter.insert(1, enter);

        swap.setLanguages(translation.getSourceLang(), translation.getTargetLang());
        enter.setSourceText(translation.getSourceText());

        inputString = "";
        articleString = "";
        translateString = "";


        complexRecycler.setAdapter(adapter);
        complexRecycler.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false));

        if (savedInstanceState != null) {

            sourceLangIdx = savedInstanceState.getInt(STATE_SELECTED_SOURCE_LANG_INDEX, 0);
            targetLangIdx = savedInstanceState.getInt(STATE_SELECTED_TARGET_LANG_INDEX, 1);
            swap.setSourceLangIndex(sourceLangIdx);
            swap.setTargetLangIndex(targetLangIdx);
            adapter.notifyItemChanged(0);

            String previousSourceText = savedInstanceState.getString(STATE_SOURCE_TEXT, StringUtils.EMPTY);
            enter.setSourceText(previousSourceText);
            inputString = previousSourceText;
            adapter.notifyItemChanged(1);

            String previousTranslation = savedInstanceState.getString(STATE_TRANSLATION, StringUtils.EMPTY);
            TranslateItem translateItem = new TranslateItem();
            adapter.insert(2, translateItem);
            adapter.notifyItemChanged(2);
            translateItem.setResultText(previousTranslation);
            translateString = previousTranslation;

            String previousArticle = savedInstanceState.getString(STATE_ARTICLE, StringUtils.EMPTY);
            if (previousArticle != null) {

                ArticleItem articleItem = new ArticleItem();
                adapter.insert(3, articleItem);
                adapter.notifyItemChanged(3);
                articleItem.setTranslatedText(previousArticle);
                articleString = previousArticle;

            }
        }


        if (HistoryManager.getLastTranslation() != null) {
            final TranslationCacheModel inputModel = HistoryManager.getLastTranslation();
            sourceLangIdx = inputModel.getSourceLang();
            targetLangIdx = inputModel.getTargetLang();
            swap.setSourceLangIndex(sourceLangIdx);
            swap.setTargetLangIndex(targetLangIdx);
            adapter.notifyItemChanged(0);

            String previousSourceText = inputModel.getSourceText();
            enter.setSourceText(previousSourceText);
            adapter.notifyItemChanged(1);
            inputString = previousSourceText;

            if (inputModel.getTargetText() != null) {

                String previousTranslation = inputModel.getTargetText();
                TranslateItem translateItem = new TranslateItem();
                adapter.insert(2, translateItem);
                adapter.notifyItemChanged(2);
                translateString = previousTranslation;
                translateItem.setResultText(previousTranslation);

            }

            if (inputModel.getArticleText() != null) {

                String previousArticle = inputModel.getArticleText();
                ArticleItem articleItem = new ArticleItem();
                adapter.insert(3, articleItem);
                adapter.notifyItemChanged(3);
                articleString = previousArticle;
                articleItem.setTranslatedText(previousArticle);

            }
        }

        return v;
    }

    public void addToFavourites() {

        final FavouritesModel favouritesModel = new FavouritesModel();
        if ((inputString != null) && (translateString != null)) {

            favouritesModel.setSourceText(inputString);
            favouritesModel.setTargetText(translateString);
            favouritesModel.setTranslateDirection(String.format(FORMAT_LANGUAGE_PARAM, ablang[sourceLangIdx], ablang[targetLangIdx]));
            favouritesModel.save();

            if (SQLite.select().from(CacheModel.class).
                    where((CacheModel_Table.sourceText.is(inputString))).
                    and(CacheModel_Table.targetText.is(translateString)).
                    querySingle() != null) {

                CacheModel element = SQLite.select().from(CacheModel.class).
                        where((CacheModel_Table.sourceText.is(inputString))).
                        and(CacheModel_Table.targetText.is(translateString)).
                        querySingle();
                element.setFavourite(true);
                element.save();

            }
        }
    }

    public void translateText(String text) {

        final TranslateItem translateItem = new TranslateItem();

        if (adapter.getItemCount() > 2) {

            adapter.set(2, translateItem);
            Logger.d(this, "translate added");

        } else {

            adapter.insert(2, translateItem);
            Logger.d(this, "translate added");

        }

        translateItem.setResultText("");
        final String lang = String.format(FORMAT_LANGUAGE_PARAM, ablang[sourceLangIdx], ablang[targetLangIdx]);
        final CacheModel cacheModel = new CacheModel();
        final String sourceText = text;

        translationCall = getApp().getTranslatorService().translateText(text, lang, API_KEY_TRANSLATOR);
        translationCall.enqueue(new Callback<Translation>() {

            @Override
            public void onResponse(Call<Translation> call, Response<Translation> response) {
                Translation answer = response.body();
                if (answer != null && answer.getText() != null && answer.getText().length > 0) {

                    translateItem.setResultText(answer.getText()[0]);
                    translateString = answer.getText()[0];

                    adapter.notifyItemChanged(2);

                    inputString = sourceText;
                    cacheModel.setSourceText(sourceText);
                    cacheModel.setTargetText(answer.getText()[0]);
                    cacheModel.setTranslateDirection(lang);
                    cacheModel.save();

                } else {
                    translateItem.setResultText("");
                    Logger.d(this, "TRANSLATE:Something is wrong :(");
                }
            }

            @Override
            public void onFailure(Call<Translation> call, Throwable t) {
                Logger.d(this, "An error occurred during networking");

            }

        });

        means = "";

        if (!text.contains(" ") && !text.contains(",") && !text.contains(":")) {

            final ArticleItem articleItem = new ArticleItem();
            articleItem.setTranslatedText("");

            if (adapter.getItemCount() > 3) {
                adapter.set(3, articleItem);

            } else {
                adapter.insert(3, articleItem);
            }

            articleCall = getApp().getDictService().getArticle(text, lang, API_KEY_DICT);
            articleCall.enqueue(new Callback<Article>() {

                @Override
                public void onResponse(Call<Article> call, Response<Article> response1) {

                    Article answer = response1.body();
                    if (answer != null  && answer.getDef() != null && answer.getDef().size() > 0) {

                        meansList = new ArrayList<>();
                        for (int j = 0; j < answer.getDef().get(0).getTr().size(); j++) {

                            if (answer.getDef().get(0).getTr().get(j).getSyn() != null)
                                for (int i = 0; i < answer.getDef().get(0).getTr().get(j).getSyn().size(); i++) {

                                    if (answer.getDef().get(0).getTr().get(j).getSyn().get(i) != null) {
                                        means += answer.getDef().get(0).getTr().get(j).getSyn().get(i).getText() + " ";
                                    }

                                }
                            means += answer.getDef().get(0).getTr().get(j).getText() + String.format("%n");

                        }
                        articleItem.setTranslatedText(means);
                        articleString = means;
                        adapter.notifyItemChanged(3);

                    } else {
                        Logger.d(this, "DICT:Something is wrong :(");
                        articleItem.setTranslatedText("");

                    }
                }

                @Override
                public void onFailure(Call<Article> call, Throwable t) {
                    Logger.d(this, "An error occurred during networking");
                }
            });
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);
        outState.putInt(STATE_SELECTED_SOURCE_LANG_INDEX, sourceLangIdx);
        outState.putInt(STATE_SELECTED_TARGET_LANG_INDEX, targetLangIdx);
        outState.putString(STATE_SOURCE_TEXT, inputString);
        outState.putString(STATE_TRANSLATION, translateString);
        outState.putString(STATE_ARTICLE, articleString);

    }

    @Override
    public void onDestroy() {

        final TranslationCacheModel inputModel = new TranslationCacheModel();
        inputModel.setId(0L);
        if (inputString != null)
            inputModel.setSourceText(inputString);
        if (translateString != null)
            inputModel.setTargetText(translateString);
        inputModel.setSourceLang(sourceLangIdx);
        inputModel.setTargetLang(targetLangIdx);
        if (means != null)
            inputModel.setArticleText(articleString);
        inputModel.save();

        ApiHelper.closeSafely(articleCall, translationCall);
        super.onDestroy();

    }

}