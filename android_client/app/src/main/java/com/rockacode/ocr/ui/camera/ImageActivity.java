package com.rockacode.ocr.ui.camera;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.github.clans.fab.FloatingActionButton;
import com.rockacode.ocr.R;
import com.squareup.picasso.Picasso;

public class ImageActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView imageView;
    private Uri filePath;

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
    }

    private void openSettings() {
    }

    private void sendPhotoToProcess(boolean ocr) {

    }
}
