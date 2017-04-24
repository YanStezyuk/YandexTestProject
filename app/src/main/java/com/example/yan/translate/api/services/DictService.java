package com.example.yan.translate.api.services;

import com.example.yan.translate.api.model.Article;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

//for calling Yandex Dict

public interface DictService {
    @GET("/api/v1/dicservice.json/lookup")
    Call<Article> getArticle(@Query("text") String inputText, @Query("lang") String language, @Query("key") String key);
}
