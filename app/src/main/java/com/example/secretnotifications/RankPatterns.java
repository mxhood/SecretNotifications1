package com.example.secretnotifications;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

public class RankPatterns extends AppCompatActivity{
    private String userId;
    private Spinner rank1, rank2, rank3, rank4, rank5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pattern_choose);

        userId = getIntent().getStringExtra("USER_ID");
        rank1 = (Spinner) findViewById(R.id.rank1);
        rank2 = (Spinner) findViewById(R.id.rank2);
        rank3 = (Spinner) findViewById(R.id.rank3);
        rank4 = (Spinner) findViewById(R.id.rank4);
        rank5 = (Spinner) findViewById(R.id.rank5);
    }

    // Read patterns from txt file
    public void patternA(View v) {

    }
    public void patternB(View v) {

    }
    public void patternC(View v) {

    }
    public void patternD(View v) {

    }
    public void patternE(View v) {

    }

    public void done(View v) {
        String rank1Value = String.valueOf(rank1.getSelectedItem());
        String rank2Value = String.valueOf(rank2.getSelectedItem());
        String rank3Value = String.valueOf(rank3.getSelectedItem());
        String rank4Value = String.valueOf(rank4.getSelectedItem());
        String rank5Value = String.valueOf(rank5.getSelectedItem());

        if (rank1Value.isEmpty() || rank2Value.isEmpty() || rank3Value.isEmpty() || rank4Value.isEmpty() || rank5Value.isEmpty()) {
            Toast.makeText(RankPatterns.this, "Please rank all the patterns", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(this, Running.class);
            intent.putExtra("USER_ID", userId);
            startActivity(intent);
        }
    }

    //    protected void turnOnSilentMode(Context context) {
//        // Will prompt user to give permission to turn on Do Not Disturb
//        NotificationManager notificationManager =
//                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
//                && !notificationManager.isNotificationPolicyAccessGranted()) {
//
//            Intent intent = new Intent(
//                    android.provider.Settings
//                            .ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS);
//
//            startActivity(intent);
//        }
//
//        // Turn on Do not Disturb
//        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
//        try {
//            audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            notificationManager.setInterruptionFilter(NotificationManager.INTERRUPTION_FILTER_NONE);
//        }
//    }

//    // Turn off Do not Disturb
//    protected void turnOffSilentMode(Context context) {
//        NotificationManager notificationManager =
//                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//        AudioManager audioManager = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);
//
//        try {
//            audioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            notificationManager.setInterruptionFilter(NotificationManager.INTERRUPTION_FILTER_ALL);
//        }
//    }

    //    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        float x = event.getX();
//        float y = event.getY();
//        Toast.makeText(this, "Touch press on x: " + x + " y: "+y, Toast.LENGTH_SHORT).show();
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        try {
//            sendResponse(spinner1_value, spinner2_value, spinner3_value, spinner4_value, spinner5_value);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        return true;
//    }
}
