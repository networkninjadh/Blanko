package com.blankoinc.blanko;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SlidingDrawer;
import android.widget.TextView;
import android.widget.VideoView;

import android.os.AsyncTask;

import java.io.IOException;
import java.net.*;
import java.net.UnknownHostException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;
import android.content.Context;

import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.UUID;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Build;
import android.util.Log;


import com.blankoinc.communications.VideoStream;

public class MainActivity extends Activity {
    private static final String TAG = "BLANKO";
    ImageButton upButton, downButton, leftButton, rightButton,
            laserButton, dockingButton, powerButton, lightButton, videoButton, nightButton,
            drawerButton, laserUpButton, laserDownButton;
    SlidingDrawer options;
    VideoView videoView;
    WebView webView;
    VideoStream Foscam = new VideoStream("http://192.168.0.101/videostream.cgi?cmd=GetMJStream&user=blanko&pwd=password1"); //holds all info about the camera and stream as well as issuing commands to the camera some stuff held in settings

    private static final int REQUEST_ENABLE_BT = 1;
    private BluetoothAdapter btAdapter = null;
    private BluetoothSocket btSocket = null;
    private OutputStream outStream = null;


    private static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    //private static final UUID myUUID = UUID.fromString("1e399928-320b-48dc-a0d0-b2f72e35a80c");

    private static String macAddress = "20:15:03:03:09:50"; // MAC address for arduino bluetooth

    //Initialize strings commands to send to Arduino
    private String sendSignal, lightSignal;
    private String leftSignal = "a";
    private String rightSignal = "d";
    private String forwardSignal = "w";
    private String backSignal = "s";
    private String stopMotor = "q";
    private String laserUpSignal = "2";
    private String laserDownSignal = "3";
    private String laserStop = "4";
    int flag = 0;

    public String robotCommand;

    String arduinoServer;
    DatagramSocket datagramSocket;
    InetAddress ip;
    DatagramPacket send;

    //Check to see if network is online
    private Boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if (ni != null && ni.isConnected())
            return true;

