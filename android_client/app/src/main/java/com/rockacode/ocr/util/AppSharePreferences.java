package com.rockacode.ocr.util;


import com.rockacode.ocr.OcrApplication;
import com.rockacode.ocr.R;

import java.util.Set;


public class AppSharePreferences extends BasePreferences {

    private static AppSharePreferences instance;

    // constants
    private static final String PREFERENCE_NAME = "application_pref";

    private static final String SERVER_URL = "server_url";

    public static AppSharePreferences getInstance() {
        if (instance == null) {
            instance = new AppSharePreferences();
        }
        return instance;
    }

    private AppSharePreferences() {
        manager = OcrApplication.getContext().getSharedPreferences(PREFERENCE_NAME, 0);
    }

    private void clear() {
        manager.edit().clear().apply();
    }

    public void saveServerUrl(String url) {
        setValue(SERVER_URL, url);
    }


    public String getServerUrl() {
        return manager.getString(SERVER_URL, OcrApplication.getContext().getString(R.string.base_url));
    }

}
