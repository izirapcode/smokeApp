package com.izirapcode.helloworld.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.izirapcode.helloworld.calendar.CalendarAdapter;
import com.izirapcode.helloworld.database.DbManager;
import com.izirapcode.helloworld.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


import static com.izirapcode.helloworld.database.DataManager.getLastCig;

public class SmokeCalendar extends BasicActivity {

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
        initFields();
        addToolbar();
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

    private void initFields(){
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
        long dayLength = 24*60*60*1000;
        long hourLength = 60*60*1000;
        long minLength = 60*1000;
        long diff = now.getTime() - date.getTime();
        int diffDays = (int)Math.floor(diff / dayLength);
        int diffHours = (int) Math.floor((diff -(diffDays * dayLength)) / (hourLength));
        int diffMin = (int)(((diff - (diffHours*hourLength)) / minLength));
        return "Ostatni papieros \nDni:"+diffDays+"\nGodzin:"+diffHours+"\nMinut:"+diffMin;

    }
}
