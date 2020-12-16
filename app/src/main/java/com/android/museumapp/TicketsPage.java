package com.android.museumapp;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TicketsPage extends AppCompatActivity {





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tickets_page);

        DatabaseHelper myDb;
        Button addButton1;

        myDb = new DatabaseHelper(this);

        Spinner spinExcursionType = findViewById(R.id.spinExcursionType);
        Spinner spinExcursionDate = findViewById(R.id.spinExcursionDate);
        Spinner spinExcursionGuide = findViewById(R.id.spinExcursionGuide);
        Spinner spinPersonNum = findViewById(R.id.spinPersonNum);
        Button addButton1 = findViewById(R.id.addButton1);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,R.array.spinExcursionType, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinExcursionType.setAdapter(adapter);
        ExcType = spinExcursionDate.getSelectedItem().toString();

        ArrayAdapter adapter1 = ArrayAdapter.createFromResource(this,R.array.spinExcursionDate, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinExcursionDate.setAdapter(adapter1);
        ExcDate = spinExcursionDate.getSelectedItem().toString();

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.spinExcursionGuide, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinExcursionGuide.setAdapter(adapter2);
        Guide = spinExcursionGuide.getSelectedItem().toString();

        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,R.array.spinPersonNum, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinPersonNum.setAdapter(adapter3);
        PNum = spinPersonNum.getSelectedItem().toString();


    }


    public void AddData() {
        addButton1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        boolean isInserted = myDb.insertData();
                        if (isInserted = true)
                            Toast.makeText(TicketsPage.this, "Data Inserted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(TicketsPage.this, "Data Inserted",Toast.LENGTH_LONG).show();

                    }
                }
        );

    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }
}