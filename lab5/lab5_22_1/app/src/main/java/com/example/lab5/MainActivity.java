package com.example.lab5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnClick(View view) {
        Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
        startActivityForResult(intent, 1);  // Исправлено с startActivity на startActivityForResult
    }

    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null) {
            String text = "";
            TextView tv = findViewById(R.id.forPrefer);
            switch (reqCode) {
                case 1:
                    switch (data.getExtras().getInt("Complexity") % 3) {
                        case 1:
                            text = "Легкий";
                            break;
                        case 2:
                            text = "Средний";
                            break;
                        case 0:
                            text = "Сложный";
                            break;
                    }
                    tv.setText(text);
                    break;
            }
        }
    }
    public void openSettings(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    public void openAuthor(View view) {
        Intent intent = new Intent(this, AuthorActivity.class);
        startActivity(intent);
    }
}