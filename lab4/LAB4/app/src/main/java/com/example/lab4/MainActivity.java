package com.example.lab4;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    SQLHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new SQLHelper(this);

        LinearLayout ll = findViewById(R.id.ll);

        db.insertPerson("Иван", 30);
        db.insertPerson("Мария", 25);
        db.insertPerson("Гадиян", 23);

        ArrayList<Person> persons = getList();

        for (Person p : persons) {
            LinearLayout row = new LinearLayout(this);
            row.setOrientation(LinearLayout.HORIZONTAL);

            TextView id = new TextView(this);
            id.setText(String.valueOf(p._id));
            id.setWidth(120);

            TextView name = new TextView(this);
            name.setText(p.name);
            name.setWidth(120);

            TextView age = new TextView(this);
            age.setText(String.valueOf(p.Age));
            age.setWidth(120);

            row.addView(id);
            row.addView(name);
            row.addView(age);

            ll.addView(row);
        }

        Cursor cursorOlderThan = db.getPersonsOlderThan(26);
        while (cursorOlderThan.moveToNext()) {

        }
        cursorOlderThan.close();

        Cursor averageAgeCursor = db.getAverageAge();
        if (averageAgeCursor.moveToFirst()) {
            double averageAge = averageAgeCursor.getDouble(0);
        }
        averageAgeCursor.close();
    }
    public ArrayList<Person> getList() {
        ArrayList<Person> persons = new ArrayList<>();
        Cursor cursor = db.getFullTable();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                persons.add(new Person(cursor.getInt(0), cursor.getString(1), cursor.getInt(2)));
            }
            cursor.close();
        }
        return persons;
    }
}