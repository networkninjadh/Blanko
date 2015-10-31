package com.blankoinc.blanko;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SlidingDrawer;
import android.widget.TextView;

public class MainActivity extends Activity {
    ImageButton upButton, downButton, leftButton, rightButton,
            laserButton, dockingButton, powerButton, lightButton, videoButton, nightButton, drawerButton;
    SlidingDrawer options;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        upButton = (ImageButton) findViewById(R.id.up_button);
        downButton = (ImageButton) findViewById(R.id.down_button);
        leftButton = (ImageButton) findViewById(R.id.left_button);
        rightButton = (ImageButton) findViewById(R.id.right_button);
        laserButton = (ImageButton) findViewById(R.id.laser_button);
        dockingButton = (ImageButton)findViewById(R.id.docking_button);
        powerButton = (ImageButton)findViewById(R.id.power_button);
        lightButton = (ImageButton)findViewById(R.id.light_button);
        videoButton = (ImageButton)findViewById(R.id.video_button);
        nightButton = (ImageButton)findViewById(R.id.night_button);
        drawerButton = (ImageButton)findViewById(R.id.handle);
        options = (SlidingDrawer)findViewById(R.id.slidingDrawer);
        upButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //up Button clicked
            }
        });
        downButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //down button clicked
            }
        });
        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //left button clicked
            }
        });
        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //right button clicked
            }
        });
        laserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //laser button clicked
            }
        });
        dockingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //docking button clicked
            }
        });
        powerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //power button clicked
            }
        });
        lightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //light button clicked
            }
        });
        videoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //video button clicked
            }
        });
        nightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //video button clicked
            }
        });

        drawerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        options.setOnDrawerOpenListener(new SlidingDrawer.OnDrawerOpenListener() {
            @Override
            public void onDrawerOpened() {
                drawerButton.setImageResource(R.drawable.down_button);
            }
        });
        options.setOnDrawerCloseListener(new SlidingDrawer.OnDrawerCloseListener() {
            @Override
            public void onDrawerClosed() {
                drawerButton.setImageResource(R.drawable.up_button);
            }
        });
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if (keyCode == KeyEvent.KEYCODE_BACK){
            final Dialog dialog = new Dialog(MainActivity.this);
            dialog.setContentView(R.layout.custom_dialog_2);
            dialog.setTitle("Alert");
            TextView text = (TextView)dialog.findViewById(R.id.text);
            text.setText("Are you sure you want to log out?");
            ImageView image = (ImageView)dialog.findViewById(R.id.image);
            image.setImageResource(R.drawable.ic_launcher);
            Button dialogButtonOk = (Button)dialog.findViewById(R.id.dialogButtonOK);
            Button dialogButtonCancel = (Button)dialog.findViewById(R.id.dialogButtonCancel);
            dialogButtonOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    finish(); //exit the activity and log out
                }
            });
            dialogButtonCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //don't exit the activity
                    dialog.dismiss();
                }
            });
            dialog.show();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
