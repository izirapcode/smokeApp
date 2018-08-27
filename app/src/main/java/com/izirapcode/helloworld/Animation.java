package com.izirapcode.helloworld;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;

public class Animation extends Activity {

    ImageView logo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        logo = findViewById(R.id.logo);
        startAnim();

    }

    private void startAnim(){
        android.view.animation.Animation in = new AlphaAnimation(0f,1f);
        in.setDuration(4000);
        in.setAnimationListener(new android.view.animation.Animation.AnimationListener() {
            @Override
            public void onAnimationStart(android.view.animation.Animation animation) {

            }

            @Override
            public void onAnimationEnd(android.view.animation.Animation animation) {
                Intent intent = new Intent(Animation.this,MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void onAnimationRepeat(android.view.animation.Animation animation) {

            }
        });
        logo.startAnimation(in);
    }
}
