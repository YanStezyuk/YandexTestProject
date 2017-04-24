package com.example.yan.translate.api;

import retrofit2.Call;

// api helper

public class ApiHelper {

    public static void closeSafely(Call... calls) {
        for (Call call : calls) {
            if (call != null && call.isExecuted() && !call.isCanceled()) {
                call.cancel();
            }
        }
    }

}
