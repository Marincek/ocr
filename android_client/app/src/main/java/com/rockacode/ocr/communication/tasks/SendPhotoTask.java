package com.rockacode.ocr.communication.tasks;

import android.net.Uri;

import com.rockacode.ocr.communication.OcrService;
import com.rockacode.ocr.communication.Parser;
import com.rockacode.ocr.domain.ResponsePhoto;

import org.json.JSONException;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Marincek on 26-Apr-16.
 */

public class SendPhotoTask extends BaseTask<ResponsePhoto> implements Callback<String> {


    private Uri imageUri;

    public SendPhotoTask(Uri imageUri) {
        this.imageUri = imageUri;
    }

    @Override
    protected void doInBackground() {
        File file = new File(imageUri.getPath());
        RequestBody fbody = RequestBody.create(MediaType.parse("image/*"), file);
        OcrService.getApi().uploadPhotoForProcessing(fbody).enqueue(this);
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        try {
            onSuccess(Parser.parsePhoto(response));
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {

    }
}