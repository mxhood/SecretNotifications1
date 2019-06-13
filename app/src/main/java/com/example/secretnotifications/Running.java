package com.example.secretnotifications;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.HapticFeedbackConstants;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class Running extends AppCompatActivity {

    private final int VOLUME_UP = 1;
    private final int VOLUME_DOWN = 2;
    private final int ASSISTANT = 3;
    private final int SCREEN_TOUCH = 4;

    private Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_running);
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        turnOnSilentMode(this);
        turnOffScreen();
        collectNotifications();
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

    private void turnOffScreen() {

    }

    private void collectNotifications() {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch(keyCode){
            case KeyEvent.KEYCODE_VOLUME_UP:
                Toast.makeText(this,"Volume Up Pressed", Toast.LENGTH_SHORT).show();
                sendResponse(VOLUME_UP);
                return true;
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                Toast.makeText(this,"Volume Down Pressed", Toast.LENGTH_SHORT).show();
                sendResponse(VOLUME_DOWN);
                return true;
            case KeyEvent.KEYCODE_VOICE_ASSIST:
                Toast.makeText(this,"Assistant Pressed", Toast.LENGTH_SHORT).show();
                sendResponse(ASSISTANT);
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        Toast.makeText(this, "Touch press on x: " + x + " y: "+y, Toast.LENGTH_SHORT).show();
        sendResponse(SCREEN_TOUCH);
        return true;
    }

    private void sendResponse(int buttonPressed) {
        // analyze notifications
        // buzz phone
        if (Build.VERSION.SDK_INT >= 26) {
            vibrator.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.EFFECT_HEAVY_CLICK));
            //Toast.makeText(this, "Vibrate", Toast.LENGTH_SHORT).show();
        } else {
            vibrator.vibrate(200);
            //Toast.makeText(this, "Vibrate", Toast.LENGTH_SHORT).show();
        }
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
