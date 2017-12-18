package com.rockacode.ocr.communication;

import com.rockacode.ocr.OcrApplication;
import com.rockacode.ocr.R;
import com.rockacode.ocr.util.AppSharePreferences;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Jan on 28-Feb-16.
 */
public class OcrService {

    private String LOG_TAG = "BenApi";
    public static String API_BASE_URL;

    private static OcrService instance;
    private static OcrApi ocrApi;

    public OcrService() {
        API_BASE_URL = AppSharePreferences.getInstance().getServerUrl();

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        // add your other interceptors …
        // add logging as last interceptor
        httpClient.addInterceptor(logging);
        httpClient.connectTimeout(10, TimeUnit.SECONDS);
        httpClient.readTimeout(30, TimeUnit.SECONDS);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .client(httpClient.build())
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        ocrApi = retrofit.create(OcrApi.class);
    }

    public static void recreate(){
        instance = new OcrService();
    }

    public OcrService getService() {
        return instance;
    }

    public static OcrApi getApi() {
        if(instance == null){
            instance = new OcrService();
        }
        return ocrApi;
    }
}
