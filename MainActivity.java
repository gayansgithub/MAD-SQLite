package com.example.sqlitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText taskEditText;
    private Button addButton;
    private ListView taskListView;
    private DBHelper dbHelper;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> taskList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskEditText = findViewById(R.id.taskEditText);
        addButton = findViewById(R.id.addButton);
        taskListView = findViewById(R.id.taskListView);

        dbHelper = new DBHelper(this);
        taskList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, taskList);
        taskListView.setAdapter(adapter);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTask(taskEditText.getText().toString());

            }
        });
        loadTasks();
    }

        private void addTask(String task) {
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(DBHelper.COLUMN_TASK, task);
            db.insert(DBHelper.TABLE_TODO, null, values);
            taskEditText.getText().clear();
            loadTasks();
            db.close();

    }

    private  void loadTasks() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        taskList.clear();
        Cursor cursor = db.query(DBHelper.TABLE_TODO, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                String task = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_TASK));
                taskList.add(task);
            } while (cursor.moveToNext());

            cursor.close();
            db.close();
            adapter.notifyDataSetChanged();

        }
    }
}
