package com.example.todo;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class TODO_new extends AppCompatActivity {

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t_o_d_o_new);
    }

    public void insertData(View view) {
        editText = (EditText)findViewById(R.id.newTODO);
        String content = editText.getText().toString();

        if(content.length() == 0){
            Intent data = new Intent();
            setResult(RESULT_CANCELED, data);
            finish();
        }else{
            Intent data = new Intent();
            data.putExtra("content", content);
            setResult(RESULT_OK, data);
            finish();
        }
    }

    OnBackPressedCallback callback = new OnBackPressedCallback(true) {
        @Override
        public void handleOnBackPressed() {
            Intent data = new Intent();
            setResult(RESULT_CANCELED, data);
            finish();
        }
    };
}