package com.blankoinc.blanko;

import android.app.Activity;
import android.os.Bundle;

import com.blankoinc.communications.Settings;

public class SettingsActivity extends Activity
{
    Settings applicationSettings;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }
}