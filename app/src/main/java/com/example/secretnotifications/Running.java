package com.example.secretnotifications;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Pair;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Running extends AppCompatActivity {

    private Vibrator vibration;
    private String userId;
    public String layerA, layerB, layerC, layerD;
    public ArrayList<String> userPatterns;
    public ArrayList<String> currentPatternOrder;
    public ArrayList<String> currentPatternMultipleOrder;
    public String currentOrder;
    public Intent intent;
    public VibrationPatterns vibrationPatterns;
    public int firstTwo;

    public ArrayList<String> orderA = new ArrayList<>(Arrays.asList("A","B","AB", "B", "AB", "A", "A", "AB", "B", "AB"));
    public ArrayList<String> orderAMultiple = new ArrayList<>(Arrays.asList("A..", "B...", "A.B.", "A..B", "B..", "A...B", "A.B...", "A.", "AB.", "A.."));

    public ArrayList<String> orderB = new ArrayList<>(Arrays.asList("A", "AC", "C", "B", "ABC", "AB", "BC", "ABC", "AC", "C"));
    public ArrayList<String> orderBMultiple = new ArrayList<>(Arrays.asList("A..", "A.B..", "B...", "AC.", "A...B", "AC..", "BC..", "A.B.C.", "C.", "ABC."));

    public ArrayList<String> orderC = new ArrayList<>(Arrays.asList("CD", "A", "ABCD", "D", "ACD", "ABCD", "AD", "BC", "BCD", "BD"));
    public ArrayList<String> orderCMultiple = new ArrayList<>(Arrays.asList("A...D..", "BCD..", "AB.C", "ABD..", "A.C.D...", "AB.CD.", "A..D.", "BC...", "A.C..", "BC.D.."));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_running);
        vibration = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        userId = getIntent().getStringExtra("USER_ID");
        userPatterns = getIntent().getStringArrayListExtra("ORDER");
        currentOrder = getIntent().getStringExtra("CURRENT_ORDER");
        vibrationPatterns = new VibrationPatterns();
        firstTwo = 0;

        layerA = getIntent().getStringExtra("LAYER_A_PATTERN");
        layerB = getIntent().getStringExtra("LAYER_B_PATTERN");
        switch (currentOrder) {
            case "B":
                layerC = getIntent().getStringExtra("LAYER_C_PATTERN");
                break;
            case "C":
                layerC = getIntent().getStringExtra("LAYER_C_PATTERN");
                layerD = getIntent().getStringExtra("LAYER_D_PATTERN");
                break;
        }

