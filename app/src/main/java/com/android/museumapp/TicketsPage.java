package com.android.museumapp;

import android.content.ContentValues;
import android.content.Intent;
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

import java.util.Objects;

public class TicketsPage extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    SQLiteDatabase db;
    Button addButton1;
    String excTypeArray[] = {"Обзорная", "Расширенная"};
    String excDateArray[] = {"21.12 10:00", "21.12 10:30", "21.12 11:00", "21.12 11:30", "21.12 12:00", "21.12 12:30", "21.12 13:00",
            "21.12 13:30", "21.12 14:00", "21.12 14:30", "21.12 15:00", "21.12 15:30", "21.12 16:00", "21.12 16:30", "21.12 17:00", "21.12 17:30",
            "21.12 18:00", "21.12 18:30", "21.12 19:00", "21.12 19:30", "21.12 20:00", "21.12 20:30", "21.12 21:00", "21.12 21:30"};
    String excGuideArray[] = {"Савченко А.Д.", "Бышовец А.Н.", "Гончаренко В.М.", "Блохин О.Г.", "Овчинников С.И."};
    String personNumArray[] = {"1 человек", "2 человека", "3 человека", "4 человека", "5 человек",
            "6 человек", "7 человек", "8 человек", "9 человек", "10 человек",
            "11 человек", "12 человек", "13 человек", "14 человек", "15 человек",
            "16 человек", "17 человек", "18 человек", "19 человек", "20 человек"};
    String excType, excDate, excGuide, personNum, name, numberInputRes;
    EditText nameInput, numberInput;
    Spinner spinExcursionType, spinExcursionDate, spinExcursionGuide, spinPersonNum;
    Toolbar order_toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tickets_page);
        databaseHelper = new DatabaseHelper(this);
        db = databaseHelper.getReadableDatabase();

        spinExcursionType = findViewById(R.id.spinExcursionType);
        spinExcursionDate = findViewById(R.id.spinExcursionDate);
        spinExcursionGuide = findViewById(R.id.spinExcursionGuide);
        spinPersonNum = findViewById(R.id.spinPersonNum);
        Button addButton1 = findViewById(R.id.addButton1);
        nameInput = findViewById(R.id.nameInput);
        numberInput = findViewById(R.id.numberInput);
        order_toolbar = findViewById(R.id.order_toolbar);
        setSupportActionBar(order_toolbar);

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
                excType = (String) parent.getItemAtPosition(position);
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
                excDate = (String) parent.getItemAtPosition(position);
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
                excGuide = (String) parent.getItemAtPosition(position);
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
                personNum = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
        spinPersonNum.setOnItemSelectedListener(itemSelectedListener3);
        addButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = nameInput.getText().toString();
                numberInputRes = numberInput.getText().toString();

                ContentValues newValues = new ContentValues();
                newValues.put("excursion_name", excType);
                newValues.put("excursion_date", excDate);
                newValues.put("guide", excGuide);
                newValues.put("person_num", personNum);
                newValues.put("customer_name", name);
                newValues.put("phone_num", numberInputRes);

                db.insert("orders", null, newValues);
                startActivity();
            }
        });
    }

    public void startActivity() {

        Intent intent = new Intent(this, OrderProcessed.class);
        startActivity(intent);

    }

}