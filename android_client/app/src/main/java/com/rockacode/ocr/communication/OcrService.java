package com.rockacode.ocr.communication;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by Jan on 28-Feb-16.
 */
public interface OcrService {

    @Multipart
    @POST("/processphoto")
    Call<ResponseBody>  uploadPhotoForProcessing(
            @Part("file\"; filename=\"image.png\" ") RequestBody file);

    @Multipart
    @POST("/processtext")
    Call<ResponseBody>  uploadPhotoForProcessingText(
            @Part("file\"; filename=\"image.png\" ") RequestBody file, @Part("lang") RequestBody language);

}
