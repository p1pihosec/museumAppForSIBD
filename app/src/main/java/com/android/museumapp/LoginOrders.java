package com.android.museumapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import android.widget.Toast;

import java.util.List;
import java.util.Objects;

public class LoginOrders extends AppCompatActivity {

    private Button button;
    private EditText editTextName;
    private EditText editTextPhone;
    public static String name;
    private static String phone;
    private Cursor tableCursor;
    DatabaseHelper databaseHelper;
    SQLiteDatabase db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_orders);

        button = findViewById(R.id.button);

        editTextName = findViewById(R.id.nameInput);
        editTextPhone =  findViewById(R.id.numberInput);


        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                name = editTextName.getText().toString();

                Intent intent1 = new Intent(LoginOrders.this, ImportOrders.class); /*Пытаюсь перенести значение в другой класс*/
                intent1.putExtra("loginName", name);
                startActivity(intent1);

                phone = editTextPhone.getText().toString();

                databaseHelper = new DatabaseHelper(getApplicationContext());
                db = databaseHelper.getWritableDatabase();
                tableCursor =  db.rawQuery("select * from "+ DatabaseHelper.TABLE1, null);

                List <String> names = new ArrayList<>();
                List <String> phones = new ArrayList<>();

                if (tableCursor.moveToFirst()){
                    do {
                        names.add(tableCursor.getString(5)); // индекс колонки с именем пользователя
                        phones.add(tableCursor.getString(6)); //индекс колонки с номером телефона
                    } while(tableCursor.moveToNext());
                }
                tableCursor.close();
                db.close();

                int i = 0;
                boolean isUserExists = false;
                for (String nameTable:names) {
                    if (phones.get(i).equals(phone) && nameTable.equals(name)) {
                        isUserExists = true;
                    }
                    i++;
                }
                if (isUserExists) {
                    Intent intent = new Intent(LoginOrders.this, ImportOrders.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Пользователь с такими данными отсутсвует", Toast.LENGTH_SHORT).show();
                }
            }
        });
        Toolbar toolbar = findViewById(R.id.login_toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

}