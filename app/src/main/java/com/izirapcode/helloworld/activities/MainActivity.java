package com.izirapcode.helloworld.activities;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.izirapcode.helloworld.database.DbManager;
import com.izirapcode.helloworld.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


import static com.izirapcode.helloworld.database.DataManager.*;

public class MainActivity extends BasicActivity {


    EditText editText;
    Button  sumButton;
    ImageButton counterButton;
    DbManager db;
    Calendar cal;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFields();
        addToolbar();
        counterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.addSmoke();
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
                        }
                        return false;
                    }
                });
            }
        });



    }



    private void initFields() {
        db = new DbManager(this);
        editText = findViewById(R.id.editText);
        counterButton = findViewById(R.id.licznikButton);
        sumButton = findViewById(R.id.paczkaButton);
        editText.setVisibility(View.INVISIBLE);
        cal = Calendar.getInstance();
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
