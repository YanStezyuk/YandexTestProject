package com.example.yan.translate.util;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.yan.translate.BuildConfig;

public class Logger {

    private static boolean enabled = BuildConfig.DEBUG;

    public static void d(@NonNull Object from, @NonNull String message) {
        if (enabled) {
            Log.d(from.getClass().getSimpleName(), message);
        }
    }

}
