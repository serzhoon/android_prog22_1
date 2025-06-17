package com.example.lab9_new;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private TextView numberTextView;
    private int number = 0;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        numberTextView = findViewById(R.id.numberTextView);
        numberTextView.setText(String.valueOf(number));

        registerForContextMenu(numberTextView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.menu_red) {
            numberTextView.setTextColor(Color.RED);
            return true;
        } else if (itemId == R.id.menu_green) {
            numberTextView.setTextColor(Color.GREEN);
            return true;
        } else if (itemId == R.id.menu_blue) {
            numberTextView.setTextColor(Color.BLUE);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.context1) {
            number += 10;
            numberTextView.setText(String.valueOf(number));
            return true;
        } else if (itemId == R.id.context2) {
            number -= 10;
            numberTextView.setText(String.valueOf(number));
            return true;
        }
        return super.onContextItemSelected(item);
    }
}