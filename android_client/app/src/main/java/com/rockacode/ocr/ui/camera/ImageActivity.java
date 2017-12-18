package com.rockacode.ocr.ui.camera;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.rockacode.ocr.R;
import com.rockacode.ocr.communication.tasks.PhotoToTextTask;
import com.rockacode.ocr.communication.tasks.SendPhotoTask;
import com.rockacode.ocr.domain.ResponsePhoto;
import com.rockacode.ocr.domain.ResponseText;
import com.rockacode.ocr.ui.BaseActivity;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;

import rx.Subscriber;

public class ImageActivity extends BaseActivity implements View.OnClickListener {

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
    }

    private void handleIntent(Intent intent) {
        filePath = intent.getData();
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
        if(filePath.toString().startsWith("/stor")){
            filePath = Uri.parse("file://"+filePath.toString());
        }
        Picasso.with(this).load(filePath).placeholder(R.drawable.ic_menu_camera).fit().centerInside().into(imageView);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.menu_item_process_photo:
                sendPhotoToProcess(false);
                break;
            case R.id.menu_item_proces_photo_ocr:
                sendPhotoToProcess(true);
                break;
        }
        floatingActionMenu.close(true);
    }

    private void openSettings() {
    }

    private void sendPhotoToProcess(boolean ocr) {
        if (ocr) {
            new PhotoToTextTask(filePath).execute().subscribe(responseText -> Toast.makeText(getBaseContext(),responseText.getText(),Toast.LENGTH_LONG).show());
        } else {
            new SendPhotoTask(filePath).execute().subscribe(responsePhoto -> saveAndShowPhoto(responsePhoto));
        }
    }

    private void saveAndShowPhoto(ResponsePhoto responsePhoto){
        File photo = new File(Environment.getExternalStorageDirectory(), "photo.jpg");

        if (photo.exists()) {
            photo.delete();
        }

        try {
            FileOutputStream fos = new FileOutputStream(photo.getPath());
            fos.write(responsePhoto.getPhoto());
            fos.close();
        } catch (java.io.IOException e) {
            e.fillInStackTrace();
        }

        Picasso.with(ImageActivity.this).load(photo).placeholder(R.drawable.ic_menu_camera).fit().centerInside().into(imageView);
    }
}
