/*
package com.example.eugene.sqliteapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.museumapp.DatabaseHelper;
import com.android.museumapp.R;

import java.util.Objects;

public class UserActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    SQLiteDatabase db;
    Button addButton1;
    String excTypeArray[] = {"Обзорная", "Расширенная"};
    String excDateArray[] = {"21.12 10:00", "21.12 10:30", "21.12 11:00", "21.12 11:30", "21.12 12:00", "21.12 12:30","21.12 13:00",
            "21.12 13:30","21.12 14:00", "21.12 14:30","21.12 15:00", "21.12 15:30","21.12 16:00", "21.12 16:30","21.12 17:00", "21.12 17:30",
            "21.12 18:00", "21.12 18:30","21.12 19:00", "21.12 19:30","21.12 20:00", "21.12 20:30","21.12 21:00", "21.12 21:30"};
    String excGuideArray[] = {"Савченко А.Д.", "Бышовец А.Н.", "Гончаренко В.М.", "Блохин О.Г.", "Овчинников С.И."};
    String personNumArray[] = {"1 человек", "2 человека", "3 человека", "4 человека", "5 человек",
            "6 человек","7 человек", "8 человек", "9 человек", "10 человек",
            "11 человек", "12 человек", "13 человек", "14 человек","15 человек",
            "16 человек", "17 человек", "18 человек", "19 человек", "20 человек"};
    String excType, excDate, excGuide, personNum;
    EditText nameInput, numberInput;
    Spinner spinExcursionType, spinExcursionDate, spinExcursionGuide, spinPersonNum;
    Toolbar order_toolbar;
    Button delButton;
    Button saveButton;

    Cursor userCursor;
    long userId=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
*/
/*
        spinExcursionType = findViewById(R.id.spinExcursionType);
        spinExcursionDate = findViewById(R.id.spinExcursionDate);
        spinExcursionGuide = findViewById(R.id.spinExcursionGuide);
        spinPersonNum = findViewById(R.id.spinPersonNum);
        Button addButton1 = findViewById(R.id.addButton1);
        order_toolbar = findViewById(R.id.order_toolbar);
        setSupportActionBar(order_toolbar);

        databaseHelper = new DatabaseHelper(this);
        db = databaseHelper.getWritableDatabase();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        // Создаем адаптер ArrayAdapter с помощью массива строк и стандартной разметки элемета spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, excTypeArray);
        // Определяем разметку для использования при выборе элемента
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Применяем адаптер к элементу spinner
        spinExcursionType.setAdapter(adapter);

        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                // Получаем выбранный объект
                excType = (String)parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
        spinExcursionType.setOnItemSelectedListener(itemSelectedListener);


        // Создаем адаптер ArrayAdapter с помощью массива строк и стандартной разметки элемета spinner
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, excDateArray);
        // Определяем разметку для использования при выборе элемента
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Применяем адаптер к элементу spinner
        spinExcursionDate.setAdapter(adapter1);

        AdapterView.OnItemSelectedListener itemSelectedListener1 = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                // Получаем выбранный объект
                excDate = (String)parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
        spinExcursionDate.setOnItemSelectedListener(itemSelectedListener1);

        // Создаем адаптер ArrayAdapter с помощью массива строк и стандартной разметки элемета spinner
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, excGuideArray);
        // Определяем разметку для использования при выборе элемента
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Применяем адаптер к элементу adapter2
        spinExcursionGuide.setAdapter(adapter2);

        AdapterView.OnItemSelectedListener itemSelectedListener2 = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                // Получаем выбранный объект
                excGuide = (String)parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
        spinExcursionGuide.setOnItemSelectedListener(itemSelectedListener2);

        // Создаем адаптер ArrayAdapter с помощью массива строк и стандартной разметки элемета spinner
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, personNumArray);
        // Определяем разметку для использования при выборе элемента
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Применяем адаптер к элементу spinner
        spinPersonNum.setAdapter(adapter3);

        AdapterView.OnItemSelectedListener itemSelectedListener3 = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                // Получаем выбранный объект
                personNum = (String)parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
        spinPersonNum.setOnItemSelectedListener(itemSelectedListener3);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            userId = extras.getLong("id");
        }
        // если 0, то добавление
        if (userId > 0) {
            // получаем элемент по id из бд
            userCursor = db.rawQuery("select * from " + DatabaseHelper.TABLE1 + " where " +
                    DatabaseHelper.COLUMN_ID + "=?", new String[]{String.valueOf(userId)});
            userCursor.moveToFirst();
            nameBox.setText(userCursor.getString(1));
            yearBox.setText(String.valueOf(userCursor.getInt(2)));
            userCursor.close();
        } else {
            // скрываем кнопку удаления
            delButton.setVisibility(View.GONE);
        }
    }

    public void save(View view){
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.COLUMN_NAME, nameBox.getText().toString());
        cv.put(DatabaseHelper.COLUMN_YEAR, Integer.parseInt(yearBox.getText().toString()));

        if (userId > 0) {
            db.update(DatabaseHelper.TABLE, cv, DatabaseHelper.COLUMN_ID + "=" + String.valueOf(userId), null);
        } else {
            db.insert(DatabaseHelper.TABLE, null, cv);
        }
    }*//*

*/
/*    public void delete(View view){
        db.delete(DatabaseHelper.TABLE, "_id = ?", new String[]{String.valueOf(userId)});
    }*//*


}*/
