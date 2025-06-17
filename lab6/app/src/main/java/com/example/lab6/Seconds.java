package com.example.lab6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;

public class Seconds extends AppCompatActivity {
    Chronometer chr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seconds);
        chr = (Chronometer) findViewById(R.id.chronometer);
        chr.setBase(SystemClock.elapsedRealtime());
    }

    public void start(View v){
        chr.start();
    }

    public void stop(View v){
        chr.stop();
    }

    public void clear(View v){
        chr.setBase(SystemClock.elapsedRealtime());
    }
}
