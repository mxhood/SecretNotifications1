package com.example.secretnotifications;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

public class Running extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_running);

        turnOnSilentMode(this);
        turnOffScreen();
        collectNotifications();
        monitorTouch();
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

    private void monitorTouch() {
        boolean sensorTriggered = false;

        if (sensorTriggered) {
            sendResponse();
        }
    }

    private void sendResponse() {
        // analyze notifications
        // buzz phone
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
