package com.example.lab2;

import android.os.Bundle;
import android.widget.Button;
import android.widget.GridLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        setTitle("My Title");
       /* GridLayout gr = (GridLayout)findViewById(R.id.grid);
        Button b1 = new Button(this);
        Button b2 = new Button(this);
        Button b3 = new Button(this);
        Button b4 = new Button(this);
        Button b5 = new Button(this);
        b1.setText("1");
        b1.setText("2");
        b1.setText("3");
        b1.setText("4");
        b1.setText("5");
        gr.addView(b1);
        gr.addView(b2);
        gr.addView(b3);
        gr.addView(b4);
        gr.addView(b5);
        gr.setRowCount(3);
        gr.setColumnCount(3);*/
    }
}