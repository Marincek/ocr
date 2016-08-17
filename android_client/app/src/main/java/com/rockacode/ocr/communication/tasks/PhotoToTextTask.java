package com.rockacode.ocr.communication.tasks;

import android.net.Uri;

import com.rockacode.ocr.communication.OcrService;
import com.rockacode.ocr.communication.Parser;
import com.rockacode.ocr.domain.ResponsePhoto;
import com.rockacode.ocr.domain.ResponseText;

import org.json.JSONException;

import java.io.File;
import java.io.UnsupportedEncodingException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Marincek on 26-Apr-16.
 */

public class PhotoToTextTask extends BaseTask<ResponseText> implements Callback<String> {


    private Uri imageUri;

    public PhotoToTextTask(Uri imageUri) {
        this.imageUri = imageUri;
    }

    @Override
    protected void doInBackground() {
        File file = new File(imageUri.getPath());
        RequestBody fbody = RequestBody.create(MediaType.parse("image/*"), file);
        RequestBody langBody = RequestBody.create(MediaType.parse("text/plain"), "mkd");
        OcrService.getApi().uploadPhotoForProcessingText(fbody, langBody).enqueue(this);
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        try {
            onSuccess(Parser.parseText(response));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        t.printStackTrace();
    }
}