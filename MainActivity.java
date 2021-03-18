package com.example.todo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.loader.app.LoaderManager;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    TestOpenHelper helper;
    SQLiteDatabase db;
    Cursor c;
    ListView listView;
    ListAdapter adapter;
    private static final int SUB_ACTIVITY = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), TODO_new.class);
                startActivityForResult(intent, SUB_ACTIVITY);
            }
        });

        helper = new TestOpenHelper(getApplicationContext());
        db = helper.getWritableDatabase();
        c = db.query("TODO", new String[]{"content", "_id"}, null, null, null, null, null);

        c.moveToFirst();

        adapter = new SimpleCursorAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, c, new String[]{"content", "_id"}, new int[]{android.R.id.text1}, 0);
        listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SUB_ACTIVITY) {
            if (resultCode == RESULT_OK) {
                ContentValues cv = new ContentValues();
                cv.put("content", data.getStringExtra("content"));
                db.insert("TODO", null, cv);
            }
        }
    }
}