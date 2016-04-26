package com.rockacode.ocr.ui.camera;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;
import com.octo.android.robospice.request.listener.RequestProgress;
import com.octo.android.robospice.request.listener.RequestProgressListener;
import com.rockacode.ocr.R;
import com.rockacode.ocr.communication.tasks.SendPhotoTask;
import com.rockacode.ocr.domain.ResponsePhoto;
import com.rockacode.ocr.ui.BaseActivity;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;

public class ImageActivity extends BaseActivity implements View.OnClickListener, RequestListener<ResponsePhoto>, RequestProgressListener {

    private ImageView imageView;
    private Uri filePath;

    private FloatingActionMenu floatingActionMenu;
    private FloatingActionButton processPhoto;
    private FloatingActionButton ocrPhoto;
    private FloatingActionButton settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.title_activity_image);

        handleIntent(getIntent());

        init();

//        spiceManager.execute(LoginTask.create("testuser","testpass", this), this);

    }

    private void handleIntent(Intent intent) {
        filePath = (Uri) intent.getExtras().get(Intent.EXTRA_STREAM);
    }

    private void init() {
        imageView = (ImageView) findViewById(R.id.image);
        processPhoto = (FloatingActionButton) findViewById(R.id.menu_item_process_photo);
        processPhoto.setOnClickListener(this);
        ocrPhoto = (FloatingActionButton) findViewById(R.id.menu_item_proces_photo_ocr);
        ocrPhoto.setOnClickListener(this);
        settings = (FloatingActionButton) findViewById(R.id.menu_item_settings);
        settings.setOnClickListener(this);
        floatingActionMenu = (FloatingActionMenu) findViewById(R.id.fab);

        Picasso.with(this).load(filePath).placeholder(R.drawable.ic_menu_camera).fit().centerInside().into(imageView);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.menu_item_process_photo:
                sendPhotoToProcess(false);
                break;
            case R.id.menu_item_proces_photo_ocr:
                sendPhotoToProcess(false);
                break;
            case R.id.menu_item_settings:
                openSettings();
                break;
        }
        floatingActionMenu.close(true);
    }

    private void openSettings() {
    }

    private void sendPhotoToProcess(boolean ocr) {
        if(ocr){

        }else{
            spiceManager.execute(new SendPhotoTask(filePath), this);
        }
    }

    @Override
    public void onRequestFailure(SpiceException spiceException) {

    }

    @Override
    public void onRequestSuccess(ResponsePhoto s) {
        File photo=new File(Environment.getExternalStorageDirectory(), "photo.jpg");

        if (photo.exists()) {
            photo.delete();
        }

        try {
            FileOutputStream fos=new FileOutputStream(photo.getPath());
            fos.write(s.getPhoto());
            fos.close();
        }
        catch (java.io.IOException e) {
            e.fillInStackTrace();
        }

        Picasso.with(this).load(photo).placeholder(R.drawable.ic_menu_camera).fit().centerInside().into(imageView);
    }

    @Override
    public void onRequestProgressUpdate(RequestProgress progress) {
        switch (progress.getStatus()) {
            case PENDING:
                break;
            case COMPLETE:
                break;
        }
    }
}
