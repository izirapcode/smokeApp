package com.izirapcode.helloworld.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.EditText;
import android.widget.ImageView;

import com.izirapcode.helloworld.R;

public class Animation extends Activity {

    ImageView logo;
    EditText title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        logo = findViewById(R.id.logo);
        title = findViewById(R.id.title);
        startAnim();

    }

    private void startAnim(){
        android.view.animation.Animation in = new AlphaAnimation(0f,1f);
        in.setDuration(1000);
        in.setAnimationListener(new android.view.animation.Animation.AnimationListener() {
            @Override
            public void onAnimationStart(android.view.animation.Animation animation) {

            }

            @Override
            public void onAnimationEnd(android.view.animation.Animation animation) {
                logo.clearAnimation();
                nextAnim();
            }

            @Override
            public void onAnimationRepeat(android.view.animation.Animation animation) {

            }
        });
        logo.startAnimation(in);
    }

    private void nextAnim(){
        android.view.animation.Animation nextAnimation = new AlphaAnimation(0f,1f);
        nextAnimation.setDuration(1000);
        nextAnimation.setAnimationListener(new android.view.animation.Animation.AnimationListener() {
            @Override
            public void onAnimationStart(android.view.animation.Animation animation) {

            }

            @Override
            public void onAnimationEnd(android.view.animation.Animation animation) {
                Intent intent = new Intent(Animation.this,MainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onAnimationRepeat(android.view.animation.Animation animation) {

            }
        });
        title.setVisibility(View.VISIBLE);
        title.startAnimation(nextAnimation);
    }
}
