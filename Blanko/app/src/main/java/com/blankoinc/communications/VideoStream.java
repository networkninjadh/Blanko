package com.blankoinc.communications;

import android.util.Log;

import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;

/**
 * Created by dhoward on 11/4/15.
 */

public class VideoStream {
    private VideoCapture capture;
    private String videoURL;
    private Mat frame;
    private static final String TAG = "VIDEOSTREAM";
    public VideoStream(String videoURL) {
        this.videoURL = videoURL;
    }
    public boolean setUpStream()
    {
        capture = new VideoCapture();
        if (capture.open(videoURL))
            return true;
        else
            return false;
    }

    public Mat getNextFrame()
    {
        if (capture.read(frame))
            return frame;
        else
            Log.v(TAG,"Failed getting last frame" );
        return null;
    }
}
