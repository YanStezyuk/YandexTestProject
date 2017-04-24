package com.example.yan.translate.db;

import com.example.yan.translate.db.model.CacheModel;
import com.example.yan.translate.db.model.FavouritesModel;
import com.example.yan.translate.db.model.TranslationCacheModel;
import com.raizlabs.android.dbflow.sql.language.Delete;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

// manager with methods for history database

public class HistoryManager {

    public static void clearCacheTable(){
        new Delete().from(CacheModel.class).execute();
    }

    public static void clearFavsTable(){
        new Delete().from(FavouritesModel.class).execute();
    }

    public static void clearInputTable(){
        new Delete().from(TranslationCacheModel.class).execute();
    }

    public static TranslationCacheModel getLastTranslation(){
        return SQLite.select().from(TranslationCacheModel.class).querySingle();
    }

    public static List<CacheModel> getCache(){
        return SQLite.select().from(CacheModel.class).queryList();
    }

    public static List<FavouritesModel> getFavs(){
        return SQLite.select().from(FavouritesModel.class).queryList();
    }

}
