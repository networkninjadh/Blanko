package com.blankoinc.blanko;

import android.os.Bundle;
import android.app.Activity;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class RegisterActivity extends Activity {
    ImageButton signUpButton;
    EditText email, pass, name, robonum;
    String   emailString, passString, nameString, robonumString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        signUpButton = (ImageButton) findViewById(R.id.sign_up_button);
        name   = (EditText)findViewById(R.id.Name);
        email   = (EditText)findViewById(R.id.Email);
        pass    = (EditText)findViewById(R.id.Pass);
        robonum   = (EditText)findViewById(R.id.Robonum);
        emailString = email.getText().toString();
        nameString = name.getText().toString();
        passString = pass.getText().toString();
        robonumString = robonum.getText().toString();
    }
    public boolean checkInput(){
        boolean inputValid = true;
        if (emailString == null)
        {
            //replace with a custom dialog box
            Toast.makeText(this, "Please enter an email", Toast.LENGTH_SHORT);
            inputValid = false;
        }
        if (nameString == null)
        {
            //replace with a custom dialog box
            Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT);
            inputValid = false;
        }
        if (passString == null)
        {
            //replace with a custom dialog box
            Toast.makeText(this, "Please enter a password", Toast.LENGTH_SHORT);
            inputValid = false;
        }
        if (robonumString == null)
        {
            //replace with a custom dialog box
            Toast.makeText(this, "Please enter the robot number", Toast.LENGTH_SHORT);
            inputValid = false;
        }
        return inputValid;
    }
}