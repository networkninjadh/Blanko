package com.blankoinc.blanko;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankoinc.communications.Settings;

public class SettingsActivity extends Activity
{
    Button saveButton, cancelButton;
    EditText ipAddress, user, pass, ipAddress2;
    Settings applicationSettings = new Settings();
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        saveButton = (Button)findViewById(R.id.save);
        cancelButton = (Button)findViewById(R.id.clear);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo check the settings in the boxes then save all settings to disk
                if(checkInput())
                {
                    applicationSettings.setCameraIP(ipAddress.getText().toString());
                    applicationSettings.setCameraUserName(user.getText().toString());
                    applicationSettings.setCameraPassword(pass.getText().toString());
                    applicationSettings.setArduinoIp(ipAddress2.getText().toString());
                    applicationSettings.saveAllSettings();
                }
                Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Todo clear out all the boxes
                ipAddress2.getText().clear();
                ipAddress.getText().clear();
                user.getText().clear();
                pass.getText().clear();
            }
        });
    }
    public boolean checkInput()
    {
        //all code for checking the validity of the input goes in here
        String ipAddressString = ipAddress.getText().toString();
        String userString = user.getText().toString();
        String passString  = pass.getText().toString();
        String ipAddress2String = ipAddress2.getText().toString();
        boolean inputValid = true;
        if (ipAddressString.trim().length() <=0)
        {
            createDialog("Please Enter Your Name!");
            inputValid = false;
        }
        if (userString.trim().length() <=5) //check if name is valid only has to be 5 or more chars
        {
            createDialog("Name Must be at least 5 Characters!");
            inputValid = false;
        }
        if (passString.trim().length() <=0)
        {
            createDialog("Please Enter an Email!");
            inputValid = false;
        }
        if (ipAddress2String.trim().length() <=0)
        {
            createDialog("Please Enter a Password!");
            inputValid = false;
        }
       return inputValid;
    }
    public void createDialog(String message){
        final Dialog dialog = new Dialog(SettingsActivity.this);
        dialog.setContentView(R.layout.custom_dialog);
        dialog.setTitle("Alert");
        TextView text = (TextView)dialog.findViewById(R.id.text);
        text.setText(message);
        ImageView image = (ImageView)dialog.findViewById(R.id.image);
        image.setImageResource(R.drawable.head);
        Button dialogButton = (Button)dialog.findViewById(R.id.dialogButtonOK);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}