package com.blankoinc.blanko;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final ImageView splashScreen = (ImageView) findViewById(R.id.splashImage);
        splashScreen.setBackgroundResource(R.drawable.splash_animation_list);
        final AnimationDrawable splashAnimation = (AnimationDrawable) splashScreen.getBackground();

        Thread timerThread = new Thread()
        {
            public void run(){
                try{
                    sleep(3000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    Intent intent = new Intent(SplashActivity.this, WelcomeActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
        splashScreen.post(new Runnable() {
            @Override
            public void run() {
                splashAnimation.start();
            }
        });
        timerThread.start();
    }
}
