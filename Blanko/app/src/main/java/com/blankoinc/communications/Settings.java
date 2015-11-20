package com.blankoinc.communications;

import android.app.Application;

/**
 * Created by dhoward on 11/18/15.
 */
public class Settings {
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

    //Constructors/////////////////////////
    public Settings(){}
    public Settings(String userName, String emailAddress, String password, String robonum)
    {
        this.userName = userName;
        this.emailAddress = emailAddress;
        this.password  = password;
        this.robonum = robonum;
    }
    public boolean saveAllSettings()
    {
        //TODO saves all settings to android storage
        return true;
    }
    public boolean loadAllSettings()
    {
        //TODO loads all settings at the start of application
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
}