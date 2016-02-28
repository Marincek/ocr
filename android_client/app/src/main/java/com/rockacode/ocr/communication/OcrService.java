package com.rockacode.ocr.communication;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by Jan on 28-Feb-16.
 */
public interface OcrService {

    @Multipart
    @POST("/process")
    Call<String> uploadPhotoForProcessing(
            @Part("myfile\"; filename=\"image.png\" ") RequestBody file,
            @Part("description") RequestBody description);

}
