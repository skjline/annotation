package com.skjline.example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.skjline.example.model.GeneratedRequestModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GeneratedRequestModel model = new GeneratedRequestModel();
        Log.wtf("Annotation", "annotation model: " + model.getMessage());
    }
}
