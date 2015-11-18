package com.blankoinc.blanko;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SlidingDrawer;
import android.widget.TextView;
import android.widget.VideoView;

import com.blankoinc.communications.VideoStream;


public class MainActivity extends Activity {
    ImageButton upButton, downButton, leftButton, rightButton,
            laserButton, dockingButton, powerButton, lightButton, videoButton, nightButton, drawerButton;
    SlidingDrawer options;
    VideoView videoView;
    VideoStream Foscam = new VideoStream("http://10.0.0.25/videoStream.cgi?usr=blanko&pwd=password1");
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
        videoView = (VideoView) findViewById(R.id.videoView);
        videoView.setVideoPath("http://10.0.0.25/videoStream.cgi?usr=blanko&pwd=password1");
        videoView.start();
        upButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    upButton.setImageResource(R.drawable.up_clicked);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    upButton.setImageResource(R.drawable.up_button);
                }
                return true;
            }
        });
        downButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    downButton.setImageResource(R.drawable.down_clicked);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    downButton.setImageResource(R.drawable.down_button);
                }
                return true;
            }
        });
        leftButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    leftButton.setImageResource(R.drawable.left_clicked);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    leftButton.setImageResource(R.drawable.left_button);
                }
                return true;
            }
        });
        rightButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    rightButton.setImageResource(R.drawable.right_clicked);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    rightButton.setImageResource(R.drawable.right_button);
                }
                return true;
            }
        });
        laserButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    laserButton.setImageResource(R.drawable.laser_clicked);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    laserButton.setImageResource(R.drawable.laser_button);
                }
                return true;
            }
        });
        dockingButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    dockingButton.setImageResource(R.drawable.docking_clicked);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    dockingButton.setImageResource(R.drawable.docking_button);
                }
                return true;
            }
        });
        powerButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    powerButton.setImageResource(R.drawable.power_clicked);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    powerButton.setImageResource(R.drawable.power_button);
                }
                return true;
            }
        });
        lightButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    lightButton.setImageResource(R.drawable.light_clicked);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    lightButton.setImageResource(R.drawable.light_button);
                }
                return true;
            }
        });
        videoButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    videoButton.setImageResource(R.drawable.video_clicked);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    videoButton.setImageResource(R.drawable.video_button);
                }
                return true;
            }
        });
        nightButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    nightButton.setImageResource(R.drawable.night_clicked);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    nightButton.setImageResource(R.drawable.night_button);
                }
                return true;
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
            image.setImageResource(R.drawable.head);
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
