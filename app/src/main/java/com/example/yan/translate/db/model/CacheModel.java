package com.example.yan.translate.db.model;

import com.example.yan.translate.db.TranslationDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

// model for storing previous requests table

@Table(database = TranslationDatabase.class)

public class CacheModel extends BaseModel {

    @PrimaryKey(autoincrement = true)
    long id;

    @Column
    String sourceText;

    @Column
    String targetText;

    @Column
    String translateDirection;

    @Column
    int hash;

    @Column (defaultValue = "0")
    boolean favourite;

    public String getSourceText() {
        return sourceText;
    }

    public void setSourceText(String sourceText) {
        this.sourceText = sourceText;
    }

    public String getTargetText() {
        return targetText;
    }

    public void setTargetText(String targetText) {
        this.targetText = targetText;
    }

    public String getTranslateDirection() {
        return translateDirection;
    }

    public void setTranslateDirection(String translateDirection) {
        this.translateDirection = translateDirection;
    }

    public boolean isFavourite() {
        return favourite;
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }

}
