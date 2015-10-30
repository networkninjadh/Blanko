package com.blankoinc.blanko;

import android.app.Dialog;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

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
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });
    }

    public void signUp(){
        //all code for registering goes in here
        checkInput();
    }
    public void checkInput(){
        //all code for checking the validity of the input goes in here
        emailString = email.getText().toString();
        nameString = name.getText().toString();
        passString = pass.getText().toString();
        robonumString = robonum.getText().toString();
        if (emailString.trim().length() <=0)
        {
            createDialog("Please Enter an Email!");
        }
        if (nameString.trim().length() <=0)
        {
            createDialog("Please Enter Your Name!");
        }
        if (passString.trim().length() <=0)
        {
            createDialog("Please Enter a Password!");
        }
        if (robonumString.trim().length() <=0)
        {
            createDialog("Please Enter your Robot's ID Number");
        }
    }
    public void createDialog(String message){
        final Dialog dialog = new Dialog(RegisterActivity.this);
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