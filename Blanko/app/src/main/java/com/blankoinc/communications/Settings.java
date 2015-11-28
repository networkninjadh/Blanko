package com.blankoinc.communications;

import android.content.Context;
import android.util.Log;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by dhoward on 11/18/15.
 */
public class Settings {
    private static final String TAG = Settings.class.getName();
    private static final String FILENAME = "settings.txt";
    private String loadedSettings;
    Context context;
    // User Login Settings ///////////////
    private String userName;
    private String password;
    private String emailAddress;
    private String robonum;
    // IP Camera settings ////////////////
    private String streamURL;
    private String cameraIP;
    private String cameraUserName;
    private String cameraPassword;
    // Arduino Settings
    private String arduinoIp;

    //Constructors/////////////////////////
    public Settings(){ // mock settings
        this.userName = "abc";
        this.emailAddress = "networkninjadh@gmail.com";
        this.password  = "123";
        this.robonum = "123456789";
        this.cameraIP = "0.0.0.0";
        this.arduinoIp = "0.0.0.0";
        this.cameraPassword = "blanko";
        this.cameraUserName = "password1";
        loadedSettings = "";
    }
    public Settings(String userName, String emailAddress, String password,
                    String robonum, String cameraIP, String arduinoIp,
                    String cameraPassword, String cameraUserName)
    {
        this.userName = userName;
        this.emailAddress = emailAddress;
        this.password  = password;
        this.robonum = robonum;
        this.cameraIP = cameraIP;
        this.arduinoIp = arduinoIp;
        this.cameraPassword = cameraPassword;
        this.cameraUserName = cameraUserName;
    }
    public boolean saveAllSettings()
    {
        //TODO saves all settings to android storage
        boolean retval;
        FileOutputStream outputStream;
        try{
            outputStream = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
            outputStream.write(userName.getBytes()); // save userName
            outputStream.write(password.getBytes());
            outputStream.write(emailAddress.getBytes());
            outputStream.write(robonum.getBytes());
            outputStream.write(cameraIP.getBytes());
            outputStream.write(arduinoIp.getBytes());
            outputStream.write(cameraPassword.getBytes());
            outputStream.write(cameraUserName.getBytes());
            outputStream.close();
            retval = true;
        }
        catch (IOException e){
            Log.e(TAG, "File write failed: " + e.toString());
            retval = false;
        }
        return retval;
    }
    public boolean loadAllSettings()
    {
        //TODO loads all settings at the start of application
        try{
            FileInputStream fileInputStream = context.openFileInput(FILENAME);
            if (fileInputStream != null)
            {
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line;
                try {
                    while ((line = bufferedReader.readLine()) != null)
                        loadedSettings += line;
                } catch (Exception e){
                    e.printStackTrace();
                }
            }

        } catch (Exception e)
        {
            e.printStackTrace();
        }
        if (loadedSettings == "")
            return false;
        else
            return true;
    }
    public String getCameraIP()
    {
        return cameraIP;
    }
    public void setCameraIP(String ip)
    {
        this.cameraIP = ip;
    }
    // IP Camera Settings
    public String getStreamURL()
    {
        return streamURL;
    }
    public void setStreamURL(String url)
    {
        this.streamURL = url;
    }
    public String getCameraUserName()
    {
        return userName;
    }
    public void setCameraUserName(String name)
    {
        this.cameraUserName = name;
    }
    public String getCameraPassword()
    {
        return cameraPassword;
    }
    public void setCameraPassword(String cameraPassword)
    {
        this.cameraPassword = cameraPassword;
    }
    ///////////////////// User Login Settings //////////////////////////////////////////////////
    public String getUserName()
    {
        return this.userName;
    }
    public void setUserName(String userName)
    {
        this.userName = userName;
    }
    public String getEmailAddress()
    {
        return emailAddress;
    }
    public void setEmailAddress(String email)
    {
        this.emailAddress = email;
    }
    public void setPassword(String pass)
    {
        this.password = pass;
    }
    public void setRobonum(String robonum){
        this.robonum = robonum;
    }
    // Arduino settings
    public String getArduinoIp()
    {
        return this.arduinoIp;
    }
    public void setArduinoIp(String ip)
    {
        this.arduinoIp = ip;
    }

}