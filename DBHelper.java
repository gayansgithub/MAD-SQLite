package com.example.sqlitedemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "TodoDB";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_TODO = "todos";
    private static final String COLUMN_ID = "_id";
    public static final String COLUMN_TASK = "task";

    private static final String CREATE_TABLE_TODO = "CREATE TABLE " + TABLE_TODO +
            "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_TASK + " TEXT);";

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_TODO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldv, int newv) {

    }
}
