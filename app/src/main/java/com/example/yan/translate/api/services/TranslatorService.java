package com.example.yan.translate.api.services;

import com.example.yan.translate.api.model.Translation;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

//for calling Yandex Translate

public interface TranslatorService {

    @GET("/api/v1.5/tr.json/translate")
    Call<Translation> translateText(@Query("text") String inputText, @Query("lang") String language, @Query("key") String key);

}
