package com.izirapcode.helloworld;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


import static com.izirapcode.helloworld.DataManager.*;

public class MainActivity extends AppCompatActivity {

    TextView counterText, sumText;
    EditText editText;
    Button  sumButton;
    ImageButton counterButton;
    DbManager db;
    Calendar cal;
    Toolbar toolbar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        final int day = cal.get(Calendar.DAY_OF_MONTH);
        final int month = cal.get(Calendar.MONTH);
        showCounter(day, month);
        showSum();
        counterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.addSmoke(month, day);
                showCounter(day, month);
                buttonFlash();
                setLastCig(getDateAndTime(),MainActivity.this);
            }
        });
        sumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setVisibility(View.VISIBLE);
                editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        if (actionId == EditorInfo.IME_ACTION_DONE) {
                        setSum(Float.parseFloat(editText.getText().toString()), MainActivity.this);
                        editText.setVisibility(View.INVISIBLE);
                        showSum();
                        }
                        return false;
                    }
                });
            }
        });



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.calendarOption:
                Intent intent = new Intent(this, SmokeCalendar.class);
                startActivity(intent);
            return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    private void showCounter(int day, int month) {
        counterText.setText(getString(R.string.CounterLabel, db.getTodayCount(day,month), db.getMonthCount(month), db.getSmokeCount()));
    }

    private void showSum() {
        sumText.setText(getString(R.string.SumLabel, getSum(this)));
    }

    private void init() {
        editText = findViewById(R.id.editText);
        editText.setVisibility(View.INVISIBLE);
        counterButton = findViewById(R.id.licznikButton);
        sumText = findViewById(R.id.wydanePieniadze);
        counterText = findViewById(R.id.licznik);
        sumButton = findViewById(R.id.paczkaButton);
        db = new DbManager(this);
        cal = Calendar.getInstance();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        setSupportActionBar(toolbar);
    }

    private void buttonFlash(){
        counterButton.setBackground(getResources().getDrawable(R.drawable.smoke2,null));
        android.view.animation.Animation in = new AlphaAnimation(0.3f,1f);
        in.setDuration(200);
        in.setAnimationListener(new android.view.animation.Animation.AnimationListener() {
            @Override
            public void onAnimationStart(android.view.animation.Animation animation) {

            }

            @Override
            public void onAnimationEnd(android.view.animation.Animation animation) {
                counterButton.setBackground(getResources().getDrawable(R.drawable.smoke,null));
            }

            @Override
            public void onAnimationRepeat(android.view.animation.Animation animation) {

            }
        });
        counterButton.startAnimation(in);
    }

    private String getDateAndTime(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return formatter.format(new Date());
    }
}
