package com.blankoinc.blanko;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
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

        signInButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    signInButton.setImageResource(R.drawable.sign_in_clicked);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    signInButton.setImageResource(R.drawable.sign_in_button);
                    signIn();
                }
                return true;
            }
        });
        signUpButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    signUpButton.setImageResource(R.drawable.sign_up_clicked);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    signUpButton.setImageResource(R.drawable.sign_up_button);
                    signUp();
                }
                return true;
            }
        });
    }
    public void signIn() {
        if (emailString == null || passString == null)
            checkInput();
        else if (emailString.equals("abc") && passString.equals("123")) { //Built in admin username and password
            Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
            startActivity(intent);
        }
        else
        {
            if (checkInput())
                login(emailString, passString);
        }
    }

    public void signUp() {
        //starts the register activity
        Intent intent = new Intent(WelcomeActivity.this, RegisterActivity.class);
        startActivity(intent);
    }
    public boolean checkInput(){
        //all code for checking the validity of the input goes here
        emailString = email.getText().toString();
        passString = pass.getText().toString();
        boolean inputValid = true;
        if (emailString.trim().length() <= 0) // the string is empty
        {
           createDialog("Please Enter an Email Address!");
            inputValid = false;
        }
        if (passString.trim().length() <= 0) // the string is empty
        {
            createDialog("Please Enter a Password!");
            inputValid = false;
        }
        return inputValid;
    }
    public void createDialog(String message){
        final Dialog dialog = new Dialog(WelcomeActivity.this);
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
    private void login(final String emailadress, final String password)
    {
        //todo later
    }
}