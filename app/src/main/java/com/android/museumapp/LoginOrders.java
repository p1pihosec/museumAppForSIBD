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
import java.util.EventListener;
import android.widget.Toast;

import java.util.Objects;

public class LoginOrders extends AppCompatActivity {

    private Button button;
    private EditText editTextName;
    private EditText editTextPhone;
    private static String name;
    private static String phone;
    private Cursor userCursor;

    DatabaseHelper databaseHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_orders);

        button = findViewById(R.id.button);

        editTextName = findViewById(R.id.nameInput);
        editTextPhone =  findViewById(R.id.numberInput);


        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                name = editTextName.getText().toString();
                phone = editTextPhone.getText().toString();

                databaseHelper = new DatabaseHelper(getApplicationContext());
                db = databaseHelper.getWritableDatabase();
                userCursor =  db.rawQuery("select * from "+ DatabaseHelper.TABLE1, null);
                String[] names = new String[] {DatabaseHelper.CUSTOMER_NAME};

                userCursor =  db.rawQuery("select * from "+ DatabaseHelper.TABLE1, null);
                String[] phones = new String[] {DatabaseHelper.PHONE_NUMBER};


                for(int i=0; i<names.length && i<phones.length; i++){
                    if (phones[i] == phone && names[i] == name) {
                        Intent intent = new Intent(LoginOrders.this, ImportOrders.class);
                        startActivity(intent);
                    }else if(phones[i] != phone && names[i] != name){
                        Toast.makeText(getApplicationContext(), "Пользователь с такими данными отсутсвует" + names[i] + phones[i], Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        Toolbar toolbar = findViewById(R.id.login_toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

}
