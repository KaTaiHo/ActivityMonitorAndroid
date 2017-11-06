package com.example.katai.activityrecognition;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.View;
import android.widget.Button;

public class SpeechRecognitionActivity extends AppCompatActivity implements View.OnClickListener{

    private Button startService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech_recognition);

        startService = (Button) findViewById(R.id.startService);
        startService.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if (view == startService) {
            System.out.println("Button Hit");
            startService( new Intent(this, recognitionService.class));
        }
    }
}
