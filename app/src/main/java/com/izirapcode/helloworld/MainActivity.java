package com.izirapcode.helloworld;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import butterknife.OnClick;

import static com.izirapcode.helloworld.DataManager.*;

public class MainActivity extends Activity {

    TextView counterText,sumText;
    EditText editText;
    Button counterButton, sumButton, okButton ;
    int score;
    DbManager db;
    Calendar cal;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        final int day = cal.get(Calendar.DAY_OF_MONTH);
        final int month = cal.get(Calendar.MONTH);
        showCounter(day,month);
        showSum();

        counterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               db.addSmoke(month,day);
               showCounter(day,month);
              }
        });
        sumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                okButton.setVisibility(View.VISIBLE);
                editText.setVisibility(View.VISIBLE);
            }
        });

        okButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                setSum(Float.parseFloat(editText.getText().toString()),MainActivity.this);
                okButton.setVisibility(View.INVISIBLE);
                editText.setVisibility(View.INVISIBLE);
                showSum();
            }
        });

    }


    private void showCounter(int day,int month){
        counterText.setText(getString(R.string.CounterLabel,db.getTodayCount(day),db.getMonthCount(month),db.getSmokeCount()));
    }

    private void showSum(){
        sumText.setText(getString(R.string.SumLabel,getSum(this)));
    }

    private void init(){
        editText = findViewById(R.id.editText);
        editText.setVisibility(View.INVISIBLE);
        counterButton = findViewById(R.id.licznikButton);
        sumText=findViewById(R.id.wydanePieniadze);
        okButton =findViewById(R.id.okButton);
        okButton.setVisibility(View.INVISIBLE);
        counterText =findViewById(R.id.licznik);
        sumButton = findViewById(R.id.paczkaButton);
        score = getCounter(this);
        db = new DbManager(this);
        cal = Calendar.getInstance();
    }

}
