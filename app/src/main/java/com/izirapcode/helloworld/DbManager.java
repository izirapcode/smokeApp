package com.izirapcode.helloworld;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbManager extends SQLiteOpenHelper {

    DbManager(Context context){
        super(context,"smoke.db",null,1);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table smoke(id integer primary key autoincrement,month integer,day integer);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addSmoke(int month,int day){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("month", month);
        values.put("day", day);
        db.insertOrThrow("smoke",null,values);
    }

    public int getSmokeCount(){
        SQLiteDatabase db = getReadableDatabase();
        String[] colums = {"id"};
        return db.query("smoke",colums,null,null,null,null,null).getCount();
    }

    public int getTodayCount(int day,int month){
        SQLiteDatabase db = getReadableDatabase();
        String[] args = {String.valueOf(day),String.valueOf(month)};
        return db.rawQuery("SELECT id FROM smoke WHERE day = ? AND month=?",args).getCount();
    }

    public int getMonthCount(int month){
        SQLiteDatabase db = getReadableDatabase();
        String[] colums = {"id"};
        return db.rawQuery("SELECT id FROM smoke WHERE month = ?",new String[] {String.valueOf(month)}).getCount();
    }

    public void clearSmoke(){
        SQLiteDatabase db = getWritableDatabase();
        db.delete("smoke",null,null);
    }
}
