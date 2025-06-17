package com.example.lab10;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.TimePickerDialog;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    Calendar dateAndTime = Calendar.getInstance();
    Timer timer;
    int TaskId = 1;
    EditText startTime;
    NotificationChannel channel;
    ArrayList<ToDoTask> tasks = new ArrayList<>();
    ArrayAdapter<String> taskList;
    WifiManager wifiManager;


    Calendar wifiOnCalendar = Calendar.getInstance();
    Calendar wifiOffCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        startTime = findViewById(R.id.toDoTime);
        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);


        taskList = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new ArrayList<String>());
        ListView tasksListView = findViewById(R.id.tasksList);
        tasksListView.setAdapter(taskList);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "MainChannel";
            String description = "Channel for ToDo Tasks";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            channel = new NotificationChannel("ch-1", name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }


        startTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        dateAndTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        dateAndTime.set(Calendar.MINUTE, minute);
                        changeStartTime();
                    }
                };
                TimePickerDialog dlg = new TimePickerDialog(MainActivity.this, t,
                        dateAndTime.get(Calendar.HOUR_OF_DAY),
                        dateAndTime.get(Calendar.MINUTE), true);
                dlg.show();
            }
        });


        EditText wifiOnTime = findViewById(R.id.wifiOnTime);
        wifiOnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar current = Calendar.getInstance();
                TimePickerDialog timePicker = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                        wifiOnCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        wifiOnCalendar.set(Calendar.MINUTE, minute);
                        wifiOnTime.setText(String.format("%02d:%02d", hourOfDay, minute));
                        Log.d("WiFi On Time", "Setter: " + hourOfDay + ":" + minute);
                    }
                }, current.get(Calendar.HOUR_OF_DAY), current.get(Calendar.MINUTE), true);
                timePicker.show();
            }
        });


        EditText wifiOffTime = findViewById(R.id.wifiOffTime);
        wifiOffTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar current = Calendar.getInstance();
                TimePickerDialog timePicker = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                        wifiOffCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        wifiOffCalendar.set(Calendar.MINUTE, minute);
                        wifiOffTime.setText(String.format("%02d:%02d", hourOfDay, minute));
                        Log.d("WiFi Off Time", "Setter: " + hourOfDay + ":" + minute);
                    }
                }, current.get(Calendar.HOUR_OF_DAY), current.get(Calendar.MINUTE), true);
                timePicker.show();
            }
        });


        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    ArrayList<ToDoTask> tasksToRemove = new ArrayList<>();
                    Date currentTime = Calendar.getInstance().getTime();
                    for (ToDoTask t : tasks) {
                        if (currentTime.after(t.ToDoTime)) {
                            t.getNotification();
                            tasksToRemove.add(t);
                        }
                    }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tasks.removeAll(tasksToRemove);
                            updateTaskList();
                        }
                    });
                } catch (Exception e) {
                    Log.e("Timer Error", e.getMessage());
                }
            }
        }, 15000, 15000);
    }

    public void updateTaskClick(View v) {
        updateTaskList();
    }

    protected void updateTaskList() {
        try {
            ArrayList<String> tasksTitles = new ArrayList<>();
            for (ToDoTask t : tasks) {
                tasksTitles.add(t.Title + " - " + t.ToDoTime.toString());
            }
            taskList.clear();
            taskList.addAll(tasksTitles);
            taskList.notifyDataSetChanged();
        } catch (Exception e) {
            Log.e("ListView Error", e.getMessage());
        }
    }

    protected void changeStartTime() {
        startTime.setText(DateUtils.formatDateTime(this,
                dateAndTime.getTimeInMillis(),
                DateUtils.FORMAT_ABBREV_TIME | DateUtils.FORMAT_SHOW_TIME));
    }

    public void addTaskClick(View v) {
        try {
            String title = ((EditText) findViewById(R.id.toDoTitle)).getText().toString();
            String description = ((EditText) findViewById(R.id.toDoDescription)).getText().toString();
            String toDoTimeOnString = ((EditText) findViewById(R.id.toDoTime)).getText().toString();

            if (title.isEmpty() || description.isEmpty() || toDoTimeOnString.isEmpty()) {
                Toast.makeText(MainActivity.this, "Пожалуйста, заполните все поля!", Toast.LENGTH_SHORT).show();
                return;
            }

            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
            Date toDoTime = formatter.parse(toDoTimeOnString);


            Calendar taskCalendar = Calendar.getInstance();
            taskCalendar.set(Calendar.HOUR_OF_DAY, dateAndTime.get(Calendar.HOUR_OF_DAY));
            taskCalendar.set(Calendar.MINUTE, dateAndTime.get(Calendar.MINUTE));
            taskCalendar.set(Calendar.SECOND, 0);
            taskCalendar.set(Calendar.MILLISECOND, 0);
            Date currentTime = new Date();
            if (taskCalendar.getTime().before(currentTime)) {
                taskCalendar.add(Calendar.DATE, 1);
            }

            ToDoTask newTask = new ToDoTask(TaskId++);
            newTask.Title = title;
            newTask.Description = description;
            newTask.ToDoTime = taskCalendar.getTime();
            tasks.add(newTask);


            ((EditText) findViewById(R.id.toDoTime)).setText("");
            ((EditText) findViewById(R.id.toDoTitle)).setText("");
            ((EditText) findViewById(R.id.toDoDescription)).setText("");

            Toast.makeText(MainActivity.this, "Задача добавлена!", Toast.LENGTH_SHORT).show();
            updateTaskList();
        } catch (Exception e) {
            Log.e("Add Task Error", e.getMessage());
            Toast.makeText(MainActivity.this, "Ошибка при добавлении задачи!", Toast.LENGTH_SHORT).show();
        }
    }

    public void setWifiSchedule(View v) {
        try {
            String wifiOnTimeString = ((EditText) findViewById(R.id.wifiOnTime)).getText().toString();
            String wifiOffTimeString = ((EditText) findViewById(R.id.wifiOffTime)).getText().toString();

            if (wifiOnTimeString.isEmpty() || wifiOffTimeString.isEmpty()) {
                Toast.makeText(MainActivity.this, "Пожалуйста, установите оба времени Wi-Fi!", Toast.LENGTH_SHORT).show();
                return;
            }

            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
            Date wifiOnTimeDate = formatter.parse(wifiOnTimeString);
            Date wifiOffTimeDate = formatter.parse(wifiOffTimeString);

            Calendar current = Calendar.getInstance();


            Calendar wifiOnCalendar = Calendar.getInstance();
            wifiOnCalendar.set(Calendar.HOUR_OF_DAY, wifiOnTimeDate.getHours());
            wifiOnCalendar.set(Calendar.MINUTE, wifiOnTimeDate.getMinutes());
            wifiOnCalendar.set(Calendar.SECOND, 0);
            wifiOnCalendar.set(Calendar.MILLISECOND, 0);
            if (wifiOnCalendar.getTime().before(current.getTime())) {
                wifiOnCalendar.add(Calendar.DATE, 1);
            }


            Calendar wifiOffCalendar = Calendar.getInstance();
            wifiOffCalendar.set(Calendar.HOUR_OF_DAY, wifiOffTimeDate.getHours());
            wifiOffCalendar.set(Calendar.MINUTE, wifiOffTimeDate.getMinutes());
            wifiOffCalendar.set(Calendar.SECOND, 0);
            wifiOffCalendar.set(Calendar.MILLISECOND, 0);
            if (wifiOffCalendar.getTime().before(current.getTime())) {
                wifiOffCalendar.add(Calendar.DATE, 1);
            }


            scheduleWifiTask(wifiOnCalendar.getTime(), true);


            scheduleWifiTask(wifiOffCalendar.getTime(), false);

            Toast.makeText(MainActivity.this, "Расписание Wi-Fi установлено!", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e("Wi-Fi Schedule Error", e.getMessage());
            Toast.makeText(MainActivity.this, "Ошибка при установке расписания Wi-Fi!", Toast.LENGTH_SHORT).show();
        }
    }

    private void scheduleWifiTask(Date time, final boolean enable) {
        TimerTask wifiTask = new TimerTask() {
            @Override
            public void run() {
                wifiManager.setWifiEnabled(enable);
                String status = enable ? "включен" : "выключен";
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "Wi-Fi " + status, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };

        long delay = time.getTime() - System.currentTimeMillis();
        if (delay < 0) {
            delay += 24 * 60 * 60 * 1000;
        }
        timer.schedule(wifiTask, delay);
    }

    protected class ToDoTask {
        public String Title;
        public String Description;
        public Date ToDoTime;
        protected int TaskId;

        public ToDoTask(int TaskId) {
            this.TaskId = TaskId;
        }

        public void getNotification() {
            NotificationCompat.Builder nbuilder = new NotificationCompat.Builder(getApplicationContext(), "ch-1")
                    .setContentTitle(Title)
                    .setContentText(Description)
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);

            NotificationManager mgr = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            mgr.notify(TaskId, nbuilder.build());

            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            if (v != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    v.vibrate(VibrationEffect.createOneShot(400, VibrationEffect.DEFAULT_AMPLITUDE));
                } else {
                    v.vibrate(400);
                }
            }
        }
    }
}