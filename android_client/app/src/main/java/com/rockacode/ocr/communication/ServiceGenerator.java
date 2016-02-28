package com.rockacode.ocr.communication;

import com.rockacode.ocr.OcrApplication;
import com.rockacode.ocr.R;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by Jan on 28-Feb-16.
 */
public class ServiceGenerator {

    private String LOG_TAG = "BenApi";
    public static String API_BASE_URL;
    private OcrService service;
    private static ServiceGenerator instance;

    public static ServiceGenerator getInstance() {
        if (instance == null) {
            instance = new ServiceGenerator();
        }
        return instance;
    }

    public ServiceGenerator() {
        API_BASE_URL = OcrApplication.getContext().getString(R.string.base_url);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        service = retrofit.create(OcrService.class);
    }

    public OcrService getService() {
        return service;
    }
}
