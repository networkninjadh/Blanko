package com.blankoinc.communications;

import android.webkit.WebView;

/**
 * Created by dhoward on 11/4/15.
 */

public class VideoStream {
    private String videoURL;
    private String resolution;
    private String pan;
    private String Ip;
    private String pass;
    private String user;
    private String port;
    private String command;

    public VideoStream(String videoURL)
    {
        this.videoURL = videoURL;
    }

    public void runCommand(String command, WebView view)
    {
        //todo make a unified way to communicate with cgi
        //will be able to run a cgi command on the ip camera
    }

    public void setStreamURL(String URL)
    {
        //sets the video url based off a string
        videoURL = URL;
    }

    public String getVideoURL()
    {
        //returns the video url
        //videoURL can either be built from its underlying parts or just returns videoURL
        return videoURL;
    }

    public void setResolution(int res)
    {
        //todo figure out code for setting resolution cgi then remake video url
    }

    public void setPan(int pan)
    {
        //todo figure out code for setting pan cgi then remake video url
    }

    public void setIp(String ip)
    {
        this.Ip = ip;
        videoURL = "http://"+ip+"/videostream.cgi?user=" + user + "&pwd=" + pass;
    }
    public String reboot()
    {
        videoURL = "http://"+Ip+"/reboot.cgi?user=" + user + "&pwd=" + pass;
        return videoURL;
    }
    public void setUser(String user)
    {
        this.user = user;
        videoURL = "http://"+Ip+"/videostream.cgi?user=" + user + "&pwd=" + pass;
    }

    public void setPass(String pass)
    {
        this.pass = pass;
        videoURL = "http://"+Ip+"/videostream.cgi?user=" + user + "&pwd=" + pass;
    }
}
