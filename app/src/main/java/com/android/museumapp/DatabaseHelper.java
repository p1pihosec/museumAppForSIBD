package com.android.museumapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static String DB_PATH = "/data/data/com.android.museumapp/databases/";
    private static String DB_NAME = "DB1.db";
    private static final int SCHEMA = 1; // версия базы данных
    static final String TABLE1 = "orders"; // название таблицы в бд
    // названия столбцов
    public static final String COLUMN_ID = "order_id";
    public static final String CUSTOMER_ID = "customer_id";
    public static final String EXCURSION_ID = "excursion_id";
    public static final String EXCURSION_DATE = "excursion_date";
    public static final String GUIDE_NAME = "guide";
    public static final String PERSON_NUM = "person_num";
    static final String TABLE2 = "customer"; // название таблицы в бд
    public static final String CUSTOMER_NAME = "customer_name";
    public static final String PHONE_NUMBER = "phone_num";
    private Context myContext;

    DatabaseHelper(Context context) {
        super(context, DB_NAME, null, SCHEMA);
        this.myContext=context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    void create_db(){
        InputStream myInput = null;
        OutputStream myOutput = null;
        try {
            File file = new File(DB_PATH);
            if (!file.exists()) {
                this.getReadableDatabase();
                //получаем локальную бд как поток
                myInput = myContext.getAssets().open(DB_NAME);
                // Путь к новой бд
                String outFileName = DB_PATH;

                // Открываем пустую бд
                myOutput = new FileOutputStream(outFileName);

                // побайтово копируем данные
                byte[] buffer = new byte[1024];
                int length;
                while ((length = myInput.read(buffer)) > 0) {
                    myOutput.write(buffer, 0, length);
                }

                myOutput.flush();
                myOutput.close();
                myInput.close();
            }
        }
        catch(IOException ex){
            Log.d("DatabaseHelper", ex.getMessage());
        }
    }
    public SQLiteDatabase open()throws SQLException {

        return SQLiteDatabase.openDatabase(DB_PATH, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public boolean insertData(String exc_type, String exc_date, String guide, String person_num) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(EXCURSION_ID,exc_type);
        contentValues.put(EXCURSION_DATE,exc_date);
        contentValues.put(GUIDE_NAME,guide);
        contentValues.put(PERSON_NUM,person_num);

        long result1 = db.insert(TABLE1, null, contentValues);
        if (result1 == -1)
            return false;
        else
            return true;

/*    public boolean insertData(String customer_name, String phone_num) {
        ContentValues contentValues1 = new ContentValues();
        contentValues1.put(CUSTOMER_NAME,customer_name);
        contentValues1.put(PHONE_NUMBER,phone_num);

        long result2 = db.insert(TABLE2, null, contentValues1);
        if (result2 == -1)
            return false;
        else
            return true;*/
        }

}