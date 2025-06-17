package com.example.lab6;

import android.util.Log;

import java.util.TimerTask;

public class Scheduler extends TimerTask {
    @Override
    public void run(){
        Log.i("Информация", "Таймер сработал");
    }
}
