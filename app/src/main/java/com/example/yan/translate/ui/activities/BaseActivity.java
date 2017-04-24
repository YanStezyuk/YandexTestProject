package com.example.yan.translate.ui.activities;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.example.yan.translate.TranslationApp;

// activity to use api

public class BaseActivity extends AppCompatActivity {

    protected final TranslationApp getApp() {
        return (TranslationApp) getApplication();
    }

    protected final void replaceFragment(@IdRes int fragmentContainerViewId,
                                         @NonNull Fragment frg, @NonNull String tag) {

        getSupportFragmentManager().beginTransaction()
                .replace(fragmentContainerViewId, frg, tag)
                .commit();
    }

}
