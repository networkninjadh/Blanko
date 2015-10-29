package com.blankoinc.blanko;

import android.os.Bundle;
import android.app.Activity;
import android.widget.EditText;
import android.widget.ImageButton;

public class RegisterActivity extends Activity {


    ImageButton signUpButton;
    EditText email;
    EditText pass;
    EditText name;
    EditText robonum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        signUpButton = (ImageButton) findViewById(R.id.sign_up_button);
        name   = (EditText)findViewById(R.id.Name);
        email   = (EditText)findViewById(R.id.Email);
        pass    = (EditText)findViewById(R.id.Pass);
        robonum   = (EditText)findViewById(R.id.Robonum);
    }

}
