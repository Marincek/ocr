package com.rockacode.ocr.ui.camera;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rockacode.ocr.R;
import com.rockacode.ocr.communication.OcrService;
import com.rockacode.ocr.util.AppSharePreferences;

public class SettingsActivity extends AppCompatActivity {

    private EditText serverUrl;
    private Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        serverUrl = (EditText) findViewById(R.id.server_url);
        save = (Button) findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppSharePreferences.getInstance().saveServerUrl(serverUrl.getText().toString());
                OcrService.recreate();
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        populate();
    }

    private void populate() {
        serverUrl.setText(AppSharePreferences.getInstance().getServerUrl());
    }

}
