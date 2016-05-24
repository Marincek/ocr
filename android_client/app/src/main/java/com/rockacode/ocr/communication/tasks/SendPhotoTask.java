package com.rockacode.ocr.communication.tasks;

import android.net.Uri;

import com.rockacode.ocr.domain.ResponsePhoto;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Marincek on 26-Apr-16.
 */

public class SendPhotoTask extends BaseRequest<ResponsePhoto> {

    private Uri imageUri;

    public SendPhotoTask(Uri imageUri) {
        super(ResponsePhoto.class);
        this.imageUri = imageUri;
    }

    @Override
    public ResponsePhoto loadDataFromNetwork() throws Exception {
        File file = new File(imageUri.getPath());
        RequestBody fbody = RequestBody.create(MediaType.parse("image/*"), file);
        Call<ResponsePhoto> call = api.uploadPhotoForProcessing(fbody);
        Response<ResponsePhoto> result = call.execute();
//        call.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//
//            }
//        });

        return result.body();
    }
}
