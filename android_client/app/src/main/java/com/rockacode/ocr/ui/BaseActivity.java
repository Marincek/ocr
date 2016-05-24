package com.rockacode.ocr.ui;

import android.support.v7.app.AppCompatActivity;

import com.octo.android.robospice.SpiceManager;
import com.rockacode.ocr.communication.InMemorySpiceService;

/**
 * Created by Marincek on 28-Mar-16.
 */

public class BaseActivity extends AppCompatActivity{

    protected SpiceManager spiceManager = new SpiceManager(InMemorySpiceService.class);

    @Override
    public void onStart() {
        super.onStart();
        spiceManager.start(this);
    }

    @Override
    public void onStop() {
        // Please review https://github.com/octo-online/robospice/issues/96 for the reason of that
        // ugly if statement.
        if (spiceManager.isStarted()) {
            spiceManager.shouldStop();
        }
        super.onStop();
    }

}
