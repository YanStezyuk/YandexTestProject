package com.example.yan.translate;

import android.app.Application;

import com.example.yan.translate.api.services.DictService;
import com.example.yan.translate.api.services.TranslatorService;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// initialization of Retrofit and TranslatorService

public class TranslationApp extends Application {

    private TranslatorService translatorService;
    private DictService dictService;

    private Retrofit.Builder serviceBuilder;

    @Override
    public void onCreate() {
        super.onCreate();

        // Init database.
        FlowManager.init(new FlowConfig.Builder(this).build());

        // Init API services.
        serviceBuilder = new Retrofit.Builder()
                .client(buildClient())
                .addConverterFactory(GsonConverterFactory.create());

        dictService = buildDictService();
        translatorService = buildTranslatorService();
    }

    private TranslatorService buildTranslatorService() {
        return serviceBuilder
                .baseUrl("https://translate.yandex.net/")
                .build()
                .create(TranslatorService.class);
    }

    private DictService buildDictService() {
        return serviceBuilder
                .baseUrl("https://dictionary.yandex.net/")
                .build()
                .create(DictService.class);
    }

    private OkHttpClient buildClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);
        }

        return builder.build();
    }

    public TranslatorService getTranslatorService() {
        return translatorService;
    }

    public DictService getDictService() {
        return dictService;
    }

}
