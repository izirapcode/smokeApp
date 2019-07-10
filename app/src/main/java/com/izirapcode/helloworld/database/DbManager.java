package com.izirapcode.helloworld.database;

import android.app.DownloadManager;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DbManager extends SQLiteOpenHelper {

    final private int day;
    final private int  month;
    final private int year;

    public DbManager(Context context){
        super(context,"smoke.db",null,1);
        Calendar cal = Calendar.getInstance();
        this.day = cal.get(Calendar.DAY_OF_MONTH);
        this.month = cal.get(Calendar.MONTH);
        this.year = cal.get(Calendar.YEAR);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table smoke(id integer primary key autoincrement," +
                "month integer,day integer);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addSmoke(){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("month", month);
        values.put("day", day);
        db.insertOrThrow("smoke",null,values);
    }

    public int getSmokeCount(){
        String sql = "SELECT id FROM smoke";
        return  getQueryCount(sql,null);
    }

    public int getTodayCount(){
        String[] args = {String.valueOf(day),String.valueOf(month)};
        String sql = "SELECT id FROM smoke WHERE day = ? AND month=?";
        return getQueryCount(sql,args);
    }

    public int getMonthCount(){
        String[] args = new String[] {String.valueOf(month)};
        String sql = "SELECT id FROM smoke WHERE month = ?";
        return getQueryCount(sql,args);
    }

    public void clearSmoke(){
        SQLiteDatabase db = getWritableDatabase();
        db.delete("smoke",null,null);
    }

    public int getDayCount(int day, int month){
        String sql = "SELECT id FROM smoke WHERE day = ? AND month=?";
        String[] args = {String.valueOf(day),String.valueOf(month)};
        return getQueryCount(sql, args);
    }

    public Date getFirstSmokeDate(){
        String sql = "SELECT ( day || '/' || (month+1) ) as date FROM smoke ORDER BY month,day LIMIT 1";
        return getQueryDate(sql);
    }

    private int getQueryCount(String sql, String[] args){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,args);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    private Date getQueryDate(String sql){
        Date date = null;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        cursor.moveToNext();
        String dayMonth= cursor.getString(cursor.getColumnIndex("date"));
        try {
            date=new SimpleDateFormat("dd/MM/yyyy").parse(dayMonth +'/'+year);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        cursor.close();
        return date;
    }
}
