package com.izirapcode.helloworld;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


import static com.izirapcode.helloworld.DataManager.getLastCig;
import static java.lang.Math.abs;

public class SmokeCalendar extends Activity {

    GridView gridView;
    Calendar calendar;
    DbManager db;
    Button previous,next;
    TextView monthHeader,lastCigTime;
    String[] monthName = { "Styczeń", "Luty", "Marzec", "Kwiecień", "Maj", "Czerwiec", "Lipiec",
            "Sierpień", "Wrzesień", "Październik", "Listopad", "Grudzień" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smoke_calendar);
        init();
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendarSet(-1);
                gridView.invalidateViews();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendarSet(1);
                gridView.invalidateViews();
            }
        });
    }

    private void init(){
        gridView = findViewById(R.id.calendar_grid);
        calendar = Calendar.getInstance();
        db = new DbManager(this);
        CalendarAdapter calendarAdapter = new CalendarAdapter(this,calendar,db);
        gridView.setAdapter(calendarAdapter);
        previous = findViewById(R.id.previous);
        next = findViewById(R.id.next);
        monthHeader = findViewById(R.id.monthHeader);
        monthHeader.setText(monthName[calendar.get(Calendar.MONTH)]);
        lastCigTime = findViewById(R.id.lastCigView);
        lastCigTime.setText(getLastSmokeTime());

    }

    public void calendarSet(int amount){
        calendar.add(Calendar.MONTH,amount);
        calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        monthHeader.setText(monthName[calendar.get(Calendar.MONTH)]);

    }

    private String getLastSmokeTime(){
        SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date, now = new Date();
        try {
            date =formatter.parse(getLastCig(SmokeCalendar.this));
        } catch (ParseException e) {
            return "error";
        }
        long diff = now.getTime() - date.getTime();
        int diffDays = (int)Math.floor(diff / (24*60*60*1000));
        int diffHours = (int) Math.floor((diff -(diffDays *24*60*60*1000)) / (60*60*1000));
        int diffMin = (int)(((diff - (diffHours*60*60*1000)) /(60*1000)));
        return "Ostatni papieros \nDni:"+diffDays+"\nGodzin:"+diffHours+"\nMinut:"+diffMin;

    }


}
