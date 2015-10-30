package com.blankoinc.blanko;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
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
        final MediaPlayer splashMediaPlayer = MediaPlayer.create(SplashActivity.this, R.raw.splash_sound); // just to show that we can have a sound here this will be replaced with final splash sound
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
                splashMediaPlayer.start();
            }
        });
        timerThread.start();
    }
}
