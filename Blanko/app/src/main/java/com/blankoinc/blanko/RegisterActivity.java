package com.blankoinc.blanko;

import android.app.Dialog;
import android.os.Bundle;
import android.app.Activity;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankoinc.communications.Settings;

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

        signUpButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    signUpButton.setImageResource(R.drawable.sign_up_clicked);
                }
                else if(event.getAction() == MotionEvent.ACTION_UP)
                {
                    signUpButton.setImageResource(R.drawable.sign_up_button);
                    signUp();
                }
                return true;
            }
        });
    }


    public void signUp(){
        //all code for registering goes in here
        if (checkInput()) //if all input is valid
            registerUser(nameString, passString, emailString,robonumString); //register the user in the database
    }
    public boolean checkInput(){
        //all code for checking the validity of the input goes in here
        emailString = email.getText().toString();
        nameString = name.getText().toString();
        passString = pass.getText().toString();
        robonumString = robonum.getText().toString();
        boolean inputValid = true;
        if (nameString.trim().length() <=0)
        {
            createDialog("Please Enter Your Name!");
            inputValid = false;
        }
        if (nameString.trim().length() <=5) //check if name is valid only has to be 5 or more chars
        {
            createDialog("Name Must be at least 5 Characters!");
            inputValid = false;
        }
        if (emailString.trim().length() <=0)
        {
            createDialog("Please Enter an Email!");
            inputValid = false;
        }
        if (!isValidEmail(emailString.trim())) //check if email is invalid validEmail function
        {
            createDialog("Invalid Email Address!");
            inputValid = false;
        }
        if (passString.trim().length() <=0)
        {
            createDialog("Please Enter a Password!");
            inputValid = false;
        }
        if (isValidPassword(passString.trim())) //check if password is valid passwordValid function
        {
            createDialog("Please Enter a Valid Password!");
            inputValid = false;
        }
        if (robonumString.trim().length() <=0)
        {
            createDialog("Enter your Robot's ID Number");
            inputValid = false;
        }
        if (robonumString.trim().length() <10) //is going to be a 10 char number
        {
            createDialog("Robot ID must be 10 characters");
            inputValid = false;
        }
        return inputValid;
    }
    public void createDialog(String message){
        final Dialog dialog = new Dialog(RegisterActivity.this);
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
    public final static boolean isValidEmail(CharSequence target){
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }
    public final static boolean isValidPassword(CharSequence target){
        return !TextUtils.isEmpty(target);
    }
    public boolean registerUser(String name, String pass, String email, String robonum){
        Settings appUserSettings = new Settings(name, email, pass, robonum);
        boolean success = true;
        return success;
    }
}
