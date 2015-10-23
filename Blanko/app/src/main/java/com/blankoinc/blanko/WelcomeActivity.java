package com.blankoinc.blanko;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;

public class WelcomeActivity extends Activity {
    ImageButton signInButton;
    ImageButton signUpButton;
    EditText    email;
    EditText    pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        signInButton = (ImageButton) findViewById(R.id.sign_in_button);
        signUpButton = (ImageButton) findViewById(R.id.sign_up_button);
        email = (EditText) findViewById(R.id.Email);
        pass = (EditText) findViewById(R.id.Pass);
    }
}