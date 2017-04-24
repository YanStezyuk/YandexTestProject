package com.example.yan.translate.db;

import com.raizlabs.android.dbflow.annotation.Database;

// database storing history, cache and input data

@Database(name = TranslationDatabase.NAME, version = TranslationDatabase.VERSION)

public class TranslationDatabase {

    public static final String NAME = "HistoryDatabase";

    public static final int VERSION = 1;

}
