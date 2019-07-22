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

    public ArrayList<String> orderA = new ArrayList<>(Arrays.asList("A","AB","B", "A", "AB", "A", "A", "AB", "B", "AB"));
    public ArrayList<String> orderAMultiple = new ArrayList<>(Arrays.asList("A..", "A.B...", "A.B.", "A..B.", "B..", "A...B.", "A.B...", "A.", "A.B.", "A.."));

    public ArrayList<String> orderB = new ArrayList<>(Arrays.asList("A", "BC", "C", "B", "ABC", "AB", "BC", "ABC", "AC", "C"));
    public ArrayList<String> orderBMultiple = new ArrayList<>(Arrays.asList("A..", "A.B..", "B...", "A.C.", "A...B.", "A.C..", "B.C..", "A.B.C.", "C.", "A.B.C."));

    public ArrayList<String> orderC = new ArrayList<>(Arrays.asList("AB", "CD", "ABCD", "D", "ACD", "ABCD", "AD", "BC", "BCD", "BD"));
    public ArrayList<String> orderCMultiple = new ArrayList<>(Arrays.asList("A...D..", "B.C.D..", "A.B.C.", "A.B.D..", "A.C.D...", "A.B.C.D.", "A..D.", "B.C...", "A.C..", "B.C.D.."));

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
                String text = makeToast(onePattern);
                Toast.makeText(this, "Vibrating: " + text, Toast.LENGTH_LONG).show();
                firstTwo++;
            }
        }
    }

    public String makeToast(String onePattern) {
        char[] chars = onePattern.toCharArray();
        if (chars.length > 1) {
            if (chars[1] == '.') {
                List<Character> allChars = new ArrayList<Character>();
                for (char c : chars) {
                    allChars.add(c);
                }
                int count = 0;
                String finalString = "";
                finalString = finalString + allChars.remove(0);
                while (!allChars.isEmpty()) {
                    char currentChar = allChars.remove(0);
                    if (currentChar != '.') {
                        finalString = finalString + count + " " + currentChar;
                        count = 0;
                    } else {
                        count++;
                    }
                }
                finalString = finalString + count;
                return finalString;
            } else {
                return onePattern;
            }
        } else {
            return onePattern;
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
                return 1500;
            case "PATTERN_B":
                return 2000;
            case "PATTERN_C":
                return 1500;
            case "PATTERN_D":
                return 1500;
            case "PATTERN_E":
                return 2000;
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
                boolean last = false;
                if (!vibrations.isEmpty()) {
                    if (vibrations.get(0) != '.') {
                        last = true;
                    }
                }

                switch(vibration) {
                    case 'A':
                        vibrate(layerA, false);
                        break;
                    case 'B':
                        vibrate(layerB, false);
                        break;
                    case 'C':
                        vibrate(layerC, false);
                        break;
                    case 'D':
                        vibrate(layerD, false);
                        break;
                    case '.':
                        vibrate("MULTI", last);
                        break;
                }
            }
        }

        private void vibrate(String layer, boolean last)
        {
            Pair<long[],int[]> pattern = getPattern(layer);
            long[] mVibratePattern = pattern.first;
            int[] amplitude = pattern.second;
            int duration = getDuration(layer);
            if (last) {
                duration = 1500;
            }
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
                    case "A":
                        intent = new Intent(this, RankPatternsDrag2.class);
                        break;

                    case "B":
//                    Toast.makeText(this,"Going to rank: " + "B", Toast.LENGTH_SHORT).show();
                        intent = new Intent(this, RankPatternsDrag3.class);
                        break;

                    case "C":
//                    Toast.makeText(this,"Going to rank: " + "C", Toast.LENGTH_SHORT).show();
                        intent = new Intent(this, RankPatternsDrag4.class);
                        break;
//
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