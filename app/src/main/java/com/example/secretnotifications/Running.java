package com.example.secretnotifications;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;


public class Running extends AppCompatActivity {

    private final int VOLUME_UP = 1;
    private final int VOLUME_DOWN = 2;
    private final int ASSISTANT = 3;
    private final int SCREEN_TOUCH = 4;
    private int spinner1_value;
    private int spinner2_value;
    private int spinner3_value;
    private int spinner4_value;
    private int spinner5_value;
    private Vibrator vibration;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_running);
        vibration = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        userId = getIntent().getStringExtra("USER_ID");
        spinner1_value = Integer.parseInt(getIntent().getStringExtra("SPINNER_1"));
        spinner2_value = Integer.parseInt(getIntent().getStringExtra("SPINNER_2"));
        spinner3_value = Integer.parseInt(getIntent().getStringExtra("SPINNER_3"));
        spinner4_value = Integer.parseInt(getIntent().getStringExtra("SPINNER_4"));
        spinner5_value = Integer.parseInt(getIntent().getStringExtra("SPINNER_5"));

        turnOnSilentMode(this);
        turnOffScreen();
    }

    protected void turnOnSilentMode(Context context) {
        // Will prompt user to give permission to turn on Do Not Disturb
        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && !notificationManager.isNotificationPolicyAccessGranted()) {

            Intent intent = new Intent(
                    android.provider.Settings
                            .ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS);

            startActivity(intent);
        }

        // Turn on Do not Disturb
        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        try {
            audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            notificationManager.setInterruptionFilter(NotificationManager.INTERRUPTION_FILTER_NONE);
        }
    }

    // Turn off Do not Disturb
    protected void turnOffSilentMode(Context context) {
        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        AudioManager audioManager = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);

        try {
            audioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            notificationManager.setInterruptionFilter(NotificationManager.INTERRUPTION_FILTER_ALL);
        }
    }

    private void turnOffScreen() {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch(keyCode){
            case KeyEvent.KEYCODE_VOLUME_UP:
                Toast.makeText(this,"Volume Up Pressed", Toast.LENGTH_SHORT).show();
                try {
                    sendResponse(spinner1_value, spinner2_value, spinner3_value, spinner4_value, spinner5_value);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return true;
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                Toast.makeText(this,"Volume Down Pressed", Toast.LENGTH_SHORT).show();
                try {
                    sendResponse(spinner1_value, spinner2_value, spinner3_value, spinner4_value, spinner5_value);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return true;
            case KeyEvent.KEYCODE_VOICE_ASSIST:
                Toast.makeText(this,"Assistant Pressed", Toast.LENGTH_SHORT).show();
                try {
                    sendResponse(spinner1_value, spinner2_value, spinner3_value, spinner4_value, spinner5_value);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        Toast.makeText(this, "Touch press on x: " + x + " y: "+y, Toast.LENGTH_SHORT).show();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            sendResponse(spinner1_value, spinner2_value, spinner3_value, spinner4_value, spinner5_value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }

    private void sendResponse(int spin1, int spin2, int spin3, int spin4, int spin5) throws InterruptedException {
        // Turn off Do Not Disturb
        turnOffSilentMode(Running.this);
        Thread.sleep(1000);

        if (spin1 > 0) {
            priority1Response(spin1);
        }
        if (spin2 > 0) {
            priority2Response(spin2);
        }
        if (spin3 > 0) {
            priority3Response(spin3);
        }
        if (spin4 > 0) {
            priority4Response(spin4);
        }
        if (spin5 > 0) {
            priority5Response(spin5);
        }

        // Turn back on Do Not Disturb
        turnOnSilentMode(Running.this);
    }

    @SuppressLint("MissingPermission")
    private void priority1Response(int quantity) {

        long[] mVibratePattern;

        if (quantity == 1) {
            mVibratePattern = new long[]{0, 200, 100, 200};

        } else if (quantity == 2) {
           mVibratePattern = new long[]{0, 200, 100, 200, 100, 200};

        } else {
           mVibratePattern = new long[]{0, 200, 100, 200, 100, 200, 100, 200};
        }
        vibration.vibrate(mVibratePattern, -1);
    }

    @SuppressLint({"MissingPermission", "NewApi"})
    private void priority2Response(int quantity) {
        long[] mVibratePattern = new long[]{0, 400, 200, 400};
        vibration.vibrate(mVibratePattern, -1);
    }
    @SuppressLint({"MissingPermission", "NewApi"})
    private void priority3Response(int quantity) {
        long[] mVibratePattern = new long[]{0, 400, 200, 400};
        vibration.vibrate(mVibratePattern, -1);
    }
    @SuppressLint({"MissingPermission", "NewApi"})
    private void priority4Response(int quantity) {
        long[] mVibratePattern = new long[]{0, 400, 200, 400};
        vibration.vibrate(mVibratePattern, -1);
    }
    @SuppressLint({"MissingPermission", "NewApi"})
    private void priority5Response(int quantity) {
        long[] mVibratePattern = new long[]{0, 400, 200, 400};
        vibration.vibrate(mVibratePattern, -1);
    }

    public void endRunning(View view) {
        // Turn off Do Not Disturb
        AudioManager audioManager = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);
        try {
            audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //TODO: send back to rank screen
        finish(); // terminates and return back to previous activity
    }
}