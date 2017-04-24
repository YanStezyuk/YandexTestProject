package com.example.yan.translate.util;

import android.text.TextUtils;

// simple methods to work with strings

public class StringUtils {

    public static String EMPTY = "";

    public static boolean emptyOrNull(CharSequence cs) {
        return cs == null || TextUtils.getTrimmedLength(cs) == 0;
    }

    public static boolean notEmpty(CharSequence cs) {
        return !emptyOrNull(cs);
    }

}
