package com.rockacode.ocr.communication;

import android.util.Base64;

import com.rockacode.ocr.domain.ResponsePhoto;
import com.rockacode.ocr.domain.ResponseText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import retrofit2.Response;

/**
 * Created by Jan on 15-Aug-16.
 */
public class Parser {


    public static ResponsePhoto parsePhoto(Response<String> response) throws JSONException, UnsupportedEncodingException {
        ResponsePhoto responsePhoto = new ResponsePhoto();
        JSONObject jsonObject = new JSONObject(response.body());
        responsePhoto.setProcessingTime(jsonObject.getLong("processingTime"));
        responsePhoto.setPhoto(Base64.decode(jsonObject.getString("photo"), Base64.DEFAULT));
        return responsePhoto;
    }

    public static ResponseText parseText(Response<String> response) throws JSONException {
        ResponseText responseText = new ResponseText();
        JSONObject jsonObject = new JSONObject(response.body());
        responseText.setProcessingTime(jsonObject.getLong("processingTime"));
        responseText.setText(jsonObject.getString("text"));
        return responseText;
    }
}