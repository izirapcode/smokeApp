package com.izirapcode.helloworld;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.CalendarView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.Calendar;

import butterknife.BindView;

public class SmokeCalendar extends Activity {


    GridView calendar;
    Calendar cal;
    DbManager db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smoke_calendar);
        calendar = findViewById(R.id.calendar_grid);
        cal = Calendar.getInstance();
        db = new DbManager(this);
        CalendarAdapter calendarAdapter = new CalendarAdapter(this,cal,db);
        calendar.setAdapter(calendarAdapter);
    }
}