        return false;
    }

    //Send data to Arduino in background thread
    public void sendData(String s) throws Exception {
        robotCommand = s;
        new BackGroundClass().execute();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main2);

        //btAdapter = BluetoothAdapter.getDefaultAdapter();
        //checkBTState();

        //Set control buttons robot
        upButton = (ImageButton) findViewById(R.id.up_button);
        downButton = (ImageButton) findViewById(R.id.down_button);
        leftButton = (ImageButton) findViewById(R.id.left_button);
        rightButton = (ImageButton) findViewById(R.id.right_button);
        laserUpButton = (ImageButton) findViewById(R.id.laser_upButton);
        laserButton = (ImageButton) findViewById(R.id.laser_button);
        laserDownButton = (ImageButton) findViewById(R.id.laser_downButton);
        dockingButton = (ImageButton) findViewById(R.id.docking_button);
        powerButton = (ImageButton) findViewById(R.id.power_button);
        lightButton = (ImageButton) findViewById(R.id.light_button);
        videoButton = (ImageButton) findViewById(R.id.video_button);
        nightButton = (ImageButton) findViewById(R.id.night_button);
        drawerButton = (ImageButton) findViewById(R.id.handle);
        options = (SlidingDrawer) findViewById(R.id.slidingDrawer);

        //Initialize video feed
        //videoView = (VideoView) findViewById(R.id.videoView);
        webView = (WebView)findViewById(R.id.webView);

        //webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        webView.setBackgroundColor(Color.BLACK);
        webView.loadUrl("http://192.168.0.101/videostream.cgi?cmd=GetMJStream&user=blanko&pwd=password1");



        //Move robot forward
        upButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    upButton.setImageResource(R.drawable.up_clicked);
                    sendSignal = forwardSignal;
                    try {
                        sendData(sendSignal);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    upButton.setImageResource(R.drawable.up_button);
                    sendSignal = stopMotor;
                    try {
                        sendData(sendSignal);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                return true;
            }
        });

        //Move robot backwards
        downButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    downButton.setImageResource(R.drawable.down_clicked);
                    sendSignal = backSignal;
                    try {
                        sendData(sendSignal);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    downButton.setImageResource(R.drawable.down_button);
                    sendSignal = stopMotor;
                    try {
                        sendData(sendSignal);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                return true;
            }
        });

        //Turn robot left
        leftButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    leftButton.setImageResource(R.drawable.left_clicked);
                    sendSignal = leftSignal;
                    try {
                        sendData(sendSignal);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    leftButton.setImageResource(R.drawable.left_button);
                    sendSignal = stopMotor;
                    try {
                        sendData(sendSignal);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                return true;
            }
        });

        //Turn robot right
        rightButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    rightButton.setImageResource(R.drawable.right_clicked);
                    sendSignal = rightSignal;
                    try {
                        sendData(sendSignal);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    rightButton.setImageResource(R.drawable.right_button);
                    sendSignal = stopMotor;
                    try {
                        sendData(sendSignal);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                return true;
            }
        });

        laserDownButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    laserDownButton.setImageResource(R.drawable.down_clicked);
                    sendSignal = laserDownSignal;
                    try {
                        sendData(sendSignal);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    laserDownButton.setImageResource(R.drawable.down_button);
                    sendSignal = laserStop;
                    try {
                        sendData(sendSignal);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                return true;
            }
        });

        laserButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    laserButton.setImageResource(R.drawable.laser_clicked);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    laserButton.setImageResource(R.drawable.laser_button);
                }
                return true;
            }
        });

        laserUpButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    laserUpButton.setImageResource(R.drawable.up_clicked);
                    sendSignal = laserUpSignal;
                    try {
                        sendData(sendSignal);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    laserUpButton.setImageResource(R.drawable.up_button);
                    sendSignal = laserStop;
                    try {
                        sendData(sendSignal);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                return true;
            }
        });

        //Did not implement
        dockingButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    dockingButton.setImageResource(R.drawable.docking_clicked);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    dockingButton.setImageResource(R.drawable.docking_button);
                }
                return true;
            }
        });


        powerButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    powerButton.setImageResource(R.drawable.power_clicked);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    powerButton.setImageResource(R.drawable.power_button);
                }
                return true;
            }
        });
        lightButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    lightButton.setImageResource(R.drawable.light_clicked);
                    if (flag == 1){
                        lightSignal = "0";
                        flag = 0;
                    }
                    else {
                        lightSignal = "1";
                        flag = 1;
                    }
                    sendSignal = lightSignal;
                    try {
                        sendData(sendSignal);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    lightButton.setImageResource(R.drawable.light_button);
                }
                return true;
            }
        });

        videoButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    videoButton.setImageResource(R.drawable.video_clicked);
                    webView.loadUrl("http://192.168.0.101/videostream.cgi?cmd=GetMJStream&user=blanko&pwd=password1");
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    //webView.loadUrl("http://192.168.1.18/reboot.cgi?user=blanko&pwd=password1");
                    videoButton.setImageResource(R.drawable.video_button);
                    //settings(); // start the settings activity
                }
                //webView.loadUrl("http://192.168.1.18/videostream.cgi?user=blanko&pwd=password1");
                return true;
            }
        });


        nightButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    nightButton.setImageResource(R.drawable.night_clicked);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    nightButton.setImageResource(R.drawable.night_button);
                }
                return true;
            }
        });

        drawerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        options.setOnDrawerOpenListener(new SlidingDrawer.OnDrawerOpenListener() {
            @Override
            public void onDrawerOpened() {
                drawerButton.setImageResource(R.drawable.down_button);
            }
        });
        options.setOnDrawerCloseListener(new SlidingDrawer.OnDrawerCloseListener() {
            @Override
            public void onDrawerClosed() {
                drawerButton.setImageResource(R.drawable.up_button);
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            final Dialog dialog = new Dialog(MainActivity.this);
            dialog.setContentView(R.layout.custom_dialog_2);
            dialog.setTitle("Alert");
            TextView text = (TextView) dialog.findViewById(R.id.text);
            text.setText("Are you sure you want to log out?");
            ImageView image = (ImageView) dialog.findViewById(R.id.image);
            image.setImageResource(R.drawable.head);
            Button dialogButtonOk = (Button) dialog.findViewById(R.id.dialogButtonOK);
            Button dialogButtonCancel = (Button) dialog.findViewById(R.id.dialogButtonCancel);
            dialogButtonOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    finish(); //exit the activity and log out
                }
            });
            dialogButtonCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //don't exit the activity
                    dialog.dismiss();
                }
            });
            dialog.show();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void settings() {
        Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
        startActivity(intent);
        finish();
    }


    //Background thread to send data packets to Arduino
    private class BackGroundClass extends AsyncTask<String, Void, Void> {

        protected Void doInBackground(String... params) {
            byte[] b = (robotCommand.getBytes());
            if (isOnline()) {

                arduinoServer = new String("192.168.0.105");

                try {
                    ip = InetAddress.getByName(arduinoServer);
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }

                try {
                    datagramSocket = new DatagramSocket();
                } catch (SocketException e) {
                    e.printStackTrace();
                }

                try {
                    send = new DatagramPacket(b, b.length, ip, 80);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    datagramSocket.send(send);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    datagramSocket.setSoTimeout(5);
                } catch (SocketException e) {
                    e.printStackTrace();
                }

                datagramSocket.close();
            } else {
                Toast.makeText(getApplicationContext(), "No network", Toast.LENGTH_LONG).show();
            }
            return null;
        }
    }

    /*private BluetoothSocket createBluetoothSocket(BluetoothDevice device) throws IOException {
        if(Build.VERSION.SDK_INT >= 10){
            try {
                final Method  m = device.getClass().getMethod("createInsecureRfcommSocket" +
                        "ToServiceRecord", new Class[] { UUID.class });
                return (BluetoothSocket) m.invoke(device, myUUID);
            } catch (Exception e) {
                Log.e(TAG, "Could not create Insecure RFComm Connection",e);
            }
        }
        return  device.createRfcommSocketToServiceRecord(myUUID);
    }

    @Override
    public void onResume() {
        super.onResume();

        Log.d(TAG, "...In onResume - Attempting client connect...");

        // Set up a pointer to the remote node using it's address.
        BluetoothDevice device = btAdapter.getRemoteDevice(macAddress);

        // Two things are needed to make a connection:
        //   A MAC address, which we got above.
        //   A Service ID or UUID.  In this case we are using the
        //     UUID for SPP.
        try {
            btSocket = device.createRfcommSocketToServiceRecord(myUUID);
        } catch (IOException e) {
            errorExit("Fatal Error", "In onResume() and socket create failed: " + e.getMessage() + ".");
        }

        // Discovery is resource intensive.  Make sure it isn't going on
        // when you attempt to connect and pass your message.
        btAdapter.cancelDiscovery();

        // Establish the connection.  This will block until it connects.
        Log.d(TAG, "...Connecting to Remote...");
        try {
            btSocket.connect();
            Log.d(TAG, "...Connection established and data link opened...");
        } catch (IOException e) {
            try {
                btSocket.close();
            } catch (IOException e2) {
                errorExit("Fatal Error", "In onResume() and unable to close socket during connection failure" + e2.getMessage() + ".");
            }
        }

        // Create a data stream so we can talk to server.
        Log.d(TAG, "...Creating Socket...");

        try {
            outStream = btSocket.getOutputStream();
        } catch (IOException e) {
            errorExit("Fatal Error", "In onResume() and output stream creation failed:" + e.getMessage() + ".");
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        Log.d(TAG, "...In onPause()...");

        if (outStream != null) {
            try {
                outStream.flush();
            } catch (IOException e) {
                errorExit("Fatal Error", "In onPause() and failed to flush output stream: " + e.getMessage() + ".");
            }
        }

        try     {
            btSocket.close();
        } catch (IOException e2) {
            errorExit("Fatal Error", "In onPause() and failed to close socket." + e2.getMessage() + ".");
        }
    }

    private void checkBTState() {
        // Check for Bluetooth support and then check to make sure it is turned on

        // Emulator doesn't support Bluetooth and will return null
        if(btAdapter==null) {
            errorExit("Fatal Error", "Bluetooth Not supported. Aborting.");
        } else {
            if (btAdapter.isEnabled()) {
                Log.d(TAG, "...Bluetooth is enabled...");
            } else {
                //Prompt user to turn on Bluetooth
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            }
        }
    }

    private void errorExit(String title, String message){
        Toast msg = Toast.makeText(getBaseContext(),
                title + " - " + message, Toast.LENGTH_SHORT);
        msg.show();
        finish();
    }

    private void sendData(String message) {
        byte[] msgBuffer = message.getBytes();

        Log.d(TAG, "...Sending data: " + message + "...");

        try {
            outStream.write(msgBuffer);
        } catch (IOException e) {
            String msg = "In onResume() and an exception occurred during write: " + e.getMessage();
            msg = msg +  ".\n\nCheck that the SPP UUID: " + myUUID.toString() + " exists on server.\n\n";

            errorExit("Fatal Error", msg);
        }
    }*/


}

