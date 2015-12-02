package com.blankoinc.communications;

import android.webkit.WebView;

import com.blankoinc.blanko.R;

/**
 * Created by dhoward on 11/4/15.
 */

public class VideoStream {
    private String videoURL;

    public VideoStream(String videoURL)
    {
        this.videoURL = videoURL;
    }

    public void runCommand(String command, WebView view)
    {

    }

    public void setStreamURL(String URL)
    {
        videoURL = URL;
    }

    public String getStreamURL()
    {
        return videoURL;
    }


}
