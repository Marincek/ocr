package com.rockacode.ocr;

import android.app.Application;
import android.content.Context;

/**
 * Created by Jan on 28-Feb-16.
 */
public class OcrApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        context = getBaseContext();

    }

    public static Context getContext() {
        return context;
    }

}
