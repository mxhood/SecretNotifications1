package com.example.secretnotifications;

import android.content.Context;
import android.os.Vibrator;
import android.util.Pair;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;


public class VibrationPatterns {

    public Pair<long[],int[]> vib1() {
        return new Pair<long[],int[]>(new long[]{0, 200, 50, 200}, new int[]{0,100,0,100});
       // vibration.vibrate(mVibratePattern, -1);
    }
    public Pair<long[],int[]> vib2() {
        return new Pair<long[],int[]>(new long[]{0, 1000}, new int[]{0,100});
    }
    public Pair<long[],int[]> vib3() {
        return new Pair<long[],int[]>(new long[]{0, 150, 50, 150, 50, 150},new int[] {0,130,0,130,0,130});
    }
    public Pair<long[],int[]> vib4() {
        return new Pair<long[],int[]>(new long[]{0, 300}, new int[]{0,100});
    }
    public Pair<long[],int[]> vib5() {
        return new Pair<long[],int[]>(new long[]{0, 150, 50, 600, 50, 150},new int[]{0,100,0,100,0,100});
    }
    public Pair<long[],int[]> vibRepeat() {
        return new Pair<long[],int[]>(new long[]{0, 50}, new int[]{0,150});
    }
}
