package com.izirapcode.helloworld.activities;

import android.os.Bundle;
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
    TextView monthHeader;
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

    }

    public void calendarSet(int amount){
        calendar.add(Calendar.MONTH,amount);
        calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        monthHeader.setText(monthName[calendar.get(Calendar.MONTH)]);
    }

}
