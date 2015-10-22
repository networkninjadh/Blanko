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
    }

}