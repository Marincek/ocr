package com.rockacode.ocr.communication.tasks;

import com.octo.android.robospice.request.SpiceRequest;
import com.rockacode.ocr.communication.OcrService;
import com.rockacode.ocr.communication.ServiceGenerator;

public abstract class BaseRequest<T> extends SpiceRequest<T> {

    private final String LOG_TAG = "BaseRequest";
    protected Exception exception;
    private int ID;
    protected OcrService api;

    public BaseRequest(Class<T> clazz) {
        super(clazz);
        api = ServiceGenerator.getInstance().getService();
    }
}