//        Toast.makeText(Running.this,
//                "layer a: " + layerA +"\n" +
//                "layer b: " + layerB +"\n" +
//                "layer c: " + layerC+"\n" +
//                "layer d: " + layerD+"\n" +
//                "layer e: " + layerE,
//                Toast.LENGTH_LONG).show();

        // find out the pattern order according to currentOrder
        currentPatternOrder = findPatternOrder(currentOrder);
        currentPatternMultipleOrder = findMultiplePatternOrder(currentOrder);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch(keyCode){
            case KeyEvent.KEYCODE_VOLUME_UP:
//                Toast.makeText(this,"Volume Up Pressed", Toast.LENGTH_SHORT).show();
                try {
                    sendResponse();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return true;
            case KeyEvent.KEYCODE_VOLUME_DOWN:
//                Toast.makeText(this,"Volume Down Pressed", Toast.LENGTH_SHORT).show();
                try {
                    sendResponse();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return true;
            case KeyEvent.KEYCODE_VOICE_ASSIST:
//                Toast.makeText(this,"Assistant Pressed", Toast.LENGTH_SHORT).show();
                try {
                    sendResponse();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private ArrayList<String> findPatternOrder(String currentOrder) {
        switch(currentOrder) {
            case "A":
                return orderA;
            case "B":
                return orderB;
            case "C":
                return orderC;
        }
        return orderC;
    }

    private ArrayList<String> findMultiplePatternOrder(String currentOrder) {
        switch(currentOrder) {
            case "A":
                return orderAMultiple;
            case "B":
                return orderBMultiple;
            case "C":
                return orderCMultiple;
        }
        return orderCMultiple;
    }

    private void sendResponse() throws InterruptedException {
        //TODO: play patterns according to file
        String onePattern;
        if (currentPatternOrder.isEmpty()) {
            Toast.makeText(this, "Done with notifications, click 'Next Step' button.", Toast.LENGTH_LONG).show();
        } else {
            onePattern = currentPatternOrder.remove(0);
            makeVibrations(onePattern);
            if (firstTwo < 2) {
                Toast.makeText(this, "Vibrating: " + onePattern, Toast.LENGTH_LONG).show();
                firstTwo++;
            }
        }
    }

    // only uses pattern A & B
    public void makeVibrations(String patternToPlay) throws InterruptedException {
        List<Character> vibrations;
        char vibration;
        char[] patternCharArray;

        if (!patternToPlay.isEmpty()) {
            patternCharArray = patternToPlay.toCharArray();
            vibrations = new ArrayList<Character>();
            for(char c : patternCharArray) {
                vibrations.add(c);
            }

            VibrationThread vibrationThread = new VibrationThread(vibrations);
            vibrationThread.start();
        }
    }

    public Pair<long[],int[]> getPattern(String pattern) {
        switch (pattern) {
            case "PATTERN_A":
                return vibrationPatterns.vib1();
            case "PATTERN_B":
                return vibrationPatterns.vib2();
            case "PATTERN_C":
                return vibrationPatterns.vib3();
            case "PATTERN_D":
                return vibrationPatterns.vib4();
            case "PATTERN_E":
                return vibrationPatterns.vib5();
            case "MULTI":
                return vibrationPatterns.vibRepeat();
        }
        // CASE F
        return vibrationPatterns.vib6();
    }

    public int getDuration(String pattern) {
        switch (pattern) {
            case "PATTERN_A":
                return 1100;
            case "PATTERN_B":
                return 1600;
            case "PATTERN_C":
                return 1150;
            case "PATTERN_D":
                return 900;
            case "PATTERN_E":
                return 1600;
            case "MULTI":
                return 500;
        }
        // CASE F
        return 1600;
    }

    class VibrationThread extends Thread
    {
        List<Character> vibrations;

        public VibrationThread(List<Character> _vibrations)
        {
            vibrations = _vibrations;
        }

        @Override
        public void run()
        {
            while (!vibrations.isEmpty()) {
                char vibration = vibrations.remove(0);

                switch(vibration) {
                    case 'A':
                        vibrate(layerA);
                        break;
                    case 'B':
                        vibrate(layerB);
                        break;
                    case 'C':
                        vibrate(layerC);
                        break;
                    case 'D':
                        vibrate(layerD);
                        break;
                    case '.':
                        vibrate("MULTI");
                        break;
                }
            }
        }

        private void vibrate(String layer)
        {
            Pair<long[],int[]> pattern = getPattern(layer);
            long[] mVibratePattern = pattern.first;
            int[] amplitude = pattern.second;
            int duration = getDuration(layer);
            if (Build.VERSION.SDK_INT >= 26) {
                vibration.vibrate(VibrationEffect.createWaveform(mVibratePattern, amplitude, -1));
            } else {
                vibration.vibrate(mVibratePattern, -1);
            }

            try {
                Thread.sleep(duration);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void endRunning(View view) {
        if (!currentPatternMultipleOrder.isEmpty()) {
            currentPatternOrder.removeAll(currentPatternOrder);
            currentPatternOrder.addAll(currentPatternMultipleOrder);
            currentPatternMultipleOrder.removeAll(currentPatternMultipleOrder);
            firstTwo = 0;
        } else {
            if (userPatterns.size() > 0) {
                currentOrder = userPatterns.remove(0);
                Toast.makeText(this, "Orders: " + userPatterns + " currentOrder = " + currentOrder, Toast.LENGTH_LONG).show();
                switch (currentOrder) {
                    case "B":
//                    Toast.makeText(this,"Going to rank: " + "B", Toast.LENGTH_SHORT).show();
                        intent = new Intent(this, RankPatternsDrag3.class);
                        break;
                    case "C":
//                    Toast.makeText(this,"Going to rank: " + "C", Toast.LENGTH_SHORT).show();
                        intent = new Intent(this, RankPatternsDrag4.class);
                        break;
//                    case "D":
////                    Toast.makeText(this,"Going to rank: " + "D", Toast.LENGTH_SHORT).show();
//                        intent = new Intent(this, RankPatternsDrag5.class);
//                        break;
                }
                intent.putExtra("USER_ID", userId);
                intent.putExtra("CURRENT_ORDER", currentOrder);
                intent.putExtra("ORDER", userPatterns);
                startActivity(intent);

            } else {
                Intent intentEnd = new Intent(this, EndOfExperiment.class);
                intentEnd.putExtra("USER_ID", userId);
                startActivity(intentEnd);
            }
        }
    }
}