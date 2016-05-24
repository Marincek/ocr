package com.rockacode.ocr.communication;

import com.rockacode.ocr.domain.ResponsePhoto;
import com.rockacode.ocr.domain.ResponseText;

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
    @POST("/processphoto")
    Call<ResponsePhoto>  uploadPhotoForProcessing(
            @Part("file\"; filename=\"image.png\" ") RequestBody file);

    @Multipart
    @POST("/processtext")
    Call<ResponseText>  uploadPhotoForProcessingText(
            @Part("file\"; filename=\"image.png\" ") RequestBody file, @Part("lang") RequestBody language);

}
