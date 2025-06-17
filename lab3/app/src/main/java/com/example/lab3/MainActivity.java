package com.example.lab3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    //Для задания 1
    /*
    public void OnButtonClick(View v){
        Toast.makeText(MainActivity.this, "Гадиян",Toast.LENGTH_SHORT).show();
    }
    */
    //Для задания 2
   /*
   public void OnSecondButtonClick(View v) {
        Button button = findViewById(R.id.b2);
        button.setText("Гадиян");
    }
    */
    //Для задания 3 и 4
    public void OnButton1Click(View v){
        Button button = (Button) v;
        button.setText("Гадиян");
    }

    public void OnButton2Click(View v) {
        Button button = (Button) v;
        button.setText("Гадиян");
    }

    public void OnButton3Click(View v) {
        Button button = (Button) v;
        button.setText("Гадиян");
    }
}