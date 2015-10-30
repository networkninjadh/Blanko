package com.blankoinc.blanko;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
