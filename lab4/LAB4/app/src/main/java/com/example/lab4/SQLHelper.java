package com.example.lab4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLHelper extends SQLiteOpenHelper {

    public SQLHelper(Context context) {
        super(context, "TrainBase", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table table1 (_id integer primary key autoincrement, name text not null, Age integer not null);");

        // Пример добавления начальных данных
        ContentValues cv = new ContentValues();
        cv.put("name", "Петр");
        cv.put("Age", 22);
        db.insert("table1", null, cv);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS table1");
        onCreate(db);
    }

    public Cursor getFullTable() {
        SQLiteDatabase db = this.getReadableDatabase(); // getReadableDatabase() для SELECT запросов
        return db.query("table1", new String[]{"_id", "name", "Age"}, null, null, null, null, null);
    }


    public Cursor getPersonsOlderThan(int k) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query("table1", new String[]{"_id", "name", "Age"}, "Age > ?", new String[]{String.valueOf(k)}, null, null, "Age");
    }

    public Cursor getAverageAge() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query("table1", new String[]{"AVG(Age) AS averageAge"}, null, null, null, null, null);
    }


    public long insertPerson(String name, int age) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("Age", age);
        return db.insert("table1", null, cv);
    }

    public int updatePersonAge(String name, int newAge) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("Age", newAge);
        return db.update("table1", cv, "name = ?", new String[]{name});
    }
}