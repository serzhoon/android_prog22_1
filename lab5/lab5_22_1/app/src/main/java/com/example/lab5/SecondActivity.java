package com.example.lab5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    RadioGroup rbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_view);

        rbtn = findViewById(R.id.rbtn);
        rbtn.clearCheck();
    }

    public void onClick(View v) {
        Intent intent = new Intent();
        int selectedId = rbtn.getCheckedRadioButtonId();

        // Определение сложности по выбранному RadioButton
        int complexity = 0;
        if (selectedId == R.id.rbtn_easy) {
            complexity = 1;
        } else if (selectedId == R.id.rbtn_medium) {
            complexity = 2;
        } else if (selectedId == R.id.rbtn_hard) {
            complexity = 3;
        }

        intent.putExtra("Complexity", complexity);
        setResult(RESULT_OK, intent);
        finish();
    }
}