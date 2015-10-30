package com.blankoinc.blanko;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class WelcomeActivity extends Activity {
    ImageButton signInButton, signUpButton;
    EditText    email, pass;
    String      emailString, passString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        signInButton = (ImageButton) findViewById(R.id.sign_in_button);
        signUpButton = (ImageButton) findViewById(R.id.sign_up_button);
        email   = (EditText)findViewById(R.id.Email);
        pass    = (EditText)findViewById(R.id.Pass);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });
    }
    public void signIn() {
        checkInput();
    }

    public void signUp() {
        //starts the register activity
        Intent intent = new Intent(WelcomeActivity.this, RegisterActivity.class);
        startActivity(intent);
    }
    public void checkInput(){
        emailString = email.getText().toString();
        passString = pass.getText().toString();
        if (emailString.trim().length() <= 0) // the string is empty
        {
           createDialog("Please Enter an Email Address!");
        }
        if (passString.trim().length() <= 0) // the string is empty
        {
            createDialog("Please Enter a Password!");
        }
    }
    public void createDialog(String message){
        final Dialog dialog = new Dialog(WelcomeActivity.this);
        dialog.setContentView(R.layout.custom_dialog);
        dialog.setTitle("Alert");
        TextView text = (TextView)dialog.findViewById(R.id.text);
        text.setText(message);
        ImageView image = (ImageView)dialog.findViewById(R.id.image);
        image.setImageResource(R.drawable.ic_launcher);
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