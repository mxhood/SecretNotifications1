package com.example.secretnotifications;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;


public class UserId  extends AppCompatActivity {

    private EditText mEdit;
    private String userId;
    private int numOfPatterns;
    public ArrayList<String> orderPatterns1 = new ArrayList<>(Arrays.asList("A", "B", "C"));
    public ArrayList<String> orderPatterns2 = new ArrayList<>(Arrays.asList("A", "C", "B"));
    public ArrayList<String> orderPatterns3 = new ArrayList<>(Arrays.asList("B", "A", "C"));
    public ArrayList<String> orderPatterns4 = new ArrayList<>(Arrays.asList("B", "C", "A"));
    public ArrayList<String> orderPatterns5 = new ArrayList<>(Arrays.asList("C", "A", "B"));
    public ArrayList<String> orderPatterns6 = new ArrayList<>(Arrays.asList("C", "B", "A"));

    public ArrayList<Integer> orderParticipants1 = new ArrayList<>(Arrays.asList(1, 7, 13, 19));
    public ArrayList<Integer> orderParticipants2 = new ArrayList<>(Arrays.asList(2, 8, 14, 20));
    public ArrayList<Integer> orderParticipants3 = new ArrayList<>(Arrays.asList(3, 9, 15, 21));
    public ArrayList<Integer> orderParticipants4 = new ArrayList<>(Arrays.asList(4, 10, 16, 22));
    public ArrayList<Integer> orderParticipants5 = new ArrayList<>(Arrays.asList(5, 11, 17, 23));
    public ArrayList<Integer> orderParticipants6 = new ArrayList<>(Arrays.asList(6, 12, 18, 24));

    public ArrayList<String> userPatterns;
    public Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_id_enter);

        mEdit = (EditText)findViewById(R.id.editText1);
        numOfPatterns = 5;
    }

    public ArrayList<String> getUserPatterns(int userId) {
        if (orderParticipants1.contains(userId)) {
            return orderPatterns1;
        } else if (orderParticipants2.contains(userId)) {
            return orderPatterns2;
        } else if (orderParticipants3.contains(userId)) {
            return orderPatterns3;
        } else if (orderParticipants4.contains(userId)) {
            return orderPatterns4;
            // orderParticipants4 or others
        } else if (orderParticipants5.contains(userId)) {
            return orderPatterns5;

        // orderParticipants6 or others
        } else {
            return orderPatterns6;
        }
    }

    public void startBtn(View v) {
            userId = mEdit.getText().toString();
//
//        if (!(userId < 0) && !(userId > 25)) {
//            Toast.makeText(UserId.this, "Please enter valid User ID: " + userId, Toast.LENGTH_SHORT).show();
//        } else {

            // Find which group user is in
            int userIdNum = Integer.parseInt(userId);
            userPatterns = getUserPatterns(userIdNum);

            String nextPattern = userPatterns.remove(0);
            Toast.makeText(this,"Orders: " + userPatterns + " currentOrder = " + nextPattern, Toast.LENGTH_SHORT).show();
            switch(nextPattern) {
                    case "A":
                        intent = new Intent(this, RankPatternsDrag2.class);
                        break;
                    case "B":
                        intent = new Intent(this, RankPatternsDrag3.class);
                        break;
                    case "C":
                        intent = new Intent(this, RankPatternsDrag4.class);
                        break;
                }

            // Write to file
            intent.putExtra("CURRENT_ORDER", nextPattern);
            intent.putExtra("ORDER", userPatterns);
            intent.putExtra("USER_ID", userId);
            startActivity(intent);
    }

}
