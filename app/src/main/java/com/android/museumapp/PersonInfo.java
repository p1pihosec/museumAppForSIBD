package com.android.museumapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

public class PersonInfo extends AppCompatActivity {

    String name;
    int numberInputRes;
    EditText nameInput;
    EditText numberInput;
    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_info);

        nameInput = findViewById(R.id.nameInput);
        numberInput = findViewById(R.id.numberInput);
        submitButton = findViewById(R.id.submitButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = nameInput.getText().toString();
                numberInputRes = Integer.parseInt(numberInput.getText().toString());

                numberInput.setText(String.format(Integer.toString(numberInputRes)+"$"));

                showToat(name);
                showToat(String.valueOf(numberInputRes));

            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(PersonInfo.this, OrderProcessed.class);
                startActivity(i);
            }
        });

        Toolbar toolbar = findViewById(R.id.order_toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

    private void showToat(String text) {
        Toast.makeText(PersonInfo.this, text, Toast.LENGTH_SHORT).show();

    }
}
