package com.izirapcode.helloworld.activities;

import com.izirapcode.helloworld.R;
import com.izirapcode.helloworld.database.DbManager;

import android.os.Bundle;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static com.izirapcode.helloworld.database.DataManager.getLastCig;
import static com.izirapcode.helloworld.database.DataManager.getSum;

public class StatsActivity extends BasicActivity {

    TextView counterText, sumText, dailyText,lastCigTime;
    DbManager db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        initFields();
        addToolbar();
        setAvg();
        showCounter();
        showSum();
        getLastSmokeTime();
    }

    private void initFields() {
        db = new DbManager(this);
        dailyText = findViewById(R.id.dailyText);
        sumText = findViewById(R.id.wydanePieniadze);
        counterText = findViewById(R.id.licznik);
        lastCigTime = findViewById(R.id.lastCigView);
    }

    private void setAvg(){
        Date today = new Date();
        Date firstSmokeDate = db.getFirstSmokeDate();
        long diff = today.getTime() - firstSmokeDate.getTime();
        long daysDiff = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        int allCount = db.getSmokeCount();
        float dailyAvg = (float)allCount/daysDiff;
        dailyText.setText(getString(R.string.stats_per_day, dailyAvg));
    }

    private void showCounter() {
        int todayCount = db.getTodayCount();
        int monthCount =  db.getMonthCount();
        int allCount = db.getSmokeCount();
        counterText.setText(getString(R.string.CounterLabel, todayCount, monthCount, allCount));
    }

    private void showSum() {
        Float spendSum = getSum(this);
        sumText.setText(getString(R.string.SumLabel, spendSum));
    }


    private void getLastSmokeTime(){
        SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date(), now = new Date();
        try {
            date =formatter.parse(getLastCig(StatsActivity.this));
        } catch (ParseException e) {

        }
        long dayLength = 24*60*60*1000;
        long hourLength = 60*60*1000;
        long minLength = 60*1000;
        long diff = now.getTime() - date.getTime();
        int diffDays = (int)Math.floor(diff / dayLength);
        int diffHours = (int) Math.floor((diff -(diffDays * dayLength)) / (hourLength));
        int diffMin = (int)(((diff - (diffHours*hourLength)) / minLength));
        lastCigTime.setText(getString(R.string.stats_last_cig,diffDays,diffHours,diffMin));
    }

}
