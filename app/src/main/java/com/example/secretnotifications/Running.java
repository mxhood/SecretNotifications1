package com.example.secretnotifications;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class Running extends AppCompatActivity {

    private final int VOLUME_UP = 1;
    private final int VOLUME_DOWN = 2;
    private final int ASSISTANT = 3;
    private final int SCREEN_TOUCH = 4;
    private String spinner1_value;
    private String spinner2_value;
    private String spinner3_value;
    private String spinner4_value;
    private String spinner5_value;
    private Vibrator vibration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_running);
        vibration = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        spinner1_value = getIntent().getStringExtra("SPINNER_1");
        spinner2_value = getIntent().getStringExtra("SPINNER_2");
        spinner3_value = getIntent().getStringExtra("SPINNER_3");
        spinner4_value = getIntent().getStringExtra("SPINNER_4");
        spinner5_value = getIntent().getStringExtra("SPINNER_5");

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
                    sendResponse(VOLUME_UP);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return true;
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                Toast.makeText(this,"Volume Down Pressed", Toast.LENGTH_SHORT).show();
                try {
                    sendResponse(VOLUME_DOWN);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return true;
            case KeyEvent.KEYCODE_VOICE_ASSIST:
                Toast.makeText(this,"Assistant Pressed", Toast.LENGTH_SHORT).show();
                try {
                    sendResponse(ASSISTANT);
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
            sendResponse(SCREEN_TOUCH);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }

    private void sendResponse(int buttonPressed) throws InterruptedException {
        // analyze notifications
        // Turn off Do Not Disturb
        turnOffSilentMode(Running.this);
        // Toast.makeText(this, "buzzing for "+buttonPressed, Toast.LENGTH_SHORT).show();
        Thread.sleep(1000);
        // buzz phone
//        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
//        if (vibrator.hasVibrator()) {
//            vibrator.vibrate(500); // for 500 ms
//        }
        customVibratePatternNoRepeat();
        // Turn back on Do Not Disturb
        turnOnSilentMode(Running.this);
    }

    private void customVibratePatternNoRepeat() {
        // 0 : Start without a delay
        // 400 : Vibrate for 400 milliseconds
        // 200 : Pause for 200 milliseconds
        // 400 : Vibrate for 400 milliseconds
        long[] mVibratePattern = new long[]{500, 400};

        // -1 : Do not repeat this pattern
        // pass 0 if you want to repeat this pattern from 0th index
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

        finish(); // terminates and return back to previous activity
    }
}