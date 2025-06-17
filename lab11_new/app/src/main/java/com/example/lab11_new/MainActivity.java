package com.example.lab11_new;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView1, imageView2, imageView3;
    private ProgressBar progressBar1, progressBar2, progressBar3;
    private Button buttonLoad;
    private final List<String> imageUrls = new ArrayList<>();
    private ExecutorService executorService;
    private final Handler handler = new Handler(Looper.getMainLooper());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        progressBar1 = findViewById(R.id.progressBar1);
        progressBar2 = findViewById(R.id.progressBar2);
        progressBar3 = findViewById(R.id.progressBar3);
        buttonLoad = findViewById(R.id.buttonLoad);

        imageUrls.add("https://egm.fondsmena.ru/media/projects/photo_location/history_14.png");
        imageUrls.add("https://static.tildacdn.com/tild3838-6235-4131-b837-323961623337/image.png");
        imageUrls.add("https://www.nevworker.ru/upload/iblock/6a5/6a54d8c39b482a9efbf1384c580dd710.png");

        executorService = Executors.newFixedThreadPool(3);

        buttonLoad.setOnClickListener(v -> loadImages());
    }

    private void loadImages() {
        buttonLoad.setEnabled(false);

        loadImage(imageUrls.get(0), imageView1, progressBar1);
        loadImage(imageUrls.get(1), imageView2, progressBar2);
        loadImage(imageUrls.get(2), imageView3, progressBar3);
    }


    private void loadImage(final String url, final ImageView imageView, final ProgressBar progressBar) {
        progressBar.setVisibility(View.VISIBLE);
        imageView.setImageBitmap(null);

        executorService.execute(() -> {
            try {
                InputStream inputStream = (InputStream) new URL(url).getContent();
                final Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                inputStream.close();


                handler.post(() -> {
                    imageView.setImageBitmap(bitmap);
                    progressBar.setVisibility(View.GONE);


                    if (areAllImagesLoaded()) {
                        buttonLoad.setEnabled(true);
                    }
                });


            } catch (IOException e) {
                handler.post(()-> {
                    Toast.makeText(MainActivity.this, "Ошибка загрузки: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                    if (areAllImagesLoaded()) {
                        buttonLoad.setEnabled(true);
                    }
                });

            }
        });
    }
    private boolean areAllImagesLoaded() {
        return progressBar1.getVisibility() == View.GONE &&
                progressBar2.getVisibility() == View.GONE &&
                progressBar3.getVisibility() == View.GONE;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        executorService.shutdown();
    }
}