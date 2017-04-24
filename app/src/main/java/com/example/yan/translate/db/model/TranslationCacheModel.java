package com.example.yan.translate.db.model;

import com.example.yan.translate.db.TranslationDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

// model for storing input data table

@Table(database = TranslationDatabase.class)

public class TranslationCacheModel extends BaseModel {

    @PrimaryKey
    private long id;

    @Column
    private String sourceText;

    @Column
    private String targetText;

    @Column
    private String articleText;

    @Column
    private int sourceLang;

    @Column
    private int targetLang;

    public long getId() {
        return id;
    }

    public String getSourceText() {
        return sourceText;
    }

    public String getTargetText() {
        return targetText;
    }

    public String getArticleText() {
        return articleText;
    }

    public int getSourceLang() {
        return sourceLang;
    }

    public int getTargetLang() {
        return targetLang;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setSourceText(String sourceText) {
        this.sourceText = sourceText;
    }

    public void setTargetText(String targetText) {
        this.targetText = targetText;
    }

    public void setArticleText(String articleText) {
        this.articleText = articleText;
    }

    public void setSourceLang(int sourceLang) {
        this.sourceLang = sourceLang;
    }

    public void setTargetLang(int targetLang) {
        this.targetLang = targetLang;
    }

}
