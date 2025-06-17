package com.example.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
       // setContentView(R.layout.activity_main);
        setContentView(new DrawView(this));
        setTitle("Тестовое задание");
    }
    class DrawView extends View {
        Paint p;
        Rect rect;

        public DrawView(Context context) {
            super(context);
            p = new Paint();
            rect = new Rect();
        }

        protected void onDraw(Canvas canvas){

            // super.onDraw(canvas);
            canvas.drawARGB(80, 102, 104, 255);
            // получаем текущие размеры View
            int width = getWidth();
            int height = getHeight();

            // создаем RectF, охватывающий весь экран
            RectF ovalRect = new RectF(0, 0, width, height);

            // настраиваем Paint для овала:
            p.setColor(Color.argb(128, 0, 255, 0)); // 128 - 50% прозрачность
            p.setStyle(Paint.Style.FILL); // Заливка овалом

            // Рисуем овал
            canvas.drawOval(ovalRect, p);

            // отрисовка круга и прямоугольника
            /* canvas.drawARGB(80, 102, 104, 255);
            p.setColor(Color.RED);
            canvas.drawRect(200, 105, 400, 200, p);
            canvas.drawCircle(100, 200, 50, p);
            p.setStrokeWidth(10);
            float[] points = new float[]{300, 200, 400, 200};
            canvas.drawLines(points,p); */

            // Первый пример кривой
            /* Path path = new Path();
            path.addArc(new RectF(0, 0, 100, 100), 0, 180);;
            canvas.drawPath(path, p); */
            // Код треугольника
           /* Path path2 = new Path();
            path2.moveTo(50, 50);
            path2.lineTo(0,100);
            path2.lineTo(100, 100);
            path2.lineTo(50,50);
            canvas.drawPath(path2,p);
            */
            // Второй пример кривой
           /* Path path3 = new Path();
            path3.addArc(new RectF(0,0,100,100),90, 180);*/

        }
    }
}