package com.izirapcode.helloworld;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static com.izirapcode.helloworld.DataManager.*;

public class MainActivity extends Activity {

    TextView counterText,sumText;
    EditText editText;
    Button counterButton, sumButton, okButton ;
    int score;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        showCounter();
        showSum();
        counterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score++;
                setCounter(score,MainActivity.this);
                showCounter();
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

    private void showCounter(){
        counterText.setText(getString(R.string.CounterLabel,getCounter(this)));
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
    }

}
