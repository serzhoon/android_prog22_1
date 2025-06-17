package com.example.lab6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.Timer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("Тэг", "Сообщение");
        Log.e("Фатальная ошибка", "Все пропало!");
    }

    public void click1(View v){
        Timer timer = new Timer();
        Scheduler task = new Scheduler();
        timer.schedule(task, 1000);
    }

    public void click2(View v) {
        Intent intent = new Intent(this, Seconds.class);
        startActivity(intent);
    }
}
