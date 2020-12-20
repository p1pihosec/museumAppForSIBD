package com.android.museumapp;

        import androidx.appcompat.app.AppCompatActivity;
        import androidx.appcompat.widget.Toolbar;

        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.os.Bundle;
        import android.widget.ListView;
        import android.widget.SimpleCursorAdapter;
        import android.widget.TextView;

        import java.io.Serializable;
        import java.util.Objects;

public class ImportOrders extends AppCompatActivity {
    TextView userName;
    ListView userList;
    TextView header;
    DatabaseHelper databaseHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    SimpleCursorAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_import_orders);

        header = (TextView)findViewById(R.id.header);
        userList = (ListView)findViewById(R.id.list);

        databaseHelper = new DatabaseHelper(getApplicationContext());
    }
    @Override
    public void onResume() {
        super.onResume();
        // открываем подключение
        db = databaseHelper.getReadableDatabase();

        //получаем данные из бд в виде курсора
        userCursor =  db.rawQuery("select * from "+ DatabaseHelper.TABLE1, null);
        // определяем, какие столбцы из курсора будут выводиться в ListView
        userCursor.moveToFirst();

        String[] headers = new String[] {DatabaseHelper.EXCURSION_DATE, DatabaseHelper.GUIDE_NAME,DatabaseHelper.PERSON_NUM, DatabaseHelper.CUSTOMER_NAME, DatabaseHelper.PHONE_NUMBER};
        // создаем адаптер, передаем в него курсор
        userAdapter = new SimpleCursorAdapter(this, android.R.layout.two_line_list_item,
                userCursor, headers, new int[]{android.R.id.text1, android.R.id.text2}, 0);
        header.setText("Найдено заказов: " + String.valueOf(userCursor.getCount()));
        userList.setAdapter(userAdapter);


        Toolbar toolbar = findViewById(R.id.login_toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        // Закрываем подключение и курсор
        db.close();
        userCursor.close();
    }
}
