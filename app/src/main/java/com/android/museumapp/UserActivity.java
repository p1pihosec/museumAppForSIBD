package com.android.museumapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Objects;

public class UserActivity extends AppCompatActivity {

    EditText excType;
    EditText excPersonNum;
    Button delButton;
    Button saveButton;
    DatabaseHelper sqlHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    Toolbar user_toolbar;

    long Id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        excType = (EditText) findViewById(R.id.excType);
        excPersonNum = (EditText) findViewById(R.id.excPersonNum);
        delButton = (Button) findViewById(R.id.deleteButton);
        saveButton = (Button) findViewById(R.id.saveButton);

        sqlHelper = new DatabaseHelper(this);
        db = sqlHelper.getWritableDatabase();

        Toolbar toolbar = findViewById(R.id.user_toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Id = extras.getLong("id");
        }
        // если 0, то добавление
        if (Id > 0) {
            // получаем элемент по id из бд
            userCursor = db.rawQuery("select * from " + DatabaseHelper.TABLE1 + " where " +
                    DatabaseHelper.COLUMN_ID + "=?", new String[]{String.valueOf(Id)});
            userCursor.moveToFirst();
            excType.setText(userCursor.getString(2));
            excPersonNum.setText(userCursor.getString(4));
            userCursor.close();
        } else {
            // скрываем кнопку удаления
            delButton.setVisibility(View.GONE);
        }
    }

    public void save(View view) {
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.EXCURSION_DATE, excType.getText().toString());
        cv.put(DatabaseHelper.PERSON_NUM, excPersonNum.getText().toString());

        if (Id > 0) {
            db.update(DatabaseHelper.TABLE1, cv, DatabaseHelper.COLUMN_ID + "=" + String.valueOf(Id), null);
        } else {
            db.insert(DatabaseHelper.TABLE1, null, cv);
        }
        goHome();
    }

    public void delete(View view) {
        db.delete(DatabaseHelper.TABLE1, "_id = ?", new String[]{String.valueOf(Id)});
        goHome();
    }

    private void goHome() {
        // закрываем подключение
        db.close();
        // переход к главной activity
        Intent intent = new Intent(this, ImportOrders.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);


    }
}